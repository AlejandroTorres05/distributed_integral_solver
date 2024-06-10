import java.util.Random;

public class ValueRandom {

    private final Random random = new Random();
    private double lLimit;
    private double uLimit;

    public ValueRandom(double lLimit, double uLimit) {
        this.lLimit = lLimit;
        this.uLimit = uLimit;
    }

    public double get() {
        return lLimit + (uLimit - lLimit) * random.nextDouble();
    }

}
