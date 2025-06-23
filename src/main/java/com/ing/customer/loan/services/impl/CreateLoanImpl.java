package com.ing.customer.loan.services.impl;

import com.ing.customer.loan.auth.AuthenticatedUserDetails;
import com.ing.customer.loan.db.Customers;
import com.ing.customer.loan.db.LoanInstallments;
import com.ing.customer.loan.db.Loans;
import com.ing.customer.loan.db.repos.CustomersRepository;
import com.ing.customer.loan.db.repos.InstallmentsRepository;
import com.ing.customer.loan.db.repos.LoansRepository;
import com.ing.customer.loan.exceptions.InvalidRequestedLoanAmount;
import com.ing.customer.loan.services.api.CreateLoanApi;
import com.ing.customer.loan.services.models.CustomerLoanReq;
import com.ing.customer.loan.services.models.CustomerLoanRes;
import com.ing.customer.loan.services.models.Error;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Data
@Slf4j
@RestController
@Service
public class CreateLoanImpl extends AuthenticatedUserDetails implements CreateLoanApi {


    private final CustomersRepository customersRepository;

    private final LoansRepository loansRepository;

    private final InstallmentsRepository installmentsRepository;

    public CreateLoanImpl(LoansRepository loansRepository, InstallmentsRepository installmentsRepository,CustomersRepository customersRepository) {
        this.loansRepository = loansRepository;
        this.installmentsRepository = installmentsRepository;
        this.customersRepository = customersRepository;
    }

    public static String incrementLoanId(String loanId) {
        // Extract prefix and numeric part
        String prefix = loanId.replaceAll("\\d", "");        // "L"
        String numberPart = loanId.replaceAll("\\D", "");    // "01"

        int number = Integer.parseInt(numberPart) + 1;

        // Optional: Keep leading zeros (same length as original number part)
        String newNumberPart = String.format("%0" + numberPart.length() + "d", number);

        return prefix + newNumberPart;
    }


    @Transactional
    @Override
    public ResponseEntity<Object> createLoanPost(CustomerLoanReq customerLoanReq) {
        try{
            String operatedCustomer = customerLoanReq.getCustomerId();
            Customers customer = customersRepository.findByCustomerId(operatedCustomer);
            if(customer == null)
                throw new SQLException("operatedCustomer could not be found!");
            //TODO: MOVE TO BUSINESS LOGIC PACKAGE
            if(customerLoanReq.getLoanAmount().compareTo(customer.getCreditLimit().subtract(customer.getUsedCreditLimit())) > 0)
                throw new InvalidRequestedLoanAmount(customerLoanReq.getLoanAmount());
            //TODO: MOVE TO BUSINESS LOGIC PACKAGE
            int i = 0;
            BigDecimal totalLoanAmount = new BigDecimal("0");
            LoanInstallments loanInstallment = new LoanInstallments();
            LocalDate startDate = LocalDate.now().withDayOfMonth(1);
            //TODO: generate LoanId
            String generatedLoanId;
            Loans latestLoan = loansRepository.findLatestLoanNative();
            if (latestLoan == null)
                generatedLoanId = "L01";
            else
                 generatedLoanId = incrementLoanId(latestLoan.getLoanId());

            while (i < customerLoanReq.getInstallments().intValue()){
                //All installments should have same amount. Total amount for loan should be amount * (1 + interest rate)
                // TODO: ask interest amount should be multiply (numberOfInstallment/12)
                BigDecimal installmentAmount = (
                        customerLoanReq.getLoanAmount().multiply((customerLoanReq.getInterestRate().add(new BigDecimal(1))))
                );

                installmentAmount = installmentAmount.divide(customerLoanReq.getInstallments(),BigDecimal.ROUND_UP);
                loanInstallment.setCustomerId(operatedCustomer);
                loanInstallment.setLoanId(generatedLoanId);
                loanInstallment.setAmount(installmentAmount);
                loanInstallment.setIsPaid("N");
                loanInstallment.setInstallmentOrder(i+1);
                loanInstallment.setPaidAmount(new BigDecimal(0));
                startDate = startDate.plusMonths(1);
                loanInstallment.setDueDate(Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                loanInstallment.setPaymentDate(null);
                totalLoanAmount = totalLoanAmount.add(installmentAmount);
                installmentsRepository.saveAndFlush(loanInstallment);
                loanInstallment = new LoanInstallments();
                i++;
            }
            Loans loan = new Loans();
            loan.setCustomerId(operatedCustomer);
            loan.setNumberOfInstallments(customerLoanReq.getInstallments().intValue());
            loan.setCreditAmount(customerLoanReq.getLoanAmount());
            loan.setLoanAmount(totalLoanAmount);
            loan.setCreateDate(new Date());
            loan.setIsPaid("N");
            loan.setLoanId(generatedLoanId);
            loan.setPaidAmount(new BigDecimal(0));
            loansRepository.save(loan);
            //TODO: update customer used_credit_limit for the customer
            customer.setUsedCreditLimit(customerLoanReq.getLoanAmount());
            customersRepository.save(customer);
            //TODO: return success body with ResponseEntity.status(HttpStatus.ok).body(....);
            CustomerLoanRes customerLoanRes = new CustomerLoanRes();
            customerLoanRes.setLoanId(generatedLoanId);
            customerLoanRes.setTotalLoanAmount(totalLoanAmount);
            return ResponseEntity.status(HttpStatus.OK).body(customerLoanRes);


        } catch (Exception e){
            Error error = new Error();
            error.code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
            error.message(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
