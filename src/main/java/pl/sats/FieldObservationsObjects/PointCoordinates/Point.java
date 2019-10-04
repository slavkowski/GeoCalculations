package pl.sats.FieldObservationsObjects.PointCoordinates;

import java.io.Serializable;

public class Point implements Serializable {

    private static final long serialVersionUID = 1;

    private String id;

    Point(String id) {
        this.id = id;
    }

    Point() {
    }

    public String getId() {
        return id;
    }
}
