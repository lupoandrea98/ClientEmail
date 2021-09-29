public class Square extends Polygon {

    private int lato;

    public Square(int lato) {
        this.lato = lato;
        setNumVertex(4);
    }

    @Override
    public int getPerimeter() {
        return lato*4;
    }

    @Override
    public int getArea() {
        return lato*lato;
    }
}
