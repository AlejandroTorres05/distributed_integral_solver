import com.zeroc.Ice.Current;
import Modules.BrokerPrx;
import Modules.IntegralStructure;
import Modules.Server;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

public class Butler implements Server {

    private static final int QUERYS = 10;
    private ForkJoinPool fork;
    private double tax;
    private BrokerPrx bPrx;

    public Butler(BrokerPrx bPrx) {
        tax = 0;
        this.bPrx = bPrx;
        fork = new ForkJoinPool(QUERYS);
    }

    @Override
    public void developI(int requestID, IntegralStructure integral, Current current) {
        tax = tax++;
        news.IntegralStructure integralS = new news.IntegralStructure(integral.functionS, integral.lRange,
                integral.uRange);
        List<news.IntegralStructure> iQuery = cQuery(integralS);
        BigDecimal total = BigDecimal.ZERO;

        try {

            total = fork.submit(() -> fork.invoke(new IntegralQuery(iQuery))).get();
        } catch (InterruptedException | ExecutionException e) {

            e.printStackTrace();
        }

        String area = total.toString();
        tax = tax--;
        bPrx.join(requestID, area);
    }

    private List<news.IntegralStructure> cQuery(news.IntegralStructure integral) {
        double x = integral.getLRange();
        double y = integral.getURange();
        double div = (y - x) / QUERYS;

        List<news.IntegralStructure> iQuery = new ArrayList<>();

        for (int i = 0; i < QUERYS; i++) {
            iQuery.add(new news.IntegralStructure(integral.getFunctionS(), x + (i * div), y + ((i + 1) * div)));
        }
        return iQuery;
    }

    @Override
    public void printResponse(String res, Current current) {

        System.out.println(res);
    }

    @Override
    public double getLoad(Current current) {
        return tax;
    }

}
