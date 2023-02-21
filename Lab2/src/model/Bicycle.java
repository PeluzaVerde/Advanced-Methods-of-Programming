package model;

public class Bicycle implements MyInterface{

    int id;

    public Bicycle(int id, String color) {
        this.id = id;
        this.color = color;
    }

    String color;

    @Override
    public String toString() {
        return "Bicycle{" +
                "id=" + id +
                ", color='" + color + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String getColor() {
        return this.color;
    }
}
