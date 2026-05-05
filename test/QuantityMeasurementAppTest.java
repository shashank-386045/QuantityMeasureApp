import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityLengthTest {

    private static final double EPSILON = 0.0001;

    @Test
    void testAddition_SameUnit_FeetPlusFeet() {
        QuantityLength result = new QuantityLength(1.0, LengthUnit.FEET)
                .add(new QuantityLength(2.0, LengthUnit.FEET));

        assertEquals(3.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAddition_SameUnit_InchPlusInch() {
        QuantityLength result = new QuantityLength(6.0, LengthUnit.INCHES)
                .add(new QuantityLength(6.0, LengthUnit.INCHES));

        assertEquals(12.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_CrossUnit_FeetPlusInches() {
        QuantityLength result = new QuantityLength(1.0, LengthUnit.FEET)
                .add(new QuantityLength(12.0, LengthUnit.INCHES));

        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAddition_CrossUnit_InchPlusFeet() {
        QuantityLength result = new QuantityLength(12.0, LengthUnit.INCHES)
                .add(new QuantityLength(1.0, LengthUnit.FEET));

        assertEquals(24.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    void testAddition_CrossUnit_YardPlusFeet() {
        QuantityLength result = new QuantityLength(1.0, LengthUnit.YARDS)
                .add(new QuantityLength(3.0, LengthUnit.FEET));

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_CrossUnit_CentimeterPlusInch() {
        QuantityLength result = new QuantityLength(2.54, LengthUnit.CENTIMETERS)
                .add(new QuantityLength(1.0, LengthUnit.INCHES));

        assertEquals(5.08, result.getValue(), 0.01);
    }

    @Test
    void testAddition_Commutativity_BaseCheck() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result1 = a.add(b);
        QuantityLength result2 = b.add(a);

        double base1 = result1.getUnit().toBase(result1.getValue());
        double base2 = result2.getUnit().toBase(result2.getValue());

        assertEquals(base1, base2, EPSILON);
    }

    @Test
    void testAddition_WithZero() {
        QuantityLength result = new QuantityLength(5.0, LengthUnit.FEET)
                .add(new QuantityLength(0.0, LengthUnit.INCHES));

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_NegativeValues() {
        QuantityLength result = new QuantityLength(5.0, LengthUnit.FEET)
                .add(new QuantityLength(-2.0, LengthUnit.FEET));

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_NullSecondOperand() {
        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityLength(1.0, LengthUnit.FEET).add(null);
        });
    }

    @Test
    void testAddition_LargeValues() {
        QuantityLength result = new QuantityLength(1e6, LengthUnit.FEET)
                .add(new QuantityLength(1e6, LengthUnit.FEET));

        assertEquals(2e6, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_SmallValues() {
        QuantityLength result = new QuantityLength(0.001, LengthUnit.FEET)
                .add(new QuantityLength(0.002, LengthUnit.FEET));

        assertEquals(0.003, result.getValue(), EPSILON);
    }
}