package com.statistics.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.statistics.validation.ValidTimestamp;

import java.util.Date;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.amount, amount) == 0 &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, timestamp);
    }
}
