import org.junit.Assert;
import org.junit.Test;
import pl.sats.BasicGeoCalculations.AngleConverter;


public class AngleConverterTest {
    private AngleConverter angleConverter = new AngleConverter();
    @Test
    public void testGradToRad() {
        Assert.assertEquals((2 * Math.PI), angleConverter.gradToRad(400.0), 0.0000000000000000001);
        Assert.assertEquals(0.015707963267949, angleConverter.gradToRad(1.0d), 0.0000000000000001);
    }
    @Test
    public void testRadToGrad(){
        Assert.assertEquals(400, angleConverter.radToGrad(2*Math.PI),0.0000000000000001);
        Assert.assertEquals(1.0d, angleConverter.radToGrad(0.015707963267949),0.00000000000001);
    }
    @Test
    public void testDegToRad(){
        Assert.assertEquals((2 * Math.PI), angleConverter.degToRad(360.0), 0.0000000000000000001);
        Assert.assertEquals(0.017453292519943, angleConverter.degToRad(1.0d), 0.000000000000001);
    }
    @Test
    public void testRadToDeg(){
        Assert.assertEquals(360, angleConverter.radToDeg(2*Math.PI),0.000000000000001);
        Assert.assertEquals(1.0d, angleConverter.radToDeg(0.017453292519943),0.0000000000001);
    }
    @Test
    public void testDegToGrad(){
        Assert.assertEquals(400,angleConverter.degToGrad(360),0.000000001);
        Assert.assertEquals(1.11111111,angleConverter.degToGrad(1),0.00000001);
    }
    @Test
    public void testGradToDeg(){
        Assert.assertEquals(360,angleConverter.gradToDeg(400),0.000000001);
        Assert.assertEquals(0.9,angleConverter.gradToDeg(1),0.1);
    }
    @Test
    public void testDegToDeg(){
        Assert.assertEquals("54°14′4.3558799999948405″",angleConverter.degToDeg(54.2345433));
        Assert.assertEquals("-54°14′4.3558799999948405″",angleConverter.degToDeg(-54.2345433));
    }

}
