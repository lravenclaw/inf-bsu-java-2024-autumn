package com.solution;

public class Linear extends Series {
    Linear(int start, int step, int count) {
        super(start, step, count);
    }

    @Override
    public int getElement(int i) {
        if (i == 0){
            return this.getStart();
        }
        return getElement(i - 1) + this.getStep();
    }
}
