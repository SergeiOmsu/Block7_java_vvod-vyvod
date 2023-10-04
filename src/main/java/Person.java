import java.io.Serializable;
import java.util.Objects;

public class Person implements Serializable {
    private String firstName;
    private String middleName;
    private String lastName;
    private int bDay;
    private int bMouth;
    private int bYear;

    public Person(String firstName, String middleName, String lastName, int bDay, int bMouth, int bYear) {
        if (bDay < 0 || bMouth < 0 || bYear < 0) {
            throw new IllegalArgumentException("Ты тупой дурак");
        }
        this.bDay = bDay;
        this.bMouth = bMouth;
        this.bYear = bYear;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public Person() {
        firstName = null;
        middleName = null;
        lastName = null;
        bYear = 0;
        bMouth = 0;
        bDay = 0;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getbDay() {
        return bDay;
    }

    public int getbMouth() {
        return bMouth;
    }

    public int getbYear() {
        return bYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return bDay == person.bDay && bMouth == person.bMouth && bYear == person.bYear && Objects.equals(firstName, person.firstName) && Objects.equals(middleName, person.middleName) && Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, middleName, lastName, bDay, bMouth, bYear);
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", bDay=" + bDay +
                ", bMouth=" + bMouth +
                ", bYear=" + bYear +
                '}';
    }
}

