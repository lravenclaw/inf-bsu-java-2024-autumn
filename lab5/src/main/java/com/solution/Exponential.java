package com.solution;

public class Exponential extends Series {
    Exponential(int start, int step, int count) {
        super(start, step, count);
    }

    public int getElement(int i) {
        if (i == 0) {
            return this.getStart();
        }
        return getElement(i - 1) * this.getStep();
    }
}
