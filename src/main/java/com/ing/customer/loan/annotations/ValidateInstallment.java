package com.ing.customer.loan.annotations;
import com.ing.customer.loan.annotations.bl.InstallmentValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = InstallmentValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateInstallment {
    String message() default "Installment must be 6, 9, 12, or 24"; // ✅ REQUIRED

    Class<?>[] groups() default {}; // ✅ Required for bean validation compatibility

    Class<? extends Payload>[] payload() default {}; // ✅ Required
}
