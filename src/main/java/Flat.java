import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Flat implements Serializable
{
    private int number;
    private double ploshadi;
    private List<Person> person;
    public Flat(int number, double ploshadi, List<Person> person)
    {
        if(number <0 || ploshadi<0)
        {
            throw new IllegalArgumentException("Введен отрицательный номер млмл площадь");
        }
        if(person == null)
        {
            throw new IllegalArgumentException("Был подан пустой список людей");
        }
        this.number = number;
        this.ploshadi = ploshadi;
        this.person = person;
    }

    public double getPloshadi() {
        return ploshadi;
    }

    public int getNumber() {
        return number;
    }

    public List<Person> getPerson() {
        return person;
    }

    @Override
    public String toString() {
        return "Flat{" +
                "number=" + number +
                ", ploshadi=" + ploshadi +
                ", person=" + person +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flat flat = (Flat) o;
        return number == flat.number && Double.compare(flat.ploshadi, ploshadi) == 0 && Objects.equals(person, flat.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, ploshadi, person);
    }
}
