import org.junit.Assert;
import org.junit.Test;
import pl.sats.FieldObservationsObjects.DeltaHeight;
import pl.sats.FieldObservationsObjects.PointNEH;
import pl.sats.FileUtils;
import pl.sats.GeodeticNetworkAdjustment.VerticalAdjustment;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class VerticalAdjustmentTest {
    private ClassLoader loader = VerticalAdjustment.class.getClassLoader();
    private File file1 = new File(Objects.requireNonNull(loader.getResource("TxtFiles/VerticalTxtFiles/ex5_1_1/FixedPoints.txt")).getFile());
    private File file2 = new File(Objects.requireNonNull(loader.getResource("TxtFiles/VerticalTxtFiles/ex5_1_1/HeightDifferenceObservations.txt")).getFile());
    private FileUtils fileUtils = new FileUtils();
    private List<PointNEH> fixedPoints = fileUtils.readNehFile(file1);
    private List<DeltaHeight> verticalObservations = fileUtils.readLevelingObservations(file2);


    private VerticalAdjustment verticalAdjustment = new VerticalAdjustment(fixedPoints, verticalObservations);


    public VerticalAdjustmentTest() throws IOException {
    }
    @Test
    public void shouldReturnNumberOfFixedPoints(){
        Assert.assertEquals(2,fixedPoints.size());
    }
    @Test
    public void shouldReturnNumberOfObservations(){
        Assert.assertEquals(5,verticalObservations.size());
    }

//    @Test
//    public void shouldReturnCorrectM0(){
//        verticalAdjustment.proceedAdjustment();
//        Assert.assertEquals(0.98, verticalAdjustment.getM0(),0.01 );
//    }
//    @Test
//    public void shouldReturnUnknownParameters(){
//        Assert.assertEquals("102.510", verticalAdjustment.getResults()[0][0]);
//    }
}
