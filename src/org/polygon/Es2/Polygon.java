package org.polygon.Es2;

abstract public class Polygon {

    private int numVertex;

    public Polygon(int vertex) {
        numVertex = vertex;
    }

    public int getNumVertex() {
        return numVertex;
    }

    public abstract double getPerimeter();
    public abstract double getArea();

    public abstract String[] describeAttributes();

    public abstract void setAttributes(double[] attributes);

    public boolean equals(Object o) {
        if(this == null || o == null)
            return false;
        else

    }



}
