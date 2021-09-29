import java.util.ArrayList;

public class Geometries {

    private ArrayList<Polygon> Elenco;

    public Geometries() {
        Elenco = new ArrayList<>();
    }
    //Inserire poligono
    public void newPolygon(Polygon p){ Elenco.add(p); }
    //Prendere un poligono
    public Polygon getPolygon(int index){
        return Elenco.get(index);
    }
    //Contare quanti poligoni ci sono in elenco
    public int numPolygon(){ return Elenco.size(); }


}
