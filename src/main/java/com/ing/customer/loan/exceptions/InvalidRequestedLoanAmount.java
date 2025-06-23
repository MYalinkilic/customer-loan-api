package com.ing.customer.loan.exceptions;

import java.math.BigDecimal;

public class InvalidRequestedLoanAmount extends RuntimeException {
    public InvalidRequestedLoanAmount(BigDecimal requestedLoanAmount) {
        super(requestedLoanAmount.toString()+" exceeds limits!");
    }
}
