package pl.sats.CoordinatesTableManagement;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CoordinatesTable<T> {
    void addCoordinate(T t) throws CoordinatesTableManagementException;
    void addSetOfCoordinates(Set<T> t) throws CoordinatesTableManagementException;
    void addListOfCoordinates(List<T> t) throws CoordinatesTableManagementException;
    void deleteCoordinate(String id);
    void deleteSetOfCoordinates(Set<String> ids);
    Map<String, T> getListOfUnorderedCoordinates();
    List<T> getListOfOrderedCoordinates();
}
