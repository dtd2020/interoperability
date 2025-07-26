package mz.gov.inage.esircev.v2.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExactLengthValidator implements ConstraintValidator<ExactLength, String> {

    private int lenght;

    @Override
    public void initialize(ExactLength constraintAnnotation) {
        this.lenght = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;
        return value.length() == lenght;
    }
}
