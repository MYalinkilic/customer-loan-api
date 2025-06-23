package com.ing.customer.loan.services.impl;

import com.ing.customer.loan.db.Loans;
import com.ing.customer.loan.db.repos.LoansRepository;
import com.ing.customer.loan.services.impl.ListLoanImpl;
import com.ing.customer.loan.services.models.Error;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ListLoanImplTest {

    @Mock
    private LoansRepository loansRepository;

    @InjectMocks
    private ListLoanImpl listLoanImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        listLoanImpl = new ListLoanImpl(loansRepository); // Constructor injection
        var auth = new UsernamePasswordAuthenticationToken("customerA", "custA", List.of());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Test
    public void testListLoan_Success() {

        Loans loan = new Loans();
        loan.setCustomerId("customerA");
        loan.setLoanId("L01");
        loan.setCreditAmount(new BigDecimal(100));
        loan.setLoanAmount(new BigDecimal(150));
        loan.setCreateDate(new Date());
        loan.setPaidAmount(new BigDecimal(0));
        loan.setNumberOfInstallments(6);
        loan.setIsPaid("N");
        loansRepository.save(loan);

        when(loansRepository.findByCustomerIdAndLoanIdAndOptionalIsPaid("customerA", "L01", "N"))
                .thenReturn(List.of(loan));

        ResponseEntity<Object> response = listLoanImpl.listLoanGet("customerA", "L01", "N");

        assertEquals(200, response.getStatusCodeValue());
        List<?> body = (List<?>) response.getBody();
        assertNotNull(body);
        assertEquals(1, body.size());
        assertTrue(body.get(0) instanceof Loans);
    }

    @Test
    public void testListLoan_NullResult_ThrowsSQLException() {
        // Arrange
        when(loansRepository.findByCustomerIdAndLoanIdAndOptionalIsPaid("customerA", "L01", "N"))
                .thenReturn(null);

        // Act
        ResponseEntity<Object> response = listLoanImpl.listLoanGet("customerC", "L01", "N");

        // Assert
        assertEquals(500, response.getStatusCodeValue());
        Error error = (Error) response.getBody();
        assertTrue(error.getMessage().contains("Data not found"));
    }

    @Test
    public void testListLoan_UnexpectedException() {
        // Arrange
        when(loansRepository.findByCustomerIdAndLoanIdAndOptionalIsPaid("customerC", "L01", "N"))
                .thenThrow(new RuntimeException("Unexpected failure"));

        // Act
        ResponseEntity<Object> response = listLoanImpl.listLoanGet("customerC", "L01", "N");

        // Assert
        assertEquals(500, response.getStatusCodeValue());
        Error error = (Error) response.getBody();
        assertTrue(error.getMessage().contains("Data not found"));
    }
}
