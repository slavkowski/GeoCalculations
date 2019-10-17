import org.junit.jupiter.api.Test;
import pl.sats.FieldObservationsObjects.PointCoordinates.LHD;
import pl.sats.FieldObservationsObjects.PointCoordinates.NEH;
import pl.sats.FileUtils.FileUtils;
import pl.sats.Main;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileUtilsTest {
    private ClassLoader loader = Main.class.getClassLoader();
    private File file = new File(Objects.requireNonNull(loader.getResource("TxtFiles/Field1.txt")).getFile());
    private FileUtils fileUtils = new FileUtils();
    private FileUtils fileUtilsLH = new FileUtils<>(new NEH());

    @Test
    void shouldReturnLdhObject() throws IOException {
        List<LHD> fieldLHDObservations = fileUtils.readLdhFile(file);
        assertEquals(4, fieldLHDObservations.size());
        assertEquals("1", fieldLHDObservations.get(0).getId());
        assertEquals(0.0, fieldLHDObservations.get(0).getL(), 0.00001);
        assertEquals(134.879, fieldLHDObservations.get(0).getH(), 0.00001);
    }

    @Test
    void shouldReturnGenericObject() {
        assertTrue(fileUtilsLH.getT() instanceof NEH);
    }
}
