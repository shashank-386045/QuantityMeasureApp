public interface IMeasurable {
    double convertToBaseUnit(double value);
    double convertFromBaseUnit(double baseValue);
}

public enum LengthUnit implements IMeasurable {
    FEET {
        public double convertToBaseUnit(double value) { return value * 12; }
        public double convertFromBaseUnit(double baseValue) { return baseValue / 12; }
    },
    INCHES {
        public double convertToBaseUnit(double value) { return value; }
        public double convertFromBaseUnit(double baseValue) { return baseValue; }
    };
}

public enum WeightUnit implements IMeasurable {
    KILOGRAM {
        public double convertToBaseUnit(double value) { return value * 1000; }
        public double convertFromBaseUnit(double baseValue) { return baseValue / 1000; }
    },
    GRAM {
        public double convertToBaseUnit(double value) { return value; }
        public double convertFromBaseUnit(double baseValue) { return baseValue; }
    };
}

public enum VolumeUnit implements IMeasurable {
    LITRE {
        public double convertToBaseUnit(double value) { return value * 1000; }
        public double convertFromBaseUnit(double baseValue) { return baseValue / 1000; }
    },
    MILLILITRE {
        public double convertToBaseUnit(double value) { return value; }
        public double convertFromBaseUnit(double baseValue) { return baseValue; }
    };
}

public class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null || Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException();
        }
        this.value = value;
        this.unit = unit;
    }

    public Quantity<U> subtract(Quantity<U> other) {
        if (other == null) throw new IllegalArgumentException();
        double baseResult = unit.convertToBaseUnit(value) - other.unit.convertToBaseUnit(other.value);
        double result = unit.convertFromBaseUnit(baseResult);
        return new Quantity<>(Math.round(result * 100.0) / 100.0, unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        if (other == null || targetUnit == null) throw new IllegalArgumentException();
        double baseResult = unit.convertToBaseUnit(value) - other.unit.convertToBaseUnit(other.value);
        double result = targetUnit.convertFromBaseUnit(baseResult);
        return new Quantity<>(Math.round(result * 100.0) / 100.0, targetUnit);
    }

    public double divide(Quantity<U> other) {
        if (other == null) throw new IllegalArgumentException();
        double divisor = other.unit.convertToBaseUnit(other.value);
        if (divisor == 0) throw new ArithmeticException();
        double dividend = unit.convertToBaseUnit(value);
        return dividend / divisor;
    }

    public double getValue() { return value; }
    public U getUnit() { return unit; }
}
