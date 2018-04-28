package com.statistics.validation;

import org.junit.Test;

import java.time.Instant;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TimestampValidatorTest {
    @Test
    public void shouldReturnTrueIfTimestampIsWithinThePast60Seconds() throws Exception {
        TimestampValidator timeStampValidator = new TimestampValidator();
        assertTrue(timeStampValidator.isValid(new Date(Instant.now().toEpochMilli()), null));
    }

    @Test
    public void shouldReturnFalseIfTimestampIsOlderThan60Seconds() throws Exception {
        TimestampValidator timeStampValidator = new TimestampValidator();
        assertFalse(timeStampValidator.isValid(new Date(Instant.now().minusSeconds(62L).toEpochMilli()), null));
    }
}