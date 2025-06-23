package com.ing.customer.loan.services.impl;

import com.ing.customer.loan.db.Customers;
import com.ing.customer.loan.db.LoanInstallments;
import com.ing.customer.loan.db.Loans;
import com.ing.customer.loan.db.repos.CustomersRepository;
import com.ing.customer.loan.db.repos.InstallmentsRepository;
import com.ing.customer.loan.db.repos.LoansRepository;
import com.ing.customer.loan.services.models.PayLoanReq;
import com.ing.customer.loan.services.models.PayLoanRes;
import com.ing.customer.loan.services.models.Error;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PayLoanImplTest {

    @Mock
    private CustomersRepository customersRepository;

    @Mock
    private LoansRepository loansRepository;

    @Mock
    private InstallmentsRepository installmentsRepository;

    @InjectMocks
    private PayLoanImpl payLoanImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        var auth = new UsernamePasswordAuthenticationToken("customerA", "custA", List.of());
        SecurityContextHolder.getContext().setAuthentication(auth);
        payLoanImpl = new PayLoanImpl(customersRepository, loansRepository, installmentsRepository) {

        };
    }

    @Test
    void successfulPartialPayment() {
        // Arrange
        String customerId = "customerA";
        String loanId = "L01";
        BigDecimal paidAmount = new BigDecimal("50");

        PayLoanReq request = new PayLoanReq();
        request.setCustomerId(customerId);
        request.setLoanId(loanId);
        request.setPaidAmount(paidAmount);

        Customers customer = new Customers();
        customer.setCustomerId(customerId);
        customer.setUsedCreditLimit(new BigDecimal("500"));

        Loans loan = new Loans();
        loan.setCustomerId(customerId);
        loan.setLoanId(loanId);
        loan.setLoanAmount(new BigDecimal("300"));
        loan.setCreditAmount(new BigDecimal("200"));
        loan.setPaidAmount(BigDecimal.ZERO);
        loan.setIsPaid("N");

        LoanInstallments installment = new LoanInstallments();
        installment.setAmount(new BigDecimal("50"));
        installment.setDueDate(new Date(System.currentTimeMillis() + 100000000));
        installment.setIsPaid("N");

        when(customersRepository.findByCustomerId(customerId)).thenReturn(customer);
        when(loansRepository.findByCustomerIdAndLoanId(customerId, loanId)).thenReturn(loan);
        when(installmentsRepository.findByCustomerIdAndLoanIdAndIsPaidOrderByInstallmentOrder(customerId, loanId, "N"))
                .thenReturn(List.of(installment));

        ResponseEntity<Object> response = payLoanImpl.payLoanPost(request);

        assertEquals(200, response.getStatusCodeValue());
        PayLoanRes res = (PayLoanRes) response.getBody();
        assertNotNull(res);
    }

    @Test
    void throwsInvalidPaidLoanAmount() {
        // Arrange
        PayLoanReq request = new PayLoanReq();
        request.setCustomerId("customerA");
        request.setLoanId("L01");
        request.setPaidAmount(new BigDecimal("100"));

        LoanInstallments installment = new LoanInstallments();
        installment.setAmount(new BigDecimal("500"));
        installment.setDueDate(new Date(System.currentTimeMillis() + 100000000));

        when(customersRepository.findByCustomerId(any())).thenReturn(new Customers());
        when(loansRepository.findByCustomerIdAndLoanId(any(), any())).thenReturn(new Loans());
        when(installmentsRepository.findByCustomerIdAndLoanIdAndIsPaidOrderByInstallmentOrder(any(), any(), eq("N")))
                .thenReturn(List.of(installment));

        ResponseEntity<Object> response = payLoanImpl.payLoanPost(request);

        assertEquals(500, response.getStatusCodeValue());
        Error error = (Error) response.getBody();
        assertNotNull(error);
        assertTrue(error.getMessage().contains("InvalidPaidLoanAmount"));
    }

    @Test
    void exceptionFlow() {
        // Arrange
        PayLoanReq request = new PayLoanReq();
        request.setCustomerId("customerA");
        request.setLoanId("L01");
        request.setPaidAmount(new BigDecimal("100"));

        when(customersRepository.findByCustomerId(any())).thenThrow(new RuntimeException("Unexpected DB error"));

        ResponseEntity<Object> response = payLoanImpl.payLoanPost(request);

        assertEquals(500, response.getStatusCodeValue());
        Error error = (Error) response.getBody();
        assertNotNull(error);
        assertTrue(error.getMessage().contains("Unexpected DB error"));
    }
}