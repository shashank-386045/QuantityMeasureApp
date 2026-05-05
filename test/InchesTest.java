import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityLengthTest {

    @Test
    public void testLengthUnitEnum_FeetConstant() {
        assertEquals(1.0, LengthUnit.FEET.getConversionFactor());
    }

    @Test
    public void testLengthUnitEnum_InchesConstant() {
        assertEquals(1.0 / 12.0, LengthUnit.INCHES.getConversionFactor(), 0.0001);
    }

    @Test
    public void testLengthUnitEnum_YardsConstant() {
        assertEquals(3.0, LengthUnit.YARDS.getConversionFactor());
    }

    @Test
    public void testLengthUnitEnum_CentimetersConstant() {
        assertEquals(1.0 / 30.48, LengthUnit.CENTIMETERS.getConversionFactor(), 0.0001);
    }

    @Test
    public void testConvertToBaseUnit_InchesToFeet() {
        assertEquals(1.0, LengthUnit.INCHES.convertToBaseUnit(12.0), 0.0001);
    }

    @Test
    public void testConvertToBaseUnit_YardsToFeet() {
        assertEquals(3.0, LengthUnit.YARDS.convertToBaseUnit(1.0), 0.0001);
    }

    @Test
    public void testConvertToBaseUnit_CentimetersToFeet() {
        assertEquals(1.0, LengthUnit.CENTIMETERS.convertToBaseUnit(30.48), 0.0001);
    }

    @Test
    public void testConvertFromBaseUnit_FeetToInches() {
        assertEquals(12.0, LengthUnit.INCHES.convertFromBaseUnit(1.0), 0.0001);
    }

    @Test
    public void testConvertFromBaseUnit_FeetToYards() {
        assertEquals(1.0, LengthUnit.YARDS.convertFromBaseUnit(3.0), 0.0001);
    }

    @Test
    public void testConvertFromBaseUnit_FeetToCentimeters() {
        assertEquals(30.48, LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0), 0.0001);
    }

    @Test
    public void testQuantityLength_Equality() {
        assertTrue(new QuantityLength(1.0, LengthUnit.FEET).equals(new QuantityLength(12.0, LengthUnit.INCHES)));
    }

    @Test
    public void testQuantityLength_ConvertTo() {
        QuantityLength q = new QuantityLength(1.0, LengthUnit.FEET).convertTo(LengthUnit.INCHES);
        assertEquals(12.0, q.getValue(), 0.0001);
        assertEquals(LengthUnit.INCHES, q.getUnit());
    }

    @Test
    public void testQuantityLength_Add() {
        QuantityLength q = new QuantityLength(1.0, LengthUnit.FEET).add(new QuantityLength(12.0, LengthUnit.INCHES), LengthUnit.FEET);
        assertEquals(2.0, q.getValue(), 0.0001);
        assertEquals(LengthUnit.FEET, q.getUnit());
    }

    @Test
    public void testQuantityLength_AddWithTargetUnit() {
        QuantityLength q = new QuantityLength(1.0, LengthUnit.FEET).add(new QuantityLength(12.0, LengthUnit.INCHES), LengthUnit.YARDS);
        assertEquals(0.667, q.getValue(), 0.001);
        assertEquals(LengthUnit.YARDS, q.getUnit());
    }

    @Test
    public void testQuantityLength_NullUnit() {
        assertThrows(IllegalArgumentException.class, () -> new QuantityLength(1.0, null));
    }

    @Test
    public void testQuantityLength_InvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> new QuantityLength(Double.NaN, LengthUnit.FEET));
    }
}
