package mz.gov.inage.esircev.v2.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExactLengthValidator.class)
public @interface ExactLength {

    String message() default "O tamanho deve ser exactamente {value} caracteres.";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    int value();
}
