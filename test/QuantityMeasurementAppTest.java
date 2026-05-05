import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityLengthTest {

    @Test
    void testAddition_ExplicitTargetUnit_Yards() {
        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(12.0, LengthUnit.INCHES);
        QuantityLength result = QuantityLength.add(l1, l2, LengthUnit.YARDS);

        assertEquals(0.667, result.getValue(), 0.001);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Centimeters() {
        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.INCHES);
        QuantityLength l2 = new QuantityLength(1.0, LengthUnit.INCHES);
        QuantityLength result = QuantityLength.add(l1, l2, LengthUnit.CENTIMETERS);

        assertEquals(5.08, result.getValue(), 0.001);
    }

    @Test
    void testAddition_Commutativity() {
        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength res1 = QuantityLength.add(l1, l2, LengthUnit.YARDS);
        QuantityLength res2 = QuantityLength.add(l2, l1, LengthUnit.YARDS);

        assertEquals(res1.getValue(), res2.getValue());
    }

    @Test
    void testAddition_NegativeValues() {
        QuantityLength l1 = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(-2.0, LengthUnit.FEET);
        QuantityLength result = QuantityLength.add(l1, l2, LengthUnit.INCHES);

        assertEquals(36.0, result.getValue());
    }

    @Test
    void testAddition_NullTargetUnit_ThrowsException() {
        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(1.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> {
            QuantityLength.add(l1, l2, null);
        });
    }

    @Test
    void testImmutability() {
        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
        double originalValue = l1.getValue();

        QuantityLength.add(l1, l1, LengthUnit.INCHES);

        assertEquals(originalValue, l1.getValue(), "Original object should not change");
    }
}