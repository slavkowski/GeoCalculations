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
import pl.sgeonet.RaportConfiguration.PrintSettings;
import pl.sgeonet.RaportConfiguration.Unit;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VerticalAdjustmentTest {
    private final ClassLoader loader = VerticalAdjustment.class.getClassLoader();
    PrintSettings printSettings = new PrintSettings();

    //1
    VerticalAdjustmentMethod method1 = VerticalAdjustmentMethod.STANDARD;
    private final File file1 = new File(Objects.requireNonNull(loader.getResource("TxtFiles/VerticalTxtFiles/ex5_1_1/FixedPoints.txt")).getFile());
    private final File file2 = new File(Objects.requireNonNull(loader.getResource("TxtFiles/VerticalTxtFiles/ex5_1_1/HeightDifferenceObservations.txt")).getFile());
    private final FileUtils<NEH> fileUtils = new FileUtils<>(PointType.H, new NEH());
    private final List<NEH> fixedPoints = fileUtils.readFile(file1, null);
    private final List<DeltaHeight> verticalObservations = fileUtils.readLevelingObservations(file2,null, method1);

    VerticalAdjustmentInitialSetup verticalAdjustmentInitialSetup = new VerticalAdjustmentInitialSetup(method1, Unit.M, Unit.M, Unit.M);
    private final VerticalAdjustment verticalAdjustment = new VerticalAdjustment(file1, file2, verticalAdjustmentInitialSetup, printSettings);

    //2
    VerticalAdjustmentMethod method2 = VerticalAdjustmentMethod.STANDARD;
    private final File file3 = new File(Objects.requireNonNull(loader.getResource("TxtFiles/VerticalTxtFiles/P1/2.1/FixedPoints.txt")).getFile());
    private final File file4 = new File(Objects.requireNonNull(loader.getResource("TxtFiles/VerticalTxtFiles/P1/2.1/HeightDifferenceObservations.txt")).getFile());

    VerticalAdjustmentInitialSetup verticalAdjustmentInitialSetup2 = new VerticalAdjustmentInitialSetup(method2, Unit.M, Unit.M, Unit.CM);
    private final VerticalAdjustment verticalAdjustment2 = new VerticalAdjustment(file3, file4, verticalAdjustmentInitialSetup2, printSettings);

    //3
    VerticalAdjustmentMethod method3 = VerticalAdjustmentMethod.STANDARD;
    private final File file5 = new File(Objects.requireNonNull(loader.getResource("TxtFiles/VerticalTxtFiles/Exception/FixedPoints.txt")).getFile());
    private final File file6 = new File(Objects.requireNonNull(loader.getResource("TxtFiles/VerticalTxtFiles/Exception/HeightDifferenceObservations.txt")).getFile());

    VerticalAdjustmentInitialSetup verticalAdjustmentInitialSetup3 = new VerticalAdjustmentInitialSetup(method3, Unit.M, Unit.M, Unit.M);
    private final VerticalAdjustment verticalAdjustment3 = new VerticalAdjustment(file5, file6, verticalAdjustmentInitialSetup3, printSettings);

    //4
    VerticalAdjustmentMethod method4 = VerticalAdjustmentMethod.WITH_LENGTH_OF_SECTION;
    private final File file7 = new File(Objects.requireNonNull(loader.getResource("TxtFiles/VerticalTxtFiles/G1/10.5.1/FixedPoints.txt")).getFile());
    private final File file8 = new File(Objects.requireNonNull(loader.getResource("TxtFiles/VerticalTxtFiles/G1/10.5.1/HeightDifferenceObservations.txt")).getFile());

    VerticalAdjustmentInitialSetup verticalAdjustmentInitialSetup4 = new VerticalAdjustmentInitialSetup(method4, 4.46, Unit.M, Unit.M, Unit.MM);
    private final VerticalAdjustment verticalAdjustment4 = new VerticalAdjustment(file7, file8, verticalAdjustmentInitialSetup4, printSettings);


    VerticalAdjustmentTest() throws IOException {
    }

    @Test
    void shouldReturnNumberOfFixedPoints() {
        assertEquals(3, fixedPoints.size());
    }

    @Test
    void shouldReturnNumberOfObservations() {
        assertEquals(5, verticalObservations.size());
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
    void shouldReturnValidationOfData3() {
        Assertions.assertThrows(DuplicatedFixedPionts.class, verticalAdjustment3::proceedAdjustment);
    }

    @Test
    void shouldReturnValidationOfData4() throws DuplicatedFixedPionts {
        verticalAdjustment4.proceedAdjustment();
    }
}
