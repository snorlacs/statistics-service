package com.statistics.domain;

public class StatisticsBuilder {
    private double sum;
    private double average;
    private double max;
    private double min;
    private double count;

    public StatisticsBuilder sum(double sum) {
        this.sum = sum;
        return this;
    }

    public StatisticsBuilder average(double average) {
        this.average = average;
        return this;
    }

    public StatisticsBuilder max(double max) {
        this.max = max;
        return this;
    }

    public StatisticsBuilder min(double min) {
        this.min = min;
        return this;
    }

    public StatisticsBuilder count(double count) {
        this.count = count;
        return this;
    }

    public Statistics build() {
        return new Statistics(sum, average, max, min, count);
    }
}
