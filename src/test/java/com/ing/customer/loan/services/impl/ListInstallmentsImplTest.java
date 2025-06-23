package com.ing.customer.loan.services.impl;

import com.ing.customer.loan.db.LoanInstallments;
import com.ing.customer.loan.db.repos.InstallmentsRepository;
import com.ing.customer.loan.services.models.Error;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ListInstallmentsImplTest {

    @Mock
    private InstallmentsRepository installmentsRepository;

    private ListInstallmentsImpl listInstallmentsImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        var auth = new UsernamePasswordAuthenticationToken("customerA", "custA", List.of());
        SecurityContextHolder.getContext().setAuthentication(auth);
        listInstallmentsImpl = new ListInstallmentsImpl(installmentsRepository) {

        };
    }

    @Test
    public void testListInstallments_Success() {
        // Arrange
        LoanInstallments installment = new LoanInstallments();
        installment.setCustomerId("customerA");
        installment.setLoanId("L01");

        when(installmentsRepository.findByCustomerIdAndLoanId("customerA", "L01"))
                .thenReturn(List.of(installment));

        // Act
        ResponseEntity<Object> response = listInstallmentsImpl.listInstallmentsGet("customerA", "L01");

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        List<?> result = (List<?>) response.getBody();
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof LoanInstallments);
    }

    @Test
    public void testListInstallments_EmptyList_ThrowsSQLException() {
        // Arrange
        when(installmentsRepository.findByCustomerIdAndLoanId("customerA", "L01"))
                .thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<Object> response = listInstallmentsImpl.listInstallmentsGet("cust123", "L01");

        // Assert
        assertEquals(500, response.getStatusCodeValue());
        Error error = (Error) response.getBody();
        assertNotNull(error);
        assertTrue(error.getMessage().contains("Data not found"));
    }

    @Test
    public void testListInstallments_UnexpectedException() {
        // Arrange
        when(installmentsRepository.findByCustomerIdAndLoanId("cust123", "L01"))
                .thenThrow(new RuntimeException("DB crash"));

        // Act
        ResponseEntity<Object> response = listInstallmentsImpl.listInstallmentsGet("cust123", "L01");

        // Assert
        assertEquals(500, response.getStatusCodeValue());
        Error error = (Error) response.getBody();
        assertNotNull(error);
        assertTrue(error.getMessage().contains("Data not found"));
    }
}