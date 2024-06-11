import java.util.concurrent.*;
import java.util.ArrayList;
import java.util.List;

import java.math.BigDecimal;

import com.zeroc.Ice.Current;
import Modules.Broker;
import Modules.ClientPrx;
import Modules.IntegralStructure;
import Modules.ServerPrx;

public class Manipulator implements Broker {

    private int servant;
    private PriorityBlockingQueue<ServerPrx> servers;
    private ConcurrentHashMap<Integer, IntegralStructure> requests;
    private ConcurrentHashMap<Integer, ClientPrx> clients;
    private ConcurrentHashMap<Integer, List<BigDecimal>> resR;
    private ConcurrentHashMap<Integer, List<Long>> latency;
    private ExecutorService querys;

    public Manipulator() {

        servers = new PriorityBlockingQueue<>(10, new ComparatorS());
        requests = new ConcurrentHashMap<>();
        clients = new ConcurrentHashMap<>();
        resR = new ConcurrentHashMap<>();
        latency = new ConcurrentHashMap<>();
        querys = Executors.newCachedThreadPool();
    }

    @Override
    public void attachServer(ServerPrx server, Current current) {

        servers.add(server);
        server.printResponse("Server en el Broker ");
    }

    @Override
    public void developI(ClientPrx clientProxy, IntegralStructure integral, Current current) {

        long startB = System.nanoTime();
        int id = integral.hashCode();

        System.out.println(integral.functionS);

        if (!servers.isEmpty()) {

            registerR(id, integral);
            registerC(id, clientProxy);
            processR(id, integral);

        } else {

            clientProxy.printResponse("", "No hay servidores registrados", "");
        }

        keepT(id, startB);
    }

    private void registerR(Integer requestID, IntegralStructure request) {
        requests.put(requestID, request);
    }

    private void registerC(Integer requestID, ClientPrx client) {
        clients.put(requestID, client);
    }

    private void processR(Integer requestID, IntegralStructure request) {
        resR.put(requestID, new ArrayList<>());
        servant = servers.size();
        List<IntegralStructure> sub = fork(request);
        earmark(requestID, sub);
    }

    private List<IntegralStructure> fork(IntegralStructure integral) {

        double x = integral.lRange;
        double y = integral.uRange;
        double div = (y - x) / servant;

        List<IntegralStructure> integrals = new ArrayList<>();

        for (int i = 0; i < servant; i++) {
            integrals.add(new IntegralStructure(integral.functionS, x + (i * div), x + ((i + 1) * div)));
        }

        return integrals;
    }

    private void earmark(Integer integralID, List<IntegralStructure> integrals) {
        for (IntegralStructure integral : integrals) {
            ServerPrx server = servers.poll();

            Runnable task = () -> {
                server.developI(integralID, integral);
            };

            querys.submit(task);
            servers.add(server);
        }
    }

    private void keepT(Integer requestID, long time) {
        if (latency.get(requestID) != null) {
            latency.get(requestID).add(time);
        } else {
            latency.put(requestID, new ArrayList<>());
            latency.get(requestID).add(time);
        }
    }

    @Override
    public void join(int requestID, String res, Current current) {

        synchronized (this) {

            BigDecimal partial = new BigDecimal(res);
            List<BigDecimal> resClient = resR.get(requestID);

            if (resClient != null) {

                resR.get(requestID).add(partial);

                if (resR.get(requestID).size() == servant) {

                    BigDecimal sum = BigDecimal.ZERO;

                    for (BigDecimal value : resR.get(requestID)) {

                        sum = sum.add(value);
                    }

                    ClientPrx client = clients.get(requestID);
                    IntegralStructure integral = requests.get(requestID);
                    long endB = System.nanoTime();
                    keepT(requestID, endB);
                    requests.remove(requestID);
                    clients.remove(requestID);
                    resR.remove(requestID);
                    String perfomanceReport = performance(requestID);

                    client.printResponse(integral.functionS + " --- " + integral.lRange + " --- " + integral.uRange,
                            "" + sum.doubleValue(), perfomanceReport);
                }
            } else {

                resR.put(requestID, new ArrayList<>());
                join(requestID, res, current);
            }
        }
    }

    private String performance(Integer requestID) {

        List<Long> latencyList = latency.get(requestID);
        double latencyInSeconds = latency(latencyList.get(0), latencyList.get(1));

        latency.remove(requestID);

        return "Latencia " + latencyInSeconds + " seg\n" +
                "";
    }

    private double latency(long start, long end) {
        long execution = end - start;
        double seconds = execution / 1_000_000_000.0;
        return seconds;
    }

}
