package by.solution;

public abstract class LightBulb {
    private String manufacturer;
    private Double power;

    @Override
    public String toString() {
        return "manufacturer=" + manufacturer + " power=" + power;
    }

    public abstract int findPrice();

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }

    public LightBulb(String manufacturer, Double power) {
        this.manufacturer = manufacturer;
        this.power = power;
    }
}
