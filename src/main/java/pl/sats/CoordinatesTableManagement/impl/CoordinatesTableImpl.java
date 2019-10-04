package pl.sats.CoordinatesTableManagement.impl;

import pl.sats.CoordinatesTableManagement.CoordinatesTable;
import pl.sats.CoordinatesTableManagement.CoordinatesTableManagementException;
import pl.sats.FieldObservationsObjects.PointCoordinates.Point;

import java.io.Serializable;
import java.util.*;

public class CoordinatesTableImpl<T extends Point> implements CoordinatesTable, Serializable {
    private Map<String, T> listOfCoordinates = new HashMap<>();

    private static final long serialVersionUID = 1;

    @Override
    public void addCoordinate(Object o) throws CoordinatesTableManagementException {
        if (o instanceof Point) {
            String pointId = ((Point) o).getId();
            if (listOfCoordinates.containsKey(pointId)) {
                throw new CoordinatesTableManagementException("Table already contains point nr: " + pointId);
            }
            listOfCoordinates.put(pointId, (T) o);
        }
    }

    @Override
    public void addListOfCoordinate(Set t) throws CoordinatesTableManagementException {
        boolean isPointDuplicated = false;
        StringBuilder sb = new StringBuilder();
        for (Object o : t) {
            if (o instanceof Point) {
                String pointId = ((Point) o).getId();
                if (listOfCoordinates.containsKey(pointId)) {
                    sb.append(" ").append(pointId).append(",");
                    isPointDuplicated = true;

                }else{
                    listOfCoordinates.put(pointId, (T) o);
                }
            }
        }
        if(isPointDuplicated){
            throw new CoordinatesTableManagementException("Table already contains points: " + sb.toString());
        }
    }

    @Override
    public void deleteCoordinate(String id) {
        listOfCoordinates.remove(id);
    }


    @Override
    public void deleteListOfCoordinate(Set ids) {
        for (Object id : ids) {
            String pointId = (String) id;
            listOfCoordinates.remove(pointId);
        }
    }

    public Map<String, T> getListOfCoordinates() {
        return listOfCoordinates;
    }
}
