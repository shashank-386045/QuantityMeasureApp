enum LengthUnit {
    FEET(1.0),        // Base unit
    INCH(1.0 / 12.0); // 12 inches = 1 foot

    private final double conversionFactorToFeet;

    LengthUnit(double conversionFactorToFeet) {
        this.conversionFactorToFeet = conversionFactorToFeet;
    }

    public double toFeet(double value) {
        return value * conversionFactorToFeet;
    }
}

class QuantityLength {
    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit type cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Reflexive
        if (obj == null || getClass() != obj.getClass()) return false;

        QuantityLength other = (QuantityLength) obj;
        double thisValueInFeet = this.unit.toFeet(this.value);
        double otherValueInFeet = other.unit.toFeet(other.value);

        return Math.abs(thisValueInFeet - otherValueInFeet) < 0.0001; // Floating-point safe comparison
    }
}

class QuantityMeasurementApp {
    public static void main(String[] args) {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        System.out.println("Comparison result: " + q1.equals(q2)); // true
    }
}
