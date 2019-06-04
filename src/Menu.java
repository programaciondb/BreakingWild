import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Menu {
    String texto1, texto2, texto3, texto4, texto5;

    public Menu() {

    }

    public void setTexto1(String texto1) {
        this.texto1 = texto1;
    }

    public void setTexto2(String texto2) {
        this.texto2 = texto2;
    }

    public void setTexto3(String texto3) {
        this.texto3 = texto3;
    }

    public void setTexto4(String texto4) {
        this.texto4 = texto4;
    }

    public void setTexto5(String texto5) {
        this.texto5 = texto5;
    }

    public static String leerOpcionUsuario() throws OpcionInvalidaException {
        Scanner scanner = new Scanner(System.in);
        int respUsuario;
        String irAString, aux;
        do {
            System.out.print(">");
            aux = scanner.nextLine();

        } while (!validarInt(aux));
        respUsuario = Integer.parseInt(aux);

        switch (respUsuario) {
            case 1:
                irAString = "irAA";
                break;
            case 2:
                irAString = "irAB";
                break;
            case 3:
                irAString = "irAC";
                break;
            case 4:
                irAString = "irAD";
                break;
            case 5:
                irAString = "irAE";
                break;
            default:
                throw new OpcionInvalidaException("opcion invalida, intentelo otra vez");

        }

        return irAString;
    }

    public static boolean validarInt(String str) {

        try {
            Integer.valueOf(str);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("por favor solo numeros");
            return false;
        }

    }

    public static void inicioPartida(JSONObject[] etapasCompleto) throws IOException, ParseException, OpcionInvalidaException {
        Scanner scanner = new Scanner(System.in);
        int respUsuario;
        JSONObject jugadorJSONObject;
        Menu.mostrarLogoBienvenida();
        System.out.println("Bienvenido a BreakingWild");
        System.out.println("Selecciona la opción");
        System.out.println("\t1. Iniciar nueva partida");
        System.out.println("\t2. Cargar partida guardada");
        System.out.println("\t3. Salir");
        System.out.print(">");

        respUsuario = Integer.parseInt(scanner.nextLine());
        switch (respUsuario) {
            case 1:
                Jugador.crearJugadorNuevo();
            case 2:
                jugadorJSONObject = Jugador.cargarJugador();

                Etapa.empezarElJuego(etapasCompleto, jugadorJSONObject);

            case 3:

                System.out.println("Gracias por jugar BreakingWild!");
                System.out.println("Hasta la proxima!");
                System.exit(0);
                break;
            default:
                throw new OpcionInvalidaException("opcion invalida, intentelo otra vez..");

        }

    }

    public static void mostrarLogoBienvenida() throws FileNotFoundException {

        File file = new File("logo.txt");
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {
            System.out.println(scanner.nextLine());
        }

    }

    @Override
    public String toString() {
        String menuString = "";
        if (!texto1.isEmpty()) menuString = menuString + "\t1. " + texto1 + "\n";
        if (!texto2.isEmpty()) menuString = menuString + "\t2. " + texto2 + "\n";
        if (!texto3.isEmpty()) menuString = menuString + "\t3. " + texto3 + "\n";
        if (!texto4.isEmpty()) menuString = menuString + "\t4. " + texto4 + "\n";
        if (!texto5.isEmpty()) menuString = menuString + "\t5. " + texto5;

        return menuString;
    }
}


