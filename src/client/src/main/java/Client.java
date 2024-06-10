import java.math.*;

public class Client {

    private static UserInterface ui;

    public static void main(String[] args) {

        uiReceiver();

        if (args.length == 0) {

            applicationStart();
        } else {

            if (args[0].equalsIgnoreCase("test") && args.length == 6) {

                tests(args);
            } else {

                System.out.println("Error: Argumentos inválidos. Por favor, intente de nuevo.");
            }
        }
    }

    private static void uiReceiver() {

        ui = new UserInterface();
    }

    private static void applicationStart() {

        boolean comparator = true;

        while (comparator) {

            String query = ui.menu();

            if (!queryV(query)) {

                System.out.println("La consulta no es valida.");
                continue;
            }

            String lRange = "";
            String uRange = "";

            if (!query.equalsIgnoreCase("exit")) {

                boolean ranges = false;

                while (!ranges) {

                    lRange = ui.lMenu();
                    uRange = ui.uMenu();

                    try {

                        double low = Double.parseDouble(lRange);
                        double upp = Double.parseDouble(uRange);

                        if (low >= upp) {

                            System.out.println("Error");
                        } else {

                            ranges = true;
                        }
                    } catch (NumberFormatException e) {

                        System.out.println("Error, numero invalido");
                    }
                }

                IntegralStructure integral = buildI(query, lRange, uRange);
                IMonte_Carlo monte_carlo = new IMonte_Carlo();
                long initiation = System.nanoTime();
                BigDecimal res = monte_carlo.development(integral);
                long end = System.nanoTime();
                long execution = end - initiation;
                double performance = execution / 1_000_000_000.0;
                double throughput = 1.0 / performance;
                String filename = "results.txt";

                System.out.println(integral.toString() + " = " + res);
                System.out.println("Latencia: " + performance + " s");
                System.out.println("Throughput: " + throughput + " por segundo");

                monte_carlo.keep(filename, integral, res);

            } else {

                comparator = false;
            }
        }
    }

    private static IntegralStructure buildI(String funct, String lwrRange, String upprRange) {

        String functionS = funct;
        Double lRange = Double.parseDouble(lwrRange);
        Double uRange = Double.parseDouble(upprRange);

        return new IntegralStructure(functionS, lRange, uRange);
    }

    private static boolean queryV(String input) {

        return input.matches("[0-9xX\\^\\+\\-\\*/\\.\\(\\) ]+");
    }

    private static void tests(String[] args) {

        if (args[1].equals("1")) {

            BigInteger numbers = new BigInteger(args[2]);
            IMonte_Carlo monte_carlo = new IMonte_Carlo(numbers);
            IntegralStructure integral = new IntegralStructure(args[3], Double.parseDouble(args[4]),
                    Double.parseDouble(args[5]));
            long initiation = System.nanoTime();
            BigDecimal res = monte_carlo.development(integral);
            long end = System.nanoTime();
            long execution = end - initiation;
            double performance = execution / 1_000_000_000.0;

            System.out.println(res.doubleValue());
            System.out.println("Latencia: " + performance + " segundos");

        }

    }
}
