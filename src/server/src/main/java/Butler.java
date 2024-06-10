import com.zeroc.Ice.Current;
import Modules.IntegralStructure;
import Modules.Server;

public class Butler implements Server {

    @Override
    public String developI(IntegralStructure integral, Current current) {

        return "";
    }

    @Override
    public void printResponse(String res, Current current) {
        System.out.println(res);
    }

}
