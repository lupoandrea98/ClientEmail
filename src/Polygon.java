abstract public class Polygon {

    private int numVertex;

    public int getNumVertex() {
        return numVertex;
    }

    public void setNumVertex(int numVertex) {
        this.numVertex = numVertex;
    }

    abstract public int getPerimeter();
    abstract public int getArea();

}
