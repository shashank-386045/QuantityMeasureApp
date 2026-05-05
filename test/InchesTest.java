import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityWeightTest {

    @Test
    public void testEquality_KilogramToKilogram_SameValue() {
        assertTrue(new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .equals(new QuantityWeight(1.0, WeightUnit.KILOGRAM)));
    }

    @Test
    public void testEquality_KilogramToGram_EquivalentValue() {
        assertTrue(new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .equals(new QuantityWeight(1000.0, WeightUnit.GRAM)));
    }

    @Test
    public void testEquality_KilogramToPound_EquivalentValue() {
        assertTrue(new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .equals(new QuantityWeight(2.20462, WeightUnit.POUND)));
    }

    @Test
    public void testEquality_GramToPound_EquivalentValue() {
        assertTrue(new QuantityWeight(453.592, WeightUnit.GRAM)
                .equals(new QuantityWeight(1.0, WeightUnit.POUND)));
    }

    @Test
    public void testEquality_NullComparison() {
        assertFalse(new QuantityWeight(1.0, WeightUnit.KILOGRAM).equals(null));
    }

    @Test
    public void testEquality_SameReference() {
        QuantityWeight q = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        assertTrue(q.equals(q));
    }

    @Test
    public void testEquality_NullUnit() {
        assertThrows(IllegalArgumentException.class, () -> new QuantityWeight(1.0, null));
    }

    @Test
    public void testEquality_ZeroValue() {
        assertTrue(new QuantityWeight(0.0, WeightUnit.KILOGRAM)
                .equals(new QuantityWeight(0.0, WeightUnit.GRAM)));
    }

    @Test
    public void testEquality_NegativeWeight() {
        assertTrue(new QuantityWeight(-1.0, WeightUnit.KILOGRAM)
                .equals(new QuantityWeight(-1000.0, WeightUnit.GRAM)));
    }

    @Test
    public void testConversion_KilogramToGram() {
        QuantityWeight q = new QuantityWeight(1.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.GRAM);
        assertEquals(1000.0, q.getValue(), 0.0001);
    }

    @Test
    public void testConversion_PoundToKilogram() {
        QuantityWeight q = new QuantityWeight(2.20462, WeightUnit.POUND).convertTo(WeightUnit.KILOGRAM);
        assertEquals(1.0, q.getValue(), 0.0001);
    }

    @Test
    public void testConversion_KilogramToPound() {
        QuantityWeight q = new QuantityWeight(1.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.POUND);
        assertEquals(2.20462, q.getValue(), 0.0001);
    }

    @Test
    public void testConversion_RoundTrip() {
        QuantityWeight q = new QuantityWeight(1.5, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM)
                .convertTo(WeightUnit.KILOGRAM);
        assertEquals(1.5, q.getValue(), 0.0001);
    }

    @Test
    public void testAddition_SameUnit() {
        QuantityWeight q = new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(2.0, WeightUnit.KILOGRAM));
        assertEquals(3.0, q.getValue(), 0.0001);
        assertEquals(WeightUnit.KILOGRAM, q.getUnit());
    }

    @Test
    public void testAddition_CrossUnit() {
        QuantityWeight q = new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(1000.0, WeightUnit.GRAM));
        assertEquals(2.0, q.getValue(), 0.0001);
        assertEquals(WeightUnit.KILOGRAM, q.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit() {
        QuantityWeight q = new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(1000.0, WeightUnit.GRAM), WeightUnit.GRAM);
        assertEquals(2000.0, q.getValue(), 0.0001);
        assertEquals(WeightUnit.GRAM, q.getUnit());
    }

    @Test
    public void testAddition_Commutativity() {
        QuantityWeight q1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(1000.0, WeightUnit.GRAM));
        QuantityWeight q2 = new QuantityWeight(1000.0, WeightUnit.GRAM)
                .add(new QuantityWeight(1.0, WeightUnit.KILOGRAM));
        assertTrue(q1.equals(q2.convertTo(WeightUnit.KILOGRAM)));
    }

    @Test
    public void testAddition_WithZero() {
        QuantityWeight q = new QuantityWeight(5.0, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(0.0, WeightUnit.GRAM));
        assertEquals(5.0, q.getValue(), 0.0001);
    }

    @Test
    public void testAddition_NegativeValues() {
        QuantityWeight q = new QuantityWeight(5.0, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(-2000.0, WeightUnit.GRAM));
        assertEquals(3.0, q.getValue(), 0.0001);
    }
}
