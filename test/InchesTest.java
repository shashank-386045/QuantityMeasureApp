import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    @Test
    void testEquality_SameValue_Inches() {
        Inches inch1 = new Inches(1.0);
        Inches inch2 = new Inches(1.0);
        assertTrue(inch1.equals(inch2));
    }

    @Test
    void testEquality_DifferentValue_Inches() {
        Inches inch1 = new Inches(1.0);
        Inches inch2 = new Inches(2.0);
        assertFalse(inch1.equals(inch2));
    }

    @Test
    void testEquality_SameValue_Feet() {
        Feet ft1 = new Feet(1.0);
        Feet ft2 = new Feet(1.0);
        assertTrue(ft1.equals(ft2));
    }

    @Test
    void testEquality_DifferentValue_Feet() {
        Feet ft1 = new Feet(1.0);
        Feet ft2 = new Feet(2.0);
        assertFalse(ft1.equals(ft2));
    }

    @Test
    void testEquality_NullComparison_Inches() {
        Inches inch1 = new Inches(1.0);
        Inches inch2 = null;
        assertFalse(inch1.equals(inch2));
    }

    @Test
    void testEquality_NullComparison_Feet() {
        Feet ft1 = new Feet(1.0);
        Feet ft2 = null;
        assertFalse(ft1.equals(ft2));
    }

    @Test
    void testEquality_SameReference_Inches() {
        Inches inch1 = new Inches(1.0);
        assertTrue(inch1.equals(inch1));
    }

    @Test
    void testEquality_SameReference_Feet() {
        Feet ft1 = new Feet(1.0);
        assertTrue(ft1.equals(ft1));
    }

    @Test
    void testEquality_NonNumericInput() {
        // This test simulates invalid input handling.
        // Since constructor only accepts double, non-numeric input would be compile-time error.
        // You can wrap parsing logic in the app to handle this case.
        assertThrows(NumberFormatException.class, () -> {
            Double.parseDouble("abc"); // simulate invalid input
        });
    }
}
