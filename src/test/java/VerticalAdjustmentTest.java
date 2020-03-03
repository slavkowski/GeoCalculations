import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.sgeonet.Exceptions.DuplicatedFixedPionts;
import pl.sgeonet.FieldObservationsObjects.FieldObservation.DeltaHeight;
import pl.sgeonet.FieldObservationsObjects.PointCoordinates.NEH;
import pl.sgeonet.FieldObservationsObjects.PointCoordinates.PointType;
import pl.sgeonet.FileUtils.FileUtils;
import pl.sgeonet.GeodeticNetworkAdjustment.VerticalAdjustment;
import pl.sgeonet.GeodeticNetworkAdjustment.VerticalAdjustmentInitialSetup;
import pl.sgeonet.GeodeticNetworkAdjustment.VerticalAdjustmentMethod;
import pl.sgeonet.RaportConfiguration.Unit;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VerticalAdjustmentTest {
    //1
    VerticalAdjustmentMethod method1 = VerticalAdjustmentMethod.STANDARD;
    private ClassLoader loader = VerticalAdjustment.class.getClassLoader();
    private File file1 = new File(Objects.requireNonNull(loader.getResource("TxtFiles/VerticalTxtFiles/ex5_1_1/FixedPoints.txt")).getFile());
    private File file2 = new File(Objects.requireNonNull(loader.getResource("TxtFiles/VerticalTxtFiles/ex5_1_1/HeightDifferenceObservations.txt")).getFile());
    private FileUtils<NEH> fileUtils = new FileUtils<>(PointType.H, new NEH());
    private List<NEH> fixedPoints = fileUtils.readFile(file1);
    private List<DeltaHeight> verticalObservations = fileUtils.readLevelingObservations(file2, method1);

    VerticalAdjustmentInitialSetup verticalAdjustmentInitialSetup = new VerticalAdjustmentInitialSetup(method1, Unit.M, Unit.M, Unit.M);
    private VerticalAdjustment verticalAdjustment = new VerticalAdjustment(fixedPoints, verticalObservations,verticalAdjustmentInitialSetup);

    //2
    VerticalAdjustmentMethod method2 = VerticalAdjustmentMethod.STANDARD;
    private File file3 = new File(Objects.requireNonNull(loader.getResource("TxtFiles/VerticalTxtFiles/P1/2.1/FixedPoints.txt")).getFile());
    private File file4 = new File(Objects.requireNonNull(loader.getResource("TxtFiles/VerticalTxtFiles/P1/2.1/HeightDifferenceObservations.txt")).getFile());
    private FileUtils<NEH> fileUtils2 = new FileUtils<>(PointType.H, new NEH());
    private List<NEH> fixedPoints2 = fileUtils.readFile(file3);
    private List<DeltaHeight> verticalObservations2 = fileUtils2.readLevelingObservations(file4, method2);

    VerticalAdjustmentInitialSetup verticalAdjustmentInitialSetup2 = new VerticalAdjustmentInitialSetup(method2, Unit.M, Unit.M, Unit.CM);
    private VerticalAdjustment verticalAdjustment2 = new VerticalAdjustment(fixedPoints2, verticalObservations2,verticalAdjustmentInitialSetup2);

    //3
    VerticalAdjustmentMethod method3 = VerticalAdjustmentMethod.STANDARD;
    private File file5 = new File(Objects.requireNonNull(loader.getResource("TxtFiles/VerticalTxtFiles/Exception/FixedPoints.txt")).getFile());
    private File file6 = new File(Objects.requireNonNull(loader.getResource("TxtFiles/VerticalTxtFiles/Exception/HeightDifferenceObservations.txt")).getFile());
    private FileUtils<NEH> fileUtils3 = new FileUtils<>(PointType.H, new NEH());
    private List<NEH> fixedPoints3 = fileUtils.readFile(file5);
    private List<DeltaHeight> verticalObservations3 = fileUtils3.readLevelingObservations(file6, method3);

    VerticalAdjustmentInitialSetup verticalAdjustmentInitialSetup3 = new VerticalAdjustmentInitialSetup(method3, Unit.M, Unit.M, Unit.M);
    private VerticalAdjustment verticalAdjustment3 = new VerticalAdjustment(fixedPoints3, verticalObservations3,verticalAdjustmentInitialSetup3);


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
