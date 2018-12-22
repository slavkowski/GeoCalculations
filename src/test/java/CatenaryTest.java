import org.junit.Assert;
import org.junit.Test;
import pl.sats.CurveCalculations.Catenary;
import pl.sats.Exceptions.MatrixDegenerateException;
import pl.sats.Exceptions.MatrixWrongSizeException;
import pl.sats.FieldObservationsObjects.LDH;
import pl.sats.FileUtils;
import pl.sats.Main;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class CatenaryTest {
    private ClassLoader loader = Catenary.class.getClassLoader();
    private File file = new File(Objects.requireNonNull(loader.getResource("TxtFiles/Field1.txt")).getFile());
    private FileUtils fileUtils = new FileUtils();

    @Test
    public void shouldReturnCatenaryParameters() throws IOException, MatrixDegenerateException, MatrixWrongSizeException {
        List<LDH> fieldLdhObservations = fileUtils.readLdhFile(file);
        Catenary catenary = new Catenary(fieldLdhObservations);
        catenary.calculateCatenary();

        Assert.assertEquals(226.791149971774, catenary.getA(), 0.00000000001);
        Assert.assertEquals(-42.2493922571074, catenary.getL(), 0.00000000001);
        Assert.assertEquals(95.855569803779, catenary.getH(), 0.00000000001);
        Assert.assertEquals(130.93170679362677, catenary.getMinH(), 0.00000000001);
        Assert.assertEquals(42.24438073207841, catenary.getLminH(), 0.00000000001);
        Assert.assertEquals(89.21, catenary.getHorizontalLength(), 0.00000000001);
        Assert.assertEquals(0.0, catenary.getFirstL(), 0.00000000001);
        Assert.assertEquals(134.879, catenary.getFirstH(), 0.00000000001);
        Assert.assertEquals(89.21, catenary.getLastL(), 0.00000000001);
        Assert.assertEquals(135.817, catenary.getLastH(), 0.00000000001);
        Assert.assertEquals(4, catenary.getFieldObservations().size());
        Assert.assertEquals(135.817, catenary.getMaxHeight(), 0.00000000001);
        Assert.assertEquals(130.931, catenary.getMinHeight(), 0.001);
    }
}
