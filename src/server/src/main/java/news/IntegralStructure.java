package news;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class IntegralStructure {

    private String fString;
    private double lRange;
    private double uRange;
    private Equation function;

    public IntegralStructure(String fString, double lRange, double uRange) {

        this.fString = fString;
        this.lRange = lRange;
        this.uRange = uRange;
        this.function = createF(fString);
    }

    private static Equation createF(String function) {

        return (double x) -> {

            Expression fun = new ExpressionBuilder(function).variables("x").build().setVariable("x", x);

            return fun.evaluate();
        };
    }

    public String getFunctionS() {

        return fString;
    }

    public double getLRange() {

        return lRange;
    }

    public double getURange() {

        return uRange;
    }

    public Equation getFunction() {

        return function;
    }

    @Override
    public String toString() {

        return "-------------" + "La ecuacion: " + fString + " " + lRange + " y " + uRange;
    }

}
