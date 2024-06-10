import java.util.Random;

public class ValueRandom {

    private final Random random = new Random();
    private double lowerLimit;
    private double upperLimit;

    public ValueRandom(double lowerLimit, double upperLimit) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    public double get() {
        return lowerLimit + (upperLimit - lowerLimit) * random.nextDouble();
    }

}
