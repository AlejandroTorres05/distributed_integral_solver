import com.zeroc.Ice.Current;
import Modules.Client;

public class Controller implements Client {

    @Override
    public void printResponse(String integral, String res, String performance, Current current) {
        if (!integral.equals("")) {
            System.out.println(integral + " = " + res);
            System.out.println(performance);
            System.out.println("\n!--------------------------------------------------!");
            System.out.println("Hola, Bienvenido\nexit para salir");
            System.out.print("Escribe un mensaje al servidor: ");
        } else {
            System.out.println(res);
        }
    }

}
