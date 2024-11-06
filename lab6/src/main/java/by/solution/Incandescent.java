package by.solution;

public class Incandescent extends LightBulb{
    private int timeinseconds;
    private double const1=3.14;

    public Incandescent(String manufacturer, Double power, int timeinseconds) {
        super(manufacturer, power);
        this.timeinseconds = timeinseconds;

    }

    public int getTimeinseconds() {
        return timeinseconds;
    }

    public void setTimeinseconds(int timeinseconds) {
        this.timeinseconds = timeinseconds;
    }

    public int findPrice(){
        return (int)(this.getPower()*const1*timeinseconds);
    }
    @Override
    public String toString() {
        return "Incandescent " + super.toString()+
                " time=" + timeinseconds;
    }
}
