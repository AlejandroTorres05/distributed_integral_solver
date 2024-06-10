import java.util.ArrayList;
import java.util.List;

import java.lang.Exception;

import com.zeroc.Ice.*;

public class Server {

    public static void main(String[] args) {

        System.out.println(
                "\n************SERVIDOR ABIERTO************* Toca darle enter al cliente para que muestre las cosas");

        List<String> extraArgs = new ArrayList<String>();

        try (Communicator communicator = Util.initialize(args, "config.server", extraArgs)) {

            if (!extraArgs.isEmpty()) {

                System.err.println("Muchos argumentos");

                for (String v : extraArgs) {

                    System.out.println(v);
                }
            }

            communicator.getProperties().setProperty("Ice.Default.Package",
                    "com.zeroc.demos.Ice.callback");

            ObjectAdapter adapter = communicator.createObjectAdapter(
                    "Callback.Service");

            adapter.activate();

            communicator.waitForShutdown();

        } catch (Exception e) {
            System.out.println("Error en el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
