package com.ing.customer.loan.services.impl;

import com.ing.customer.loan.db.Customers;
import com.ing.customer.loan.db.repos.CustomersRepository;
import com.ing.customer.loan.db.repos.InstallmentsRepository;
import com.ing.customer.loan.db.repos.LoansRepository;
import com.ing.customer.loan.services.impl.CreateLoanImpl;
import com.ing.customer.loan.services.models.CustomerLoanReq;
import com.ing.customer.loan.services.models.CustomerLoanRes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreateLoanImplTest {

    @Mock
    private CustomersRepository customersRepository;

    @Mock
    private LoansRepository loansRepository;

    @Mock
    private InstallmentsRepository installmentsRepository;

    @InjectMocks
    private CreateLoanImpl createLoanImpl;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        createLoanImpl = new CreateLoanImpl(loansRepository, installmentsRepository,customersRepository);
    }

    @Test
    public void testCreateLoanSuccess() {

        CustomerLoanReq request = new CustomerLoanReq();
        request.setCustomerId("customerA");
        request.setLoanAmount(new BigDecimal("100"));
        request.setInstallments(new BigDecimal("6"));
        request.setInterestRate(new BigDecimal("0.5"));

        Customers customer = new Customers();
        customer.setCustomerId("customerA");
        customer.setCreditLimit(new BigDecimal("500"));
        customer.setUsedCreditLimit(new BigDecimal("0"));


        when(customersRepository.findByCustomerId("customerA")).thenReturn(customer);
        when(loansRepository.findLatestLoanNative()).thenReturn(null);


        ResponseEntity<Object> response = createLoanImpl.createLoanPost(request);


        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof CustomerLoanRes);
        CustomerLoanRes res = (CustomerLoanRes) response.getBody();
        assertEquals("L01", res.getLoanId());
        assertNotNull(res.getTotalLoanAmount());

        verify(installmentsRepository, times(6)).saveAndFlush(any());
        verify(loansRepository).save(any());
        verify(customersRepository).save(any());
    }

    @Test
    public void testCreateLoanAmountTooHigh() {

        CustomerLoanReq request = new CustomerLoanReq();
        request.setCustomerId("customerA");
        request.setLoanAmount(new BigDecimal("10000"));
        request.setInstallments(new BigDecimal("12"));
        request.setInterestRate(new BigDecimal("0.5"));

        Customers customer = new Customers();
        customer.setCustomerId("customerA");
        customer.setCreditLimit(new BigDecimal("500"));
        customer.setUsedCreditLimit(new BigDecimal("0"));

        when(customersRepository.findByCustomerId("customerA")).thenReturn(customer);

        ResponseEntity<Object> response = createLoanImpl.createLoanPost(request);

        assertEquals(500, response.getStatusCodeValue());
        assertTrue(response.getBody().toString().contains("InvalidRequestedLoanAmount"));
    }

    @Test
    public void testUnexpectedException() {

        CustomerLoanReq request = new CustomerLoanReq();
        request.setCustomerId("customerA");
        request.setLoanAmount(new BigDecimal("1000"));
        request.setInstallments(new BigDecimal("2"));
        request.setInterestRate(new BigDecimal("0.10"));

        when(customersRepository.findByCustomerId("customerC")).thenThrow(new RuntimeException("operatedCustomer could not be found!"));

        ResponseEntity<Object> response = createLoanImpl.createLoanPost(request);

        assertEquals(500, response.getStatusCodeValue());
        assertTrue(response.getBody().toString().contains("operatedCustomer could not be found!"));
    }
}
