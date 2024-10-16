package solution;

import java.util.Objects;

public class Person implements Comparable<Person> {
    public int id;
    public int age;

    public String name;
    public String surname;

    @Override
    public int compareTo(Person other) {
        return Integer.compare(this.id, other.id);
    }
}
