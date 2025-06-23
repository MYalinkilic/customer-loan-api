package com.ing.customer.loan.exceptions;

import java.math.BigDecimal;

public class InvalidPaidLoanAmount extends RuntimeException{
     public InvalidPaidLoanAmount(BigDecimal installmentAmount){
         super("You cannot pay less than "+installmentAmount.toString());
     }
}
