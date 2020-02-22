import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.sgeonet.CoordinatesTableManagement.CoordinateOrder;
import pl.sgeonet.CoordinatesTableManagement.CoordinatesTableManagementException;
import pl.sgeonet.CoordinatesTableManagement.impl.CoordinatesTableImpl;
import pl.sgeonet.FieldObservationsObjects.PointCoordinates.XYZ;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class OrderedCoordinatesManagementTests {

    private CoordinatesTableImpl<XYZ> xyzTable = new CoordinatesTableImpl<>(CoordinateOrder.ORDERED);

    @Test
    void shouldAddOneCoordinate() throws CoordinatesTableManagementException {
        xyzTable.addCoordinate(new XYZ("1", 1.1, 2.2, 3.3));
        Assertions.assertEquals(1, xyzTable.getListOfOrderedCoordinates().size());
        Assertions.assertEquals(1.1, xyzTable.getListOfOrderedCoordinates().get(0).getX());
        Assertions.assertEquals(2.2, xyzTable.getListOfOrderedCoordinates().get(0).getY());
        Assertions.assertEquals(3.3, xyzTable.getListOfOrderedCoordinates().get(0).getZ());
    }
    @Test
    void shouldAddListOfCoordinates() throws CoordinatesTableManagementException {
        XYZ xyz1 = new XYZ("1", 1.0, 1.0, 1.0);
        XYZ xyz2 = new XYZ("2", 2.0, 1.0, 1.0);
        XYZ xyz3 = new XYZ("3", 3.0, 1.0, 1.0);
        List<XYZ> list = new ArrayList<>();
        list.add(xyz1);
        list.add(xyz2);
        list.add(xyz3);
        xyzTable.addListOfCoordinates(list);
        Assertions.assertEquals(3, xyzTable.getListOfOrderedCoordinates().size());
        Assertions.assertEquals(1.0, xyzTable.getListOfOrderedCoordinates().get(0).getX());
        Assertions.assertEquals(2.0, xyzTable.getListOfOrderedCoordinates().get(1).getX());
        Assertions.assertEquals(3.0, xyzTable.getListOfOrderedCoordinates().get(2).getX());
    }
    @Test
    void shouldDeleteCoordinate() throws CoordinatesTableManagementException {
        XYZ xyz1 = new XYZ("1", 1.0, 1.0, 1.0);
        XYZ xyz2 = new XYZ("2", 2.0, 1.0, 1.0);
        XYZ xyz3 = new XYZ("3", 3.0, 1.0, 1.0);
        List<XYZ> list = new ArrayList<>();
        list.add(xyz1);
        list.add(xyz2);
        list.add(xyz3);
        xyzTable.addListOfCoordinates(list);
        xyzTable.deleteCoordinate("1");
        Assertions.assertEquals(2, xyzTable.getListOfOrderedCoordinates().size());
        xyzTable.deleteCoordinate("5");
        Assertions.assertEquals(2, xyzTable.getListOfOrderedCoordinates().size());
    }
    @Test
    void shouldDeleteSetOfCoordinate() throws CoordinatesTableManagementException {
        XYZ xyz1 = new XYZ("1", 1.0, 1.0, 1.0);
        XYZ xyz2 = new XYZ("2", 2.0, 1.0, 1.0);
        XYZ xyz3 = new XYZ("3", 3.0, 1.0, 1.0);
        List<XYZ> list = new ArrayList<>();
        list.add(xyz1);
        list.add(xyz2);
        list.add(xyz3);
        xyzTable.addListOfCoordinates(list);
        Set<String> setCoordinatesToDelete = new HashSet<>();
        setCoordinatesToDelete.add("1");
        setCoordinatesToDelete.add("2");
        xyzTable.deleteSetOfCoordinates(setCoordinatesToDelete);

        Assertions.assertEquals(1, xyzTable.getListOfOrderedCoordinates().size());
    }

    @Test
    void shouldAddSetOfDuplicatedCoordinates() throws CoordinatesTableManagementException {
        XYZ xyz1 = new XYZ("1", 1.0, 1.0, 1.0);
        XYZ xyz2 = new XYZ("2", 2.0, 1.0, 1.0);
        XYZ xyz3 = new XYZ("3", 3.0, 1.0, 1.0);
        List<XYZ> list = new ArrayList<>();
        list.add(xyz1);
        list.add(xyz2);
        list.add(xyz3);
        xyzTable.addListOfCoordinates(list);

        XYZ xyz4 = new XYZ("1", 4.0, 1.0, 1.0);
        XYZ xyz5 = new XYZ("2", 5.0, 1.0, 1.0);

        List<XYZ> list2 = new ArrayList<>();
        list2.add(xyz4);
        list2.add(xyz5);
        Assertions.assertThrows(CoordinatesTableManagementException.class, ()->{
            xyzTable.addListOfCoordinates(list2);
        });
    }
}
