package org.polygon.Es2.main;
import org.polygon.Es2.*;
import java.util.ArrayList;

public class Geometries {

    private ArrayList<Polygon> list;

    public Geometries() {
        list = new ArrayList<>();
    }

    public void newPolygon(Polygon p){
        if(!list.contains(p))
            list.add(p);
    }
    //Take polygon from the list
    public Polygon getPolygon(int index){
        return list.get(index);
    }
    //Enumerate the elements in the polygon's list
    public int numPolygon(){ return list.size(); }


}
