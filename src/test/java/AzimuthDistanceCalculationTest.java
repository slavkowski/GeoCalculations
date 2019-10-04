import org.junit.jupiter.api.Test;
import pl.sats.BasicGeoCalculations.AzimuthDistanceCalculation;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AzimuthDistanceCalculationTest {
    private AzimuthDistanceCalculation azimuthDistanceCalculation = new AzimuthDistanceCalculation();
    @Test
    void distanceTest(){
        assertEquals(0, azimuthDistanceCalculation.calculateDistance(0,0,0,0),0.0000001);
        assertEquals(141.4213562373, azimuthDistanceCalculation.calculateDistance(0,0,100.0,100.0),0.00000001);
    }
    @Test
    void azimuthTest(){
        assertEquals(Math.PI/4,azimuthDistanceCalculation.calculateAzimuth(1.0,1.0,2.0,2.0),0.00000001 );
        assertEquals(Math.PI*0.75,azimuthDistanceCalculation.calculateAzimuth(0,0,-1,1),0.00000001 );
        assertEquals(Math.PI*1.25,azimuthDistanceCalculation.calculateAzimuth(0,0,-1.0,-1.0),0.00000001 );
        assertEquals(Math.PI*1.75,azimuthDistanceCalculation.calculateAzimuth(0,0,1.0,-1.0),0.00000001 );
        assertEquals(0,azimuthDistanceCalculation.calculateAzimuth(0,0,0,0),0.00000001 );
        assertEquals(0,azimuthDistanceCalculation.calculateAzimuth(0,0,1,0),0.00000001 );
        assertEquals(Math.PI/2,azimuthDistanceCalculation.calculateAzimuth(0,0,0,1),0.00000001 );
        assertEquals(Math.PI,azimuthDistanceCalculation.calculateAzimuth(0,0,-1.0,0),0.00000001 );
        assertEquals(Math.PI*1.5,azimuthDistanceCalculation.calculateAzimuth(0,0,0,-1.0),0.00000001 );
    }
}
