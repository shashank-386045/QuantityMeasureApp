import java.util.Objects;

public class QuantityLength {
    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Value must be a finite number");

        this.value = value;
        this.unit = unit;
    }

    // UC6: Default addition (uses unit of the first operand)
    public QuantityLength add(QuantityLength other) {
        return add(this, other, this.unit);
    }

    // UC7: Explicit target unit addition
    public static QuantityLength add(QuantityLength l1, QuantityLength l2, LengthUnit targetUnit) {
        validateInputs(l1, l2, targetUnit);

        // Convert to base unit and sum
        double sumInBase = l1.unit.toBase(l1.value) + l2.unit.toBase(l2.value);

        // Convert sum to target unit
        double finalValue = targetUnit.fromBase(sumInBase);

        // Round to 3 decimal places for precision consistency
        double roundedValue = Math.round(finalValue * 1000.0) / 1000.0;

        return new QuantityLength(roundedValue, targetUnit);
    }

    private static void validateInputs(QuantityLength l1, QuantityLength l2, LengthUnit target) {
        if (l1 == null || l2 == null || target == null) {
            throw new IllegalArgumentException("Operands and target unit must not be null");
        }
    }

    public double getValue() { return value; }
    public LengthUnit getUnit() { return unit; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuantityLength that = (QuantityLength) o;
        return Math.abs(that.value - this.value) < 0.001 && unit == that.unit;
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}