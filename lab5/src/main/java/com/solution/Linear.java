package com.solution;

public class Linear extends Series {
    int origin;
    int difference;

    public Linear(int origin, int difference) {
        this.origin = origin;
        this.difference = difference;
    }

    public int getElement(int i) {
        if (i == 0){
            return origin;
        }
        return getElement(i - 1) + difference;
    }
}
