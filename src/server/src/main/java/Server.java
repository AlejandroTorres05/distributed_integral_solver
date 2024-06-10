import java.util.*;
import java.lang.Exception;
//import com.zeroc.Ice.*;
import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Object;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;
import Modules.BrokerPrx;
import Modules.ServerPrx;

public class Server {

    private static BrokerPrx bPrx;
    private static ServerPrx sPrx;

    public static void main(String[] args) {

        System.out.println(
                "\n************SERVIDOR ABIERTO************* Toca darle enter al cliente para que muestre las cosas");

        List<String> extraArgs = new ArrayList<String>();

        try (Communicator communicator = Util.initialize(args, "config.server", extraArgs)) {

            bPrx = BrokerPrx.checkedCast(communicator.propertyToProxy("Broker.Proxy")).ice_twoway().ice_secure(false);

            if (bPrx == null) {
                throw new Error("Proxy invalido para el broker, no se pudo crear");
            }

            ObjectAdapter adapter = communicator.createObjectAdapter("Server");
            Object butler_server = new Butler();
            adapter.add(butler_server, Util.stringToIdentity("Server"));
            adapter.activate();

            sPrx = ServerPrx
                    .checkedCast(adapter.createProxy(Util.stringToIdentity("Server")).ice_twoway().ice_secure(false));

            bPrx.attachServer(sPrx);

            if (!extraArgs.isEmpty()) {

                System.err.println("Demasiados argumentos");

                for (String v : extraArgs) {

                    System.out.println(v);
                }
            }

            /**
             * communicator.getProperties().setProperty("Ice.Default.Package",
             * "com.zeroc.demos.Ice.callback");
             * 
             * 
             * ObjectAdapter adapter = communicator.createObjectAdapter(
             * "Callback.Service");
             * 
             * adapter.activate();
             * 
             * communicator.waitForShutdown();
             */

            communicator.waitForShutdown();

        } catch (Exception e) {
            System.out.println("Error en el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
