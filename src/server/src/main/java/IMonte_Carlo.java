import java.io.*;
import java.math.*;

public class IMonte_Carlo {

    private BigInteger number;

    public IMonte_Carlo() {

        number = new BigInteger("100000");
    }

    public IMonte_Carlo(BigInteger numbers) {

        number = numbers;
    }

    public BigDecimal development(IntegralStructure integral) {

        double x = integral.getLowerRange();
        double y = integral.getUpperRange();

        ValueRandom random = new ValueRandom(x, y);

        BigDecimal addition = BigDecimal.ZERO;
        BigInteger index = BigInteger.ZERO;

        while (number.compareTo(index) == 1) {

            double randomValue = random.get();
            addition = addition.add(BigDecimal.valueOf(integral.getFunction().development(randomValue)));

            index = index.add(BigInteger.ONE);
        }

        return (BigDecimal.valueOf(y - x).divide(new BigDecimal(number))).multiply(addition);
    }

    public void keep(String filename, IntegralStructure integral, BigDecimal result) {

        try {

            File folder = new File("results");

            if (!folder.exists()) {

                folder.mkdirs();
            }

            File file = new File(folder, filename);

            try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {

                writer.printf("%s, = %s%n", integral.toString(), result);
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

}