package solution;

import solution.*;
import java.lang.reflect.*;
import java.util.*;
import java.lang.*;

public class CustomHandle implements BST.ValueHandler<Integer> {
    @Override
    public void call(Integer value) {
        System.out.print(Integer.toString(value) + " ");
    }
}
