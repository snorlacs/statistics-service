package com.statistics.domain;

import java.util.DoubleSummaryStatistics;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistics that = (Statistics) o;
        return Double.compare(that.sum, sum) == 0 &&
                Double.compare(that.average, average) == 0 &&
                Double.compare(that.max, max) == 0 &&
                Double.compare(that.min, min) == 0 &&
                Double.compare(that.count, count) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sum, average, max, min, count);
    }
}
