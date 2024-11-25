package by.solution.pair;

import java.util.Objects;

public class Entry<F, S> {
    private final F key;
    private final S value;

    public Entry(F first, S second) {
        this.key = first;
        this.value = second;
    }

    public F getKey() {
        return key;
    }

    public S getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Entry)) {
            return false;
        }
        Entry<?, ?> p = (Entry<?, ?>) o;
        return Objects.equals(p.key, key) && Objects.equals(p.value, value);
    }

    public static <A, B> Entry<A, B> create(A a, B b) {
        return new Entry<A, B>(a, b);
    }
}

