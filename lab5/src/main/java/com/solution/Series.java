package com.solution;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Series {
    private int start, step,count;

    Series() {
        count = 0;
    }

    Series(int start, int step, int count) {
        this.start = start;
        this.step = step;
        this.count = count;
    }

    public abstract int getElement(int i);

    public int sumOf(){
        int result = 0;
        for (int i = 0; i < count; ++i){
            result += getElement(i);
        }
        return result;
    }

    public String toString(){
        if (count == 0){
            return String.valueOf(getElement(0));
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count - 1; i++){
            result.append(String.valueOf(getElement(i))).append(", ");
        }
        result.append(String.valueOf(getElement(count - 1)));
        return String.valueOf(result);
    }

    public void saveToFile(File file) throws IOException, IllegalArgumentException {
        if (file == null) {
            throw new IllegalArgumentException("Invalid argument! Expected file, null passed.");
        }

        FileWriter writer = new FileWriter(file);
        writer.write(this.toString());
        writer.flush();
        writer.close();
    }
}
