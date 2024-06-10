import com.zeroc.Ice.Current;
import Modules.Client;

public class Controller implements Client {

    @Override
    public void printResponse(String integral, String res, Current current) {
        if (!integral.equals("")) {
            System.out.println(integral + " = " + res);
        } else {
            System.out.println(res);
        }
    }

}
