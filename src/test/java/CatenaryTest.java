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
    private ClassLoader loader = Main.class.getClassLoader();
    private File file = new File(Objects.requireNonNull(loader.getResource("TxtFiles/Field1.txt")).getFile());
    private FileUtils fileUtils = new FileUtils();

//    var a = 226.791149971774;
//    var l = -42.2493922571074;
//    var h = 95.855569803779;
//    var length = 89.21;
//    var minH = 130.93170679362677;
//    var LminH = 42.24438073207841;
//    var firstL = 0.0;
//    var firstH = 134.879;
//    var lastL = 89.21;
//    var lastH = 135.817;
//    var realPoints = [[0.0, 134.879], [17.51, 132.292], [63.98, 131.973], [89.21, 135.817]];
//    var maxHeight = 135.817;
//    var minHeight = 130.931;

    @Test
    public void shouldReturnCatenaryParameters() throws IOException, MatrixDegenerateException, MatrixWrongSizeException {
        List<LDH> fieldLdhObservations = fileUtils.readLdhFile(file);
        Catenary catenary = new Catenary(fieldLdhObservations);
        catenary.calculateCatenary();

        Assert.assertEquals(1.0,catenary.getA(),0.00000000001);
    }
}
