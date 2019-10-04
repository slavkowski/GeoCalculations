package pl.sats.CoordinatesTableManagement;

import java.util.Set;

public interface CoordinatesTable<T> {
    void addCoordinate(T t) throws CoordinatesTableManagementException;
    void addListOfCoordinate(Set<T> t) throws CoordinatesTableManagementException;
    void deleteCoordinate(String id);
    void deleteListOfCoordinate(Set<String> ids);
}
