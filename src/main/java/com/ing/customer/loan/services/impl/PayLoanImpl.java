package com.ing.customer.loan.services.impl;

import com.ing.customer.loan.auth.AuthenticatedUserDetails;
import com.ing.customer.loan.db.Customers;
import com.ing.customer.loan.db.LoanInstallments;
import com.ing.customer.loan.db.Loans;
import com.ing.customer.loan.db.repos.CustomersRepository;
import com.ing.customer.loan.db.repos.InstallmentsRepository;
import com.ing.customer.loan.db.repos.LoansRepository;
import com.ing.customer.loan.exceptions.InvalidPaidLoanAmount;
import com.ing.customer.loan.services.api.PayLoanApi;
import com.ing.customer.loan.services.models.Error;
import com.ing.customer.loan.services.models.PayLoanReq;
import com.ing.customer.loan.services.models.PayLoanRes;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data
@Slf4j
@RestController
@Service
public class PayLoanImpl extends AuthenticatedUserDetails implements PayLoanApi {


    final static BigDecimal zeroAmount = new BigDecimal(0);

    private final CustomersRepository customersRepository;

    private final LoansRepository loansRepository;

    private final InstallmentsRepository installmentsRepository;


    public PayLoanImpl(CustomersRepository customersRepository, LoansRepository loansRepository, InstallmentsRepository installmentsRepository) {
        this.customersRepository = customersRepository;
        this.loansRepository = loansRepository;
        this.installmentsRepository = installmentsRepository;
    }

    public static boolean isFurtherThanThreeMonths(Date inputDate) {
        LocalDate date = inputDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate threeMonthsLater = LocalDate.now().plusMonths(3);
        return date.isBefore(threeMonthsLater);
    }

    @Transactional
    @Override
    public ResponseEntity<Object> payLoanPost(PayLoanReq payLoanReq) {

        try{
            String operatedCustomer = operatedCustomer(payLoanReq.getCustomerId());
            Customers customer = customersRepository.findByCustomerId(operatedCustomer);
            Loans customerLoan = loansRepository.findByCustomerIdAndLoanId(operatedCustomer,payLoanReq.getLoanId());
            //TODO: query only next three loan installments to make loop less items,after the tests!
            List<LoanInstallments> notPaidInstallments = installmentsRepository.findByCustomerIdAndLoanIdAndIsPaidOrderByInstallmentOrder(
                    operatedCustomer, payLoanReq.getLoanId(), "N"
            );

            BigDecimal remainingPaidAmount = payLoanReq.getPaidAmount();
            BigDecimal totalPaidAmount = zeroAmount;
            BigDecimal marginAmount = zeroAmount;
            BigDecimal totalMarginAmount = zeroAmount;
            BigDecimal paidInstallmentAmount = zeroAmount;
            BigDecimal monthlyInstallmentAmount = notPaidInstallments.get(0).getAmount();

            if (monthlyInstallmentAmount.compareTo(remainingPaidAmount) > 0)
                throw new InvalidPaidLoanAmount(monthlyInstallmentAmount);
            int i = 0;
            while( remainingPaidAmount.compareTo(monthlyInstallmentAmount) >= 0
                    && i < notPaidInstallments.size()
            ){
                if(
                        remainingPaidAmount.compareTo(notPaidInstallments.get(i).getAmount()) >= 0
                         && isFurtherThanThreeMonths(notPaidInstallments.get(i).getDueDate())
                ){
                    //bonus 2
                    LocalDate dueDate = notPaidInstallments.get(i).getDueDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    long daysBetween = ChronoUnit.DAYS.between( new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), dueDate);
                    marginAmount = new BigDecimal("0.001").multiply(new BigDecimal(daysBetween)).multiply(monthlyInstallmentAmount);
                    totalMarginAmount = totalMarginAmount.add(marginAmount);
                    paidInstallmentAmount = notPaidInstallments.get(i).getAmount().subtract(marginAmount);
                    notPaidInstallments.get(i).setPaidAmount(paidInstallmentAmount);
                    //bonus 2
                    if( daysBetween < 0 && monthlyInstallmentAmount.compareTo(paidInstallmentAmount) > 0)
                        throw new InvalidPaidLoanAmount(paidInstallmentAmount);
                    //
                    notPaidInstallments.get(i).setIsPaid("Y");
                    totalPaidAmount = totalPaidAmount.add(paidInstallmentAmount);
                    remainingPaidAmount = remainingPaidAmount.subtract(paidInstallmentAmount);
                    notPaidInstallments.get(i).setPaymentDate(new Date());
                    installmentsRepository.save(notPaidInstallments.get(i));
                }
                i++;
            }
            PayLoanRes payLoanRes = new PayLoanRes();
            payLoanRes.setLoanStatus("NC");
            payLoanRes.setSpentAmount(totalPaidAmount);
            if(!totalPaidAmount.equals(zeroAmount)){
                customerLoan.setPaidAmount(customerLoan.getPaidAmount().add(totalPaidAmount));
                customerLoan.setLoanAmount(customerLoan.getLoanAmount().subtract(totalMarginAmount));//bonus 2
                if((customerLoan.getLoanAmount().subtract(customerLoan.getPaidAmount())).compareTo(zeroAmount) == 0){
                    customerLoan.setIsPaid("Y");
                    payLoanRes.setLoanStatus("C");
                    //Regarding Turkish regulations, customer limit is updated, If the loan is paid totally.
                    customer.setUsedCreditLimit(customer.getUsedCreditLimit().subtract(customerLoan.getCreditAmount()));
                    customersRepository.save(customer);
                }
                loansRepository.save(customerLoan);

            }
            System.gc();
            return ResponseEntity.status(HttpStatus.OK).body(payLoanRes);

        }
        catch (Exception e){
            Error error = new Error();
            error.code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
            error.message(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }

    }
}
