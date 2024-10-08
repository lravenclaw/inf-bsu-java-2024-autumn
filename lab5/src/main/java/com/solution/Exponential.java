package com.solution;

public class Exponential extends Series {
    int origin;
    int product;

    public Exponential(int origin, int product) {
        this.origin = origin;
        this.product = product;
    }

    public int getElement(int i) {
        if (i == 0){
            return origin;
        }
        return getElement(i - 1) * product;
    }
}
