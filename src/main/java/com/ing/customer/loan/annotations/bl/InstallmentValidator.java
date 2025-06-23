package com.ing.customer.loan.annotations.bl;

import com.ing.customer.loan.annotations.ValidateInstallment;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;
import java.util.Set;

public class InstallmentValidator implements ConstraintValidator<ValidateInstallment, BigDecimal> {
    private static final Set<BigDecimal> VALID_INSTALLMENTS = Set.of(new BigDecimal(6),new BigDecimal(9),new BigDecimal(12),new BigDecimal(24));

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        return value != null && VALID_INSTALLMENTS.contains(value);
    }
}
