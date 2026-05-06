import java.util.function.DoubleBinaryOperator;

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

enum ArithmeticOperation {
    ADD((a, b) -> a + b),
    SUBTRACT((a, b) -> a - b),
    DIVIDE((a, b) -> {
        if (b == 0) throw new ArithmeticException();
        return a / b;
    });

    private final DoubleBinaryOperator operator;
    ArithmeticOperation(DoubleBinaryOperator operator) { this.operator = operator; }
    public double compute(double a, double b) { return operator.applyAsDouble(a, b); }
}

public class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null || Double.isNaN(value) || Double.isInfinite(value)) throw new IllegalArgumentException();
        this.value = value;
        this.unit = unit;
    }

    private void validateArithmeticOperands(Quantity<U> other, U targetUnit, boolean targetUnitRequired) {
        if (other == null) throw new IllegalArgumentException();
        if (!unit.getClass().equals(other.unit.getClass())) throw new IllegalArgumentException();
        if (Double.isNaN(other.value) || Double.isInfinite(other.value)) throw new IllegalArgumentException();
        if (targetUnitRequired && targetUnit == null) throw new IllegalArgumentException();
    }

    private double performBaseArithmetic(Quantity<U> other, ArithmeticOperation operation) {
        double baseThis = unit.convertToBaseUnit(value);
        double baseOther = other.unit.convertToBaseUnit(other.value);
        return operation.compute(baseThis, baseOther);
    }

    private double roundToTwoDecimals(double val) {
        return Math.round(val * 100.0) / 100.0;
    }

    public Quantity<U> add(Quantity<U> other) {
        validateArithmeticOperands(other, unit, false);
        double baseResult = performBaseArithmetic(other, ArithmeticOperation.ADD);
        double result = unit.convertFromBaseUnit(baseResult);
        return new Quantity<>(roundToTwoDecimals(result), unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);
        double baseResult = performBaseArithmetic(other, ArithmeticOperation.ADD);
        double result = targetUnit.convertFromBaseUnit(baseResult);
        return new Quantity<>(roundToTwoDecimals(result), targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        validateArithmeticOperands(other, unit, false);
        double baseResult = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        double result = unit.convertFromBaseUnit(baseResult);
        return new Quantity<>(roundToTwoDecimals(result), unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);
        double baseResult = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        double result = targetUnit.convertFromBaseUnit(baseResult);
        return new Quantity<>(roundToTwoDecimals(result), targetUnit);
    }

    public double divide(Quantity<U> other) {
        validateArithmeticOperands(other, null, false);
        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    public double getValue() { return value; }
    public U getUnit() { return unit; }
}
