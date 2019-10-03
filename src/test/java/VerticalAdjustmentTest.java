import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.sats.Exceptions.DuplicatedFixedPionts;
import pl.sats.FieldObservationsObjects.DeltaHeight;
import pl.sats.FieldObservationsObjects.PointCoordinates.NEH;
import pl.sats.FileUtils.FileUtils;
import pl.sats.GeodeticNetworkAdjustment.VerticalAdjustment;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VerticalAdjustmentTest {
    private ClassLoader loader = VerticalAdjustment.class.getClassLoader();
    private File file1 = new File(Objects.requireNonNull(loader.getResource("TxtFiles/VerticalTxtFiles/ex5_1_1/FixedPoints.txt")).getFile());
    private File file2 = new File(Objects.requireNonNull(loader.getResource("TxtFiles/VerticalTxtFiles/ex5_1_1/HeightDifferenceObservations.txt")).getFile());

    private File file3 = new File(Objects.requireNonNull(loader.getResource("TxtFiles/VerticalTxtFiles/P1/2.1/FixedPoints.txt")).getFile());
    private File file4 = new File(Objects.requireNonNull(loader.getResource("TxtFiles/VerticalTxtFiles/P1/2.1/HeightDifferenceObservations.txt")).getFile());

    private File file5 = new File(Objects.requireNonNull(loader.getResource("TxtFiles/VerticalTxtFiles/Exception/FixedPoints.txt")).getFile());
    private File file6 = new File(Objects.requireNonNull(loader.getResource("TxtFiles/VerticalTxtFiles/Exception/HeightDifferenceObservations.txt")).getFile());

    private FileUtils fileUtils = new FileUtils();
    private List<NEH> fixedPoints = fileUtils.readNehFile(file1);
    private List<DeltaHeight> verticalObservations = fileUtils.readLevelingObservationsWithStdErrors(file2);

    private FileUtils fileUtils2 = new FileUtils();
    private List<NEH> fixedPoints2 = fileUtils2.readNehFile(file3);
    private List<DeltaHeight> verticalObservations2 = fileUtils2.readLevelingObservationsWithStdErrors(file4);

    private FileUtils fileUtils3 = new FileUtils();
    private List<NEH> fixedPoints3 = fileUtils3.readNehFile(file5);
    private List<DeltaHeight> verticalObservations3 = fileUtils3.readLevelingObservationsWithStdErrors(file6);

    private VerticalAdjustment verticalAdjustment = new VerticalAdjustment(fixedPoints, verticalObservations,1.0);
    private VerticalAdjustment verticalAdjustment2 = new VerticalAdjustment(fixedPoints2, verticalObservations2,0.01);
    private VerticalAdjustment verticalAdjustment3 = new VerticalAdjustment(fixedPoints3, verticalObservations3,1.0);


    VerticalAdjustmentTest() throws IOException {
    }
    @Test
    void shouldReturnNumberOfFixedPoints(){
        assertEquals(3,fixedPoints.size());
    }
    @Test
    void shouldReturnNumberOfObservations(){
        assertEquals(5,verticalObservations.size());
    }
    @Test
    void shouldReturnValidationOfData() throws DuplicatedFixedPionts {
        verticalAdjustment.proceedAdjustment();
    }
    @Test
    void shouldReturnValidationOfData2() throws DuplicatedFixedPionts {
        verticalAdjustment2.proceedAdjustment();
    }
    @Test
    void shouldReturnValidationOfData3(){
        Assertions.assertThrows(DuplicatedFixedPionts.class, ()->{
            verticalAdjustment3.proceedAdjustment();
        });
    }
}
