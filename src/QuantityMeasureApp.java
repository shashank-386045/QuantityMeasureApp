public class QuantityMeasurementApp {

    public static double demonstrateLengthConversion(double value, LengthUnit from, LengthUnit to) {
        return QuantityLength.convert(value, from, to);
    }

    public static QuantityLength demonstrateLengthConversion(QuantityLength length, LengthUnit to) {
        return length.convertTo(to);
    }

    public static boolean demonstrateLengthEquality(QuantityLength l1, QuantityLength l2) {
        return l1.equals(l2);
    }

    public static boolean demonstrateLengthComparison(double v1, LengthUnit u1, double v2, LengthUnit u2) {
        QuantityLength l1 = new QuantityLength(v1, u1);
        QuantityLength l2 = new QuantityLength(v2, u2);
        return demonstrateLengthEquality(l1, l2);
    }

    public static void main(String[] args) {
        System.out.println(demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCHES));
        System.out.println(demonstrateLengthConversion(3.0, LengthUnit.YARDS, LengthUnit.FEET));
        System.out.println(demonstrateLengthConversion(36.0, LengthUnit.INCHES, LengthUnit.YARDS));
        System.out.println(demonstrateLengthConversion(1.0, LengthUnit.CENTIMETERS, LengthUnit.INCHES));
        System.out.println(demonstrateLengthConversion(0.0, LengthUnit.FEET, LengthUnit.INCHES));
    }
}