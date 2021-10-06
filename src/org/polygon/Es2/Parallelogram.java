package org.polygon.Es2;
public class Parallelogram extends Rectangle {

    private double lato;

    public Parallelogram(double base, double altezza, double lato) {
        super(base, altezza);
        this.lato = lato;
    }

    @Override
    public double getPerimeter() {
        return (getBase() + lato)*2;
    }

    //TODO: scrivere gli override dei metodi describe e parse attributes

    @Override
    public String[] describeAttributes() {
        String[] str = {"[ " + getBase() + " ]", "[ " + getHeight() + " ]", "[ " + this.getNumVertex() + " ]", "[ " + lato + " ]"};
        return str;
    }

    @Override
    public void setAttributes(double[] attributes) {
        setBase(attributes[0]);
        setHeight(attributes[1]);
        lato = attributes[2];
    }
}
