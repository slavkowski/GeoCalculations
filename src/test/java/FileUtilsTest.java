import org.junit.jupiter.api.Test;
import pl.sats.FieldObservationsObjects.PointCoordinates.*;
import pl.sats.FileUtils.FileUtils;
import pl.sats.Main;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileUtilsTest {
    private ClassLoader loader = Main.class.getClassLoader();

    @Test
    void shouldReturnXYZObject() throws IOException {
        File fileXYZ = new File(Objects.requireNonNull(loader.getResource("TxtFiles/TestXYZ.txt")).getFile());
        FileUtils<XYZ> fileUtils = new FileUtils<>(PointType.XYZ, new XYZ());
        List<XYZ> list = fileUtils.readFile(fileXYZ);
        assertEquals(4, list.size());
        assertEquals("1", list.get(0).getId());
        assertEquals(0.0, list.get(0).getX());
        assertEquals(1.1, list.get(0).getY());
        assertEquals(2.2, list.get(0).getZ());
        assertEquals("4", list.get(3).getId());
        assertEquals(6.6, list.get(3).getX());
        assertEquals(7.7, list.get(3).getY());
        assertEquals(8.8, list.get(3).getZ());
    }
    @Test
    void shouldReturnXYZEObject() throws IOException {
        File fileXYZ = new File(Objects.requireNonNull(loader.getResource("TxtFiles/TestXYZE.txt")).getFile());
        FileUtils<XYZ> fileUtils = new FileUtils<>(PointType.XYZE, new XYZ());
        List<XYZ> list = fileUtils.readFile(fileXYZ);
        assertEquals(2, list.size());
        assertEquals("1", list.get(0).getId());
        assertEquals(0.0, list.get(0).getX());
        assertEquals(1.1, list.get(0).getY());
        assertEquals(2.2, list.get(0).getZ());
        assertEquals("2", list.get(1).getId());
        assertEquals(2.2, list.get(1).getX());
        assertEquals(0.2, list.get(1).getmX());
        assertEquals(3.3, list.get(1).getY());
        assertEquals(0.3, list.get(1).getmY());
        assertEquals(4.4, list.get(1).getZ());
        assertEquals(0.4, list.get(1).getmZ());
    }

    @Test
    void shouldReturnLhObject() throws IOException {
        FileUtils<LHD> fileUtils = new FileUtils<>(PointType.LH, new LHD());
        File file = new File(Objects.requireNonNull(loader.getResource("TxtFiles/LH.txt")).getFile());
        List<LHD> fieldLHDObservations = fileUtils.readFile(file);
        assertEquals(4, fieldLHDObservations.size());
        assertEquals("1", fieldLHDObservations.get(0).getId());
        assertEquals(0.0, fieldLHDObservations.get(0).getL(), 0.00001);
        assertEquals(134.879, fieldLHDObservations.get(0).getH(), 0.00001);
    }
    @Test
    void shouldReturnLheObject() throws IOException {
        FileUtils<LHD> fileUtils = new FileUtils<>(PointType.LHE, new LHD());
        File file = new File(Objects.requireNonNull(loader.getResource("TxtFiles/LHE.txt")).getFile());
        List<LHD> fieldLHDObservations = fileUtils.readFile(file);
        assertEquals(2, fieldLHDObservations.size());
        assertEquals("1", fieldLHDObservations.get(0).getId());
        assertEquals(0.0, fieldLHDObservations.get(0).getL(), 0.00001);
        assertEquals(0.1, fieldLHDObservations.get(0).getmL(), 0.00001);
        assertEquals(134.879, fieldLHDObservations.get(0).getH(), 0.00001);
        assertEquals(0.2, fieldLHDObservations.get(0).getmH(), 0.00001);
    }
    @Test
    void shouldReturnLhdObject() throws IOException {
        FileUtils<LHD> fileUtils = new FileUtils<>(PointType.LHD, new LHD());
        File file = new File(Objects.requireNonNull(loader.getResource("TxtFiles/LHD.txt")).getFile());
        List<LHD> fieldLHDObservations = fileUtils.readFile(file);
        assertEquals(2, fieldLHDObservations.size());
        assertEquals("1", fieldLHDObservations.get(0).getId());
        assertEquals(0.0, fieldLHDObservations.get(0).getL(), 0.00001);
        assertEquals(134.879, fieldLHDObservations.get(0).getH(), 0.00001);
        assertEquals(1.11, fieldLHDObservations.get(0).getD(), 0.00001);
        assertEquals(-2.22, fieldLHDObservations.get(1).getD(), 0.00001);
    }
    @Test
    void shouldReturnLhdeObject() throws IOException {
        FileUtils<LHD> fileUtils = new FileUtils<>(PointType.LHDE, new LHD());
        File file = new File(Objects.requireNonNull(loader.getResource("TxtFiles/LHDE.txt")).getFile());
        List<LHD> fieldLHDObservations = fileUtils.readFile(file);
        assertEquals(2, fieldLHDObservations.size());
        assertEquals("1", fieldLHDObservations.get(0).getId());
        assertEquals(0.0, fieldLHDObservations.get(0).getL(), 0.00001);
        assertEquals(0.5, fieldLHDObservations.get(0).getmL(), 0.00001);
        assertEquals(134.879, fieldLHDObservations.get(0).getH(), 0.00001);
        assertEquals(0.6, fieldLHDObservations.get(0).getmH(), 0.00001);
        assertEquals(1.11, fieldLHDObservations.get(0).getD(), 0.00001);
        assertEquals(0.7, fieldLHDObservations.get(0).getmD(), 0.00001);
        assertEquals(-2.22, fieldLHDObservations.get(1).getD(), 0.00001);
        assertEquals(0.1, fieldLHDObservations.get(1).getmD(), 0.00001);
    }
    @Test
    void shouldReturnBLHObject() throws IOException {
        File fileBLH = new File(Objects.requireNonNull(loader.getResource("TxtFiles/BLH.txt")).getFile());
        FileUtils<BLH> fileUtils = new FileUtils<>(PointType.BLH, new BLH());
        List<BLH> list = fileUtils.readFile(fileBLH);
        assertEquals(4, list.size());
        assertEquals("1", list.get(0).getId());
        assertEquals(0.0, list.get(0).getLatitude());
        assertEquals(1.1, list.get(0).getLongitude());
        assertEquals(2.2, list.get(0).getEllipsoidHeight());
        assertEquals("4", list.get(3).getId());
        assertEquals(6.6, list.get(3).getLatitude());
        assertEquals(7.7, list.get(3).getLongitude());
        assertEquals(8.8, list.get(3).getEllipsoidHeight());
    }
    @Test
    void shouldReturnBLHEObject() throws IOException {
        File fileBLH = new File(Objects.requireNonNull(loader.getResource("TxtFiles/BLHE.txt")).getFile());
        FileUtils<BLH> fileUtils = new FileUtils<>(PointType.BLHE, new BLH());
        List<BLH> list = fileUtils.readFile(fileBLH);
        assertEquals(2, list.size());
        assertEquals("1", list.get(0).getId());
        assertEquals(0.0, list.get(0).getLatitude());
        assertEquals(1.1, list.get(0).getLongitude());
        assertEquals(2.2, list.get(0).getEllipsoidHeight());
        assertEquals("2", list.get(1).getId());
        assertEquals(2.2, list.get(1).getLatitude());
        assertEquals(0.2, list.get(1).getmLatitude());
        assertEquals(3.3, list.get(1).getLongitude());
        assertEquals(0.3, list.get(1).getmLongitude());
        assertEquals(4.4, list.get(1).getEllipsoidHeight());
        assertEquals(0.4, list.get(1).getmEllipsoidHeight());
    }
}
