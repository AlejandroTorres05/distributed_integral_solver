import java.io.*;

public class UserInterface {

    private final BufferedReader reader;

    public UserInterface() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String menu() {

        String task = "";

        try {

            System.out.println("\n!--------------------------------------------------!");
            System.out.println(
                    "Hola, Bienvenido\nSi tu escribes (exit) la conexion finaliza o si tu escribes (shutdown) sales y el server se apaga");
            System.out.print("Escribe un mensaje al servidor: ");

            task = reader.readLine();
        } catch (IOException e) {

            e.printStackTrace();
        }

        return task;
    }

    public String lMenu() {

        String lowRange = "";

        try {
            System.out.println("\n!--------------------------------------------------!");
            System.out.print("Inferior: ");
            lowRange = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lowRange;
    }

    public String uMenu() {
        String uppRange = "";

        try {
            System.out.println("\n!--------------------------------------------------!");
            System.out.print("Superior ");
            uppRange = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return uppRange;
    }

}
