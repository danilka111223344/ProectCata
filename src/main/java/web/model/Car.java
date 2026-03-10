package web.model;

public class Car {

    private String model;
    private int series;
    private Color color;

    public Car() {
    }

    public Car(String model, int series, Color color) {
        this.model = model;
        this.series = series;
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public int getSeries() {
        return series;
    }

    public Color getColor() {
        return color;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
