public enum LengthUnit {
    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }
}

public class QuantityLength {
    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        if (unit == null || Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException();
        }
        this.value = value;
        this.unit = unit;
    }

    public QuantityLength convertTo(LengthUnit targetUnit) {
        double baseValue = unit.convertToBaseUnit(value);
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);
        return new QuantityLength(convertedValue, targetUnit);
    }

    public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
        double baseValue1 = unit.convertToBaseUnit(value);
        double baseValue2 = other.unit.convertToBaseUnit(other.value);
        double sum = baseValue1 + baseValue2;
        double convertedValue = targetUnit.convertFromBaseUnit(sum);
        return new QuantityLength(convertedValue, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QuantityLength)) return false;
        QuantityLength other = (QuantityLength) obj;
        double baseValue1 = unit.convertToBaseUnit(value);
        double baseValue2 = other.unit.convertToBaseUnit(other.value);
        return Math.abs(baseValue1 - baseValue2) < 0.0001;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }
}
