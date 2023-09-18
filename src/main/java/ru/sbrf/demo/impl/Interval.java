package ru.sbrf.demo.impl;

public class Interval<Type extends Comparable<Type>> {
    private final Type min;
    private final Type max;

    public Interval(Type min, Type max) {
        this.min = min;
        this.max = max;
    }

    public boolean toRange(Type type) {
        return (min.compareTo(type) <= 0) && (max.compareTo(type) <= 0);
    }

}
