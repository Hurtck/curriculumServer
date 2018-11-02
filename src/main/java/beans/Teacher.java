package beans;

public class Teacher {
    private String number;
    private String name;

    public Teacher() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
