import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.zeroc.Ice.Current;
import Modules.Broker;
import Modules.ClientPrx;
import Modules.IntegralStructure;
import Modules.ServerPrx;

public class Manipulator implements Broker {

    private List<ServerPrx> servers;
    private AtomicInteger index;

    public Manipulator() {

        servers = Collections.synchronizedList(new LinkedList<>());
        index = new AtomicInteger(0);
    }

    @Override
    public void attachServer(ServerPrx server, Current current) {

        servers.add(server);
        server.printResponse("Server en el Broker ");
    }

    @Override
    public void developI(ClientPrx clientProxy, IntegralStructure integral, com.zeroc.Ice.Current current) {

        System.out.println(integral.functionS);

        if (!servers.isEmpty()) {

            int serverIndex = index.getAndUpdate(i -> (i + 1) % servers.size());
            ServerPrx server = servers.get(serverIndex);

            try {

                server.developI(integral);

                clientProxy.printResponse(integral.toString(), "Exito!!! :)");
            } catch (Exception e) {

                clientProxy.printResponse("", "Error al procesar la solicitud en el servidor: " + e.getMessage());
            }
        } else {

            clientProxy.printResponse("", "No hay servidores registrados");
        }
    }

}
