package com.statistics.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Documented
@Target( FIELD )
@Retention( RetentionPolicy.RUNTIME )
@Constraint( validatedBy = TimestampValidator.class )
public @interface ValidTimestamp {
    String message() default "Invalid timestamp";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
