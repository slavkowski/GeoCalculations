import org.junit.Assert;
import org.junit.Test;
import pl.sats.FieldObservationsObjects.PointLDH;
import pl.sats.FileUtils;
import pl.sats.Main;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class FileUtilsTest {
    private ClassLoader loader = Main.class.getClassLoader();
    private File file = new File(Objects.requireNonNull(loader.getResource("TxtFiles/Field1.txt")).getFile());
    private FileUtils fileUtils = new FileUtils(file);

    @Test
    public void shouldReturnLdhObject() throws IOException {
        fileUtils.readFile();
        Assert.assertEquals(4,fileUtils,eldObservations.size());
        Assert.assertEquals("1",fieldObservations.get(0).getName());
        Assert.assertEquals(0.0,fieldObservations.get(0).getL(),0.00001);
        Assert.assertEquals(134.879,fieldObservations.get(0).getH(), 0.00001);
    }
}
