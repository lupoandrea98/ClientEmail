package org.polygon.Es2;
import java.lang.String;
public class Rectangle extends Polygon{

    private double base;
    private double height;

    public Rectangle(double base, double height) {
        super(4);
        this.base = base;
        this.height = height;
    }

    public double getBase() {
        return base;
    }

    public double getHeight() {
        return height;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public double getPerimeter(){
        return 2*(base+height);
    }

    @Override
    public double getArea(){
        return (base*height);
    }

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
