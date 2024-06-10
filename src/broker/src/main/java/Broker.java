import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;
import com.zeroc.Ice.Object;

public class Broker {

    public static void main(String[] args) {

        try (Communicator communicator = Util.initialize(args, "config.broker")) {

            System.out.println("\n!--------------------------------------------------!");
            System.out.println("BROKER\n");
            System.out.print("---------------------------------------------------");

            ObjectAdapter adapter = communicator.createObjectAdapter("Broker");
            Object manipulator = new Manipulator();

            adapter.add(manipulator, Util.stringToIdentity("Broker"));
            adapter.activate();

            communicator.waitForShutdown();
        }
    }
}
