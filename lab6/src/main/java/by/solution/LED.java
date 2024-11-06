package by.solution;

public class LED extends LightBulb{
    private int diodnumber;
    private double const2=2.71;

    public LED(String manufacturer, Double power, int diodnumber) {
        super(manufacturer, power);
        this.diodnumber = diodnumber;
    }

    public int getDiodnumber() {
        return diodnumber;
    }

    public void setDiodnumber(int diodnumber) {
        this.diodnumber = diodnumber;
    }

    @Override
    public String toString() {
        return "LED " + super.toString()+
                " diodnumber=" + diodnumber;
    }

    public int findPrice(){
        return (int)(this.getPower()/const2*diodnumber);
    }
}
