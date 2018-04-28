package com.statistics.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.statistics.validation.ValidTimestamp;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.Date;

@JsonAutoDetect
public class Transaction {

    private double amount;

    @ValidTimestamp(message = "Timestamp is older than a minute")
    private Date timestamp;

    @SuppressWarnings("Unused from code")
    private Transaction() {
    }

    public Transaction(double amount, Date timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public double getAmount() {
        return amount;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
