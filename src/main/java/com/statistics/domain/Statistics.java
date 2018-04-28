package com.statistics.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.DoubleSummaryStatistics;

public class Statistics {
    private final double sum;
    private final double average;
    private final double max;
    private final double min;
    private final double count;

    public Statistics(double sum, double average, double max, double min, double count) {
        this.sum = sum;
        this.average = average;
        this.max = max;
        this.min = min;
        this.count = count;
    }

    public static Statistics from(DoubleSummaryStatistics summaryStatistics) {
        return new StatisticsBuilder()
                .average(summaryStatistics.getAverage())
                .sum(summaryStatistics.getSum())
                .min(summaryStatistics.getMin())
                .max(summaryStatistics.getMax())
                .count(summaryStatistics.getCount())
                .build();
    }

    public double getSum() {
        return sum;
    }

    public double getAverage() {
        return average;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }

    public double getCount() {
        return count;
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
