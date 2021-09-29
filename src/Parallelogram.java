public class Parallelogram extends Polygon {

    private int base;
    private int altezza;
    private int lato;

    public Parallelogram(int base, int altezza, int lato) {
        this.base = base;
        this.altezza = altezza;
        this.lato = lato;
        setNumVertex(4);
    }

    @Override
    public int getArea(){
        return (base*altezza);
    }

    @Override
    public int getPerimeter() { return(base+lato)*2; }

}
