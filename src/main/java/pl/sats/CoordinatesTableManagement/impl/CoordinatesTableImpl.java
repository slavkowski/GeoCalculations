package pl.sats.CoordinatesTableManagement.impl;

import pl.sats.CoordinatesTableManagement.CoordinateOrder;
import pl.sats.CoordinatesTableManagement.CoordinatesTable;
import pl.sats.CoordinatesTableManagement.CoordinatesTableManagementException;
import pl.sats.FieldObservationsObjects.PointCoordinates.Point;

import java.io.Serializable;
import java.util.*;

public class CoordinatesTableImpl<T extends Point> implements CoordinatesTable, Serializable {
    private Map<String, T> listOfCoordinatesUnordered = new HashMap<>();
    private List<T> listOfCoordinatesOrdered = new ArrayList<>();

    private static final long serialVersionUID = 1L;
    private CoordinateOrder coordinateOrder;

    public CoordinatesTableImpl(CoordinateOrder coordinateOrder) {
        this.coordinateOrder = coordinateOrder;
    }

    @Override
    public void addCoordinate(Object o) throws CoordinatesTableManagementException {
        if (o instanceof Point) {
            switch (coordinateOrder) {
                case UNORDERED:
                    String pointIdU = ((Point) o).getId();
                    if (listOfCoordinatesUnordered.containsKey(pointIdU)) {
                        throw new CoordinatesTableManagementException("Table already contains point nr: " + pointIdU);
                    }
                    listOfCoordinatesUnordered.put(pointIdU, (T) o);
                    break;
                case ORDERED:
                    boolean alreadyInList = false;
                    String pointIdO = ((Point) o).getId();
                    for (T o1 : listOfCoordinatesOrdered) {
                        alreadyInList = pointIdO.equals(o1.getId());
                    }
                    if (alreadyInList) {
                        throw new CoordinatesTableManagementException("Table already contains point nr: ");
                    } else {
                        listOfCoordinatesOrdered.add((T) o);
                    }
                    break;
            }
        }
    }

    @Override
    public void addSetOfCoordinates(Set t) throws CoordinatesTableManagementException {
        boolean isPointDuplicated = false;
        StringBuilder sb = new StringBuilder();
        for (Object o : t) {
            if (o instanceof Point) {
                String pointId = ((Point) o).getId();
                if (listOfCoordinatesUnordered.containsKey(pointId)) {
                    sb.append(" ").append(pointId).append(",");
                    isPointDuplicated = true;
                } else {
                    listOfCoordinatesUnordered.put(pointId, (T) o);
                }
            }
        }
        if (isPointDuplicated) {
            throw new CoordinatesTableManagementException("Table already contains points: " + sb.toString());
        }
    }

    @Override
    public void addListOfCoordinates(List t) throws CoordinatesTableManagementException {
        boolean isPointDuplicated = false;
        StringBuilder sb = new StringBuilder();
        for (Object o : t) {
            if (o instanceof Point) {
                boolean alreadyInList = false;
                String pointIdO = ((Point) o).getId();
                for (T o1 : listOfCoordinatesOrdered) {
                    if(pointIdO.equals(o1.getId())){
                        alreadyInList = true;
                    }
                }
                if (alreadyInList) {
                    sb.append(" ").append(pointIdO).append(",");
                    isPointDuplicated = true;
                } else {
                    listOfCoordinatesOrdered.add((T) o);
                }
            }
        }
        if (isPointDuplicated) {
            throw new CoordinatesTableManagementException("Table already contains points: " + sb.toString());
        }
    }

    @Override
    public void deleteCoordinate(String id) {
        switch (coordinateOrder) {
            case UNORDERED:
                listOfCoordinatesUnordered.remove(id);
                break;
            case ORDERED:
                boolean ifPointToDeleteExists = false;
                int idToDelete = 0;
                int i = 0;
                for (T o1 : listOfCoordinatesOrdered) {
                    if (o1.getId().equals(id)) {
                        ifPointToDeleteExists = true;
                        idToDelete = i;
                    }
                    i++;
                }
                if (ifPointToDeleteExists){
                    listOfCoordinatesOrdered.remove(idToDelete);
                }
                break;
        }
    }

    @Override
    public void deleteSetOfCoordinates(Set ids) {
        for (Object id : ids) {
            String pointId = (String) id;
            deleteCoordinate(pointId);
        }
    }

    public Map<String, T> getListOfUnorderedCoordinates() {
        return listOfCoordinatesUnordered;
    }

    public List<T> getListOfOrderedCoordinates() {
        return listOfCoordinatesOrdered;
    }
}
