import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.RecursiveTask;

import news.IMonte_Carlo;
import news.IResolve;

public class IntegralQuery extends RecursiveTask<BigDecimal> {

    private List<news.IntegralStructure> iQuery;
    private static IResolve resolve;

    IntegralQuery(List<news.IntegralStructure> iQuery) {

        this.iQuery = iQuery;
        resolve = new IMonte_Carlo();
    }

    @Override
    public BigDecimal compute() {

        if (iQuery.size() == 1) {

            news.IntegralStructure integral = iQuery.get(0);

            return resolve.development(integral);
        }

        int half = iQuery.size() / 2;
        List<news.IntegralStructure> lQuerys = iQuery.subList(0, half);
        List<news.IntegralStructure> rQuerys = iQuery.subList(half, iQuery.size());
        IntegralQuery lQuery = new IntegralQuery(lQuerys);
        IntegralQuery rQuery = new IntegralQuery(rQuerys);
        lQuery.fork();
        BigDecimal left = lQuery.join();
        BigDecimal right = rQuery.compute();

        return left.add(right);
    }
}