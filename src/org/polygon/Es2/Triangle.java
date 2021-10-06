package org.polygon.Es2;
public class Triangle extends Polygon {

    //TODO: rimuovere i lati e lasciare solo base e altezza.
    private double base;
    private double height;

    public Triangle(double base, double height) {
        super(3);
        this.base = base;
        this.height = height;
    }

    @Override
    public double getPerimeter() { return base; }

    @Override
    public double getArea() { return (base* height)/2; }

    //TODO: scrivere gli override dei metodi describe e parse attributes

    @Override
    public String[] describeAttributes() {
        String[] str = {"[ " + base + " ]", "[ " + height + " ]", "[ " + this.getNumVertex() + " ]"};
        return str;
    }

    @Override
    public void setAttributes(double[] attributes) {
        this.base = attributes[0];
        this.height = attributes[1];
    }


}
