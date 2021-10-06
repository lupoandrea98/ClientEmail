package org.polygon.Es2;
public class Square extends Polygon {

    private double side;

    public Square(double side) {
        super(4);
        this.side = side;
    }

    @Override
    public double getPerimeter() {
        return side *4;
    }

    @Override
    public double getArea() {
        return side * side;
    }

    //TODO: scrivere gli override dei metodi describe e parse attributes
    @Override
    public String[] describeAttributes() {
        String[] str = {"[ " + side+ " ]"};
        return str;
    }

    @Override
    public void setAttributes(double[] attributes) {
        this.side = attributes[0];
    }
}
