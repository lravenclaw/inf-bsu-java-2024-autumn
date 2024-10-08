package com.solution;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Series {
    public abstract int getElement(int i);

    public int sumOf(int k){
        int result = 0;
        for (int i = 0; i < k; i++){
            result += getElement(i);
        }
        return result;
    }

    public String toString(int k){
        if (k == 0){
            return String.valueOf(getElement(0));
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < k - 1; i++){
            result.append(String.valueOf(getElement(i))).append(", ");
        }
        result.append(String.valueOf(getElement(k - 1)));
        return String.valueOf(result);
    }

    public void saveToFile(File file, int k) throws IOException{
        FileWriter writer = new FileWriter(file);
        writer.write(this.toString(k));
        writer.flush();
        writer.close();
    }
}
