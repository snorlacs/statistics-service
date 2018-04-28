package com.statistics.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Instant;
import java.util.Date;

public class TimestampValidator implements ConstraintValidator<ValidTimestamp, Date> {
    @Override
    public void initialize(ValidTimestamp validTimestamp) {

    }

    @Override
    public boolean isValid(final Date timestamp, ConstraintValidatorContext constraintValidatorContext) {
        return new Date(Instant.now().minusSeconds(60L).toEpochMilli()).before(timestamp);
    }
}
