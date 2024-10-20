package solution;

import java.util.Comparator;

public class Person implements Comparable<Person> {
    public int id;
    public int age;

    public String name;
    public String surname;

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public int compareTo(Person other) {

        return Comparator.comparing(Person::getId)
                .thenComparing(Person::getName)
                .thenComparing(Person::getSurname)
                .thenComparing(Person::getAge)
                .compare(this, other);
    }
}
