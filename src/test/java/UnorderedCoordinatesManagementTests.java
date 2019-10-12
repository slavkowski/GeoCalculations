import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.sats.CoordinatesTableManagement.CoordinateOrder;
import pl.sats.CoordinatesTableManagement.CoordinatesTableManagementException;
import pl.sats.CoordinatesTableManagement.impl.CoordinatesTableImpl;
import pl.sats.FieldObservationsObjects.PointCoordinates.XYZ;

import java.util.HashSet;
import java.util.Set;

class UnorderedCoordinatesManagementTests {

    private CoordinatesTableImpl<XYZ> xyzTable = new CoordinatesTableImpl<>(CoordinateOrder.UNORDERED);


    @Test
    void shouldAddOneCoordinate() throws CoordinatesTableManagementException {
        xyzTable.addCoordinate(new XYZ("1", 1.1, 2.2, 3.3));
        Assertions.assertEquals(1, xyzTable.getListOfUnorderedCoordinates().size());
        Assertions.assertEquals(1.1, xyzTable.getListOfUnorderedCoordinates().get("1").getX());
        Assertions.assertEquals(2.2, xyzTable.getListOfUnorderedCoordinates().get("1").getY());
        Assertions.assertEquals(3.3, xyzTable.getListOfUnorderedCoordinates().get("1").getZ());
    }

    @Test
    void shouldAddSetOfCoordinates() throws CoordinatesTableManagementException {
        XYZ xyz1 = new XYZ("1", 1.0, 1.0, 1.0);
        XYZ xyz2 = new XYZ("2", 2.0, 1.0, 1.0);
        XYZ xyz3 = new XYZ("3", 3.0, 1.0, 1.0);
        Set<XYZ> set = new HashSet<>();
        set.add(xyz1);
        set.add(xyz2);
        set.add(xyz3);
        xyzTable.addSetOfCoordinates(set);
        Assertions.assertEquals(3, xyzTable.getListOfUnorderedCoordinates().size());
        Assertions.assertEquals(1.0, xyzTable.getListOfUnorderedCoordinates().get("1").getX());
        Assertions.assertEquals(2.0, xyzTable.getListOfUnorderedCoordinates().get("2").getX());
        Assertions.assertEquals(3.0, xyzTable.getListOfUnorderedCoordinates().get("3").getX());
    }
    @Test
    void shouldDeleteCoordinate() throws CoordinatesTableManagementException {
        XYZ xyz1 = new XYZ("1", 1.0, 1.0, 1.0);
        XYZ xyz2 = new XYZ("2", 2.0, 1.0, 1.0);
        XYZ xyz3 = new XYZ("3", 3.0, 1.0, 1.0);
        Set<XYZ> set = new HashSet<>();
        set.add(xyz1);
        set.add(xyz2);
        set.add(xyz3);
        xyzTable.addSetOfCoordinates(set);
        xyzTable.deleteCoordinate("1");
        xyzTable.deleteCoordinate("1");
        Assertions.assertEquals(2, xyzTable.getListOfUnorderedCoordinates().size());
        Assertions.assertNull(xyzTable.getListOfUnorderedCoordinates().get("1"));
    }
    @Test
    void shouldDeleteSetOfCoordinate() throws CoordinatesTableManagementException {
        XYZ xyz1 = new XYZ("1", 1.0, 1.0, 1.0);
        XYZ xyz2 = new XYZ("2", 2.0, 1.0, 1.0);
        XYZ xyz3 = new XYZ("3", 3.0, 1.0, 1.0);
        Set<XYZ> set = new HashSet<>();
        set.add(xyz1);
        set.add(xyz2);
        set.add(xyz3);
        xyzTable.addSetOfCoordinates(set);
        Set<String> setCoordinatesToDelete = new HashSet<>();
        setCoordinatesToDelete.add("1");
        setCoordinatesToDelete.add("2");
        xyzTable.deleteSetOfCoordinates(setCoordinatesToDelete);

        Assertions.assertEquals(1, xyzTable.getListOfUnorderedCoordinates().size());
        Assertions.assertNull(xyzTable.getListOfUnorderedCoordinates().get("1"));
        Assertions.assertNull(xyzTable.getListOfUnorderedCoordinates().get("2"));
    }
    @Test
    void shouldAddSetOfDuplicatedCoordinates() throws CoordinatesTableManagementException {
        XYZ xyz1 = new XYZ("1", 1.0, 1.0, 1.0);
        XYZ xyz2 = new XYZ("2", 2.0, 1.0, 1.0);
        XYZ xyz3 = new XYZ("3", 3.0, 1.0, 1.0);
        Set<XYZ> set = new HashSet<>();
        set.add(xyz1);
        set.add(xyz2);
        set.add(xyz3);
        xyzTable.addSetOfCoordinates(set);

        XYZ xyz4 = new XYZ("1", 4.0, 1.0, 1.0);
        XYZ xyz5 = new XYZ("2", 5.0, 1.0, 1.0);

        Set<XYZ> set2 = new HashSet<>();
        set2.add(xyz4);
        set2.add(xyz5);
        Assertions.assertThrows(CoordinatesTableManagementException.class, ()->{
            xyzTable.addSetOfCoordinates(set2);
        });
    }

}
