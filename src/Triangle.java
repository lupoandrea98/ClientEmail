public class Triangle extends Polygon {

    private int lato1;
    private int lato2;
    private int base;
    private int altezza;

    public Triangle(int lato1, int lato2, int base, int altezza) {
        this.lato1 = lato1;
        this.lato2 = lato2;
        this.base = base;
        this.altezza = altezza;
        setNumVertex(3);
    }

    @Override
    public int getPerimeter() { return lato1+lato2+base; }

    @Override
    public int getArea() { return (base*altezza)/2; }

}
