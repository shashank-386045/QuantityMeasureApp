class Inches {
    private final double value;

    public Inches(double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Same reference
        if (obj == null || getClass() != obj.getClass()) return false;
        Inches other = (Inches) obj;
        return Math.abs(this.value - other.value) < 0.0001; // Floating-point safe comparison
    }
}

class Feet {
    private final double value;

    public Feet(double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Feet other = (Feet) obj;
        return Math.abs(this.value - other.value) < 0.0001;
    }
}

class QuantityMeasurementApp {
    public static boolean checkFeetEquality(double val1, double val2) {
        return new Feet(val1).equals(new Feet(val2));
    }

    public static boolean checkInchesEquality(double val1, double val2) {
        return new Inches(val1).equals(new Inches(val2));
    }

    public static void main(String[] args) {
        System.out.println("Feet equality: " + checkFeetEquality(1.0, 1.0));
        System.out.println("Inches equality: " + checkInchesEquality(1.0, 2.0));
    }
}
