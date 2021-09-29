public class Rectangle extends Polygon{

    private int base;
    private int altezza;

    public Rectangle(int base, int altezza) {
        this.base = base;
        this.altezza = altezza;
        setNumVertex(4);
    }

    @Override
    public int getPerimeter(){
        return 2*(base+altezza);
    }

    @Override
    public int getArea(){
        return (base*altezza);
    }



}
