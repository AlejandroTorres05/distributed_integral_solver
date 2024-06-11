import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Object;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import Modules.BrokerPrx;
import Modules.ClientPrx;
import Modules.IntegralStructure;

public class Client {

    private static BrokerPrx bPrx;
    private static ClientPrx cPrx;

    public static void main(String[] args) {

        List<String> extraArgs = new ArrayList<String>();

        try (Communicator communicator = Util.initialize(args, "config.client", extraArgs)) {

            bPrx = BrokerPrx.checkedCast(communicator.propertyToProxy("Broker.Proxy")).ice_twoway().ice_secure(false);

            if (bPrx == null) {

                throw new Error("El proxy es invalido");
            }

            ObjectAdapter adapter = communicator.createObjectAdapter("Client");
            Object servent = new Controller();
            adapter.add(servent, Util.stringToIdentity("Client"));
            adapter.activate();
            cPrx = ClientPrx
                    .checkedCast(adapter.createProxy(Util.stringToIdentity("Client")).ice_twoway().ice_secure(false));

            if (args.length == 0) {

                clientStart();
            } else {
                System.out.println("Error: Argumentos inválidos. Por favor, intente de nuevo.");
            }

            if (!extraArgs.isEmpty()) {

                System.err.println("Demasiados argumentos");

                for (String v : extraArgs) {

                    System.out.println(v);
                }
            }
        }
    }

    private static void clientStart() {

        boolean comparator = true;

        while (comparator) {

            String input = menu();
            double lRange = 0;
            double uRange = 0;

            if (!input.equalsIgnoreCase("exit")) {

                boolean ranges = false;

                while (!ranges) {

                    String low = lMenu();
                    String upp = uMenu();

                    try {

                        lRange = Double.parseDouble(low);
                        uRange = Double.parseDouble(upp);

                        if (lRange >= uRange) {

                            System.out.println("Error");
                        } else {

                            ranges = true;
                        }
                    } catch (NumberFormatException e) {

                        System.out.println("Dato invalido");
                    }
                }

                IntegralStructure integral = new IntegralStructure(input, lRange, uRange);
                bPrx.developI(cPrx, integral);

            } else {

                comparator = false;
            }
        }
    }

    private static String menu() {

        String task = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {

            System.out.println("\n!--------------------------------------------------!");
            System.out.println("Hola, Bienvenido\nexit para salir");
            System.out.print("Escribe un mensaje al servidor: ");

            task = reader.readLine();
        } catch (IOException e) {

            e.printStackTrace();
        }

        return task;
    }

    private static String lMenu() {

        String lower = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {

            System.out.println("\n!--------------------------------------------------!");
            System.out.print("Escribe el valor que esta abajo: ");
            lower = reader.readLine();
        } catch (IOException e) {

            e.printStackTrace();
        }

        return lower;
    }

    private static String uMenu() {

        String upper = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {

            System.out.println("\n!--------------------------------------------------!");
            System.out.print("Escribe el valor que esta arriba: ");
            upper = reader.readLine();
        } catch (IOException e) {

            e.printStackTrace();
        }

        return upper;
    }
}
