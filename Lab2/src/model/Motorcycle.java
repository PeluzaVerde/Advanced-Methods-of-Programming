package model;

public class Motorcycle implements MyInterface{
    String color;
    int id;

    @Override
    public String toString() {
        return "Motorcycle{" +
                "color='" + color + '\'' +
                ", id=" + id +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Motorcycle(int id, String color) {
        this.color = color;
        this.id = id;
    }

    public Motorcycle(String color) {
        this.color = color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String getColor() {
        return this.color;
    }
}
