package model;

public class Car implements MyInterface{

    String color;
    int id;

    @Override
    public String toString() {
        return "car{" +
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

    public Car( int id,String color) {
        this.color = color;
        this.id = id;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Car(String color) {
        this.color = color;
    }

    @Override
    public String getColor() {
        return this.color;
    }
}
