import org.junit.jupiter.api.Test;
import pl.sats.FieldObservationsObjects.PointLDH;
import pl.sats.FileUtils.FileUtils;
import pl.sats.Main;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileUtilsTest {
    private ClassLoader loader = Main.class.getClassLoader();
    private File file = new File(Objects.requireNonNull(loader.getResource("TxtFiles/Field1.txt")).getFile());
    private FileUtils fileUtils = new FileUtils();

    @Test
    void shouldReturnLdhObject() throws IOException {
        List<PointLDH> fieldLdhObservations = fileUtils.readLdhFile(file);
        assertEquals(4,fieldLdhObservations.size());
        assertEquals("1",fieldLdhObservations.get(0).getName());
        assertEquals(0.0,fieldLdhObservations.get(0).getL(),0.00001);
        assertEquals(134.879,fieldLdhObservations.get(0).getH(), 0.00001);
    }
}
