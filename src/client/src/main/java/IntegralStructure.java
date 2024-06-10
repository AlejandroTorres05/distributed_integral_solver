
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class IntegralStructure {

    private String functionS;

    private double lRange;
    private double uRange;

    private Equation function;

    public IntegralStructure(String functionS, double lRange, double uRange) {

        this.functionS = functionS;
        this.lRange = lRange;
        this.uRange = uRange;
        this.function = buildFunction(functionS);
    }

    private static Equation buildFunction(String function) {

        return (double x) -> {

            Expression f = new ExpressionBuilder(function).variables("x").build().setVariable("x", x);

            return f.evaluate();
        };
    }

    public double getLowerRange() {

        return lRange;
    }

    public double getUpperRange() {

        return uRange;
    }

    public Equation getFunction() {

        return function;
    }

    @Override
    public String toString() {

        return "-- " + functionS + " -- " + lRange + " y " + functionS;
    }

}
