import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityRefactorTest {

    @Test
    void testAddition_SameUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<LengthUnit> result = q1.add(q2);
        assertEquals(2.0, result.getValue());
    }

    @Test
    void testAddition_ExplicitTargetUnit() {
        Quantity<WeightUnit> q1 = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(5000.0, WeightUnit.GRAM);
        Quantity<WeightUnit> result = q1.add(q2, WeightUnit.GRAM);
        assertEquals(15000.0, result.getValue());
    }

    @Test
    void testSubtraction_CrossUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCHES);
        Quantity<LengthUnit> result = q1.subtract(q2);
        assertEquals(9.5, result.getValue());
    }

    @Test
    void testSubtraction_ExplicitTargetUnit() {
        Quantity<VolumeUnit> q1 = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(2.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = q1.subtract(q2, VolumeUnit.MILLILITRE);
        assertEquals(3000.0, result.getValue());
    }

    @Test
    void testDivision_SameUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);
        double result = q1.divide(q2);
        assertEquals(5.0, result);
    }

    @Test
    void testDivision_CrossUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(24.0, LengthUnit.INCHES);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);
        double result = q1.divide(q2);
        assertEquals(1.0, result);
    }

    @Test
    void testDivision_ByZero() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(0.0, LengthUnit.FEET);
        assertThrows(ArithmeticException.class, () -> q1.divide(q2));
    }

    @Test
    void testValidation_NullOperand() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> q1.add(null));
        assertThrows(IllegalArgumentException.class, () -> q1.subtract(null));
        assertThrows(IllegalArgumentException.class, () -> q1.divide(null));
    }

    @Test
    void testValidation_CrossCategory() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<WeightUnit> q2 = new Quantity<>(5.0, WeightUnit.KILOGRAM);
        assertThrows(IllegalArgumentException.class, () -> q1.add((Quantity) q2));
        assertThrows(IllegalArgumentException.class, () -> q1.subtract((Quantity) q2));
        assertThrows(IllegalArgumentException.class, () -> q1.divide((Quantity) q2));
    }
}
