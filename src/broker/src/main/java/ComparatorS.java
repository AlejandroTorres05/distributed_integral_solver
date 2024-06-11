import java.util.Comparator;

import Modules.ServerPrx;

public class ComparatorS implements Comparator<ServerPrx> {

    @Override
    public int compare(ServerPrx a, ServerPrx b) {
        return Double.compare(a.getLoad(), b.getLoad());
    }

}
