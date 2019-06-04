import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jugador {
    String nombre, etapaActual;
    int pasos;
    Double progreso;

    // constructor jugador nuevo
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.etapaActual = "zooJaula_0";
        this.progreso = 0.0;
        this.pasos = 0;
    }

    // constructor jugador ya creado
    public Jugador(String nombre, String etapaActual, Double progreso, int pasos) {
        this.nombre = nombre;
        this.etapaActual = etapaActual;
        this.progreso = progreso;
        this.pasos = pasos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setEtapaActual(String etapaActual) {
        this.etapaActual = etapaActual;
    }

    public Double getProgreso() {
        return progreso;
    }

    public void setProgreso(Double progreso) {
        this.progreso = progreso;
    }

    public void setPasos(int pasos) {
        this.pasos = pasos;
    }


    public static void crearJugadorNuevo() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String nombre;
        System.out.println("Ingresa tu nombre:");
        nombre = scanner.nextLine();
        nombre = nombre.substring(0, 1).toUpperCase() + nombre.substring(1).toLowerCase();
        Jugador jugador = new Jugador(nombre);
        jugador.guardarJugadorEnDisco(jugador);

    }

    public static JSONObject cargarJugador() throws FileNotFoundException, ParseException {
        List<String> listaJugadores = new ArrayList<String>();
        boolean x = true;
        Scanner scanner = new Scanner(System.in);
        String nombreRecibido, nombreACargar;
        File[] files = new File("jugadores").listFiles();

        for (File file : files) {
            if (file.isFile()) {
                listaJugadores.add(file.getName());
            }
        }
        do {
            System.out.println();
            System.out.println("Escribe el nombre de algún jugador de la lista para cargar la partida:");
            for (String jugador : listaJugadores) {
                System.out.println(jugador.substring(0, jugador.length() - 5));
            }
            nombreRecibido = scanner.nextLine();
            nombreRecibido = nombreRecibido.substring(0, 1).toUpperCase() + nombreRecibido.substring(1).toLowerCase();
            nombreRecibido = nombreRecibido + ".json";
            nombreACargar = "";

            for (String jugador : listaJugadores) {
                if (nombreRecibido.equals(jugador)) {
                    nombreACargar = nombreRecibido;
                    x = true;
                    break;
                } else {
                    //jugador no existe manejar exception
                    x = false;
                }
            }
            if (!x) {
                System.out.println("no encontramos el jugador, intentelo otra vez");
                System.out.println();
            }

        } while (!x);


        File file = new File("jugadores/" + nombreACargar);
        Scanner scannerFile = new Scanner(file);
        JSONObject jsonObject;

        try {
            Object object = new JSONParser().parse(scannerFile.nextLine());
            jsonObject = (JSONObject) object;

        } catch (ParseException ex) {
            System.out.println("Error al cargar el jugador.");
            ex.getLocalizedMessage();
            throw ex;
        }
        return jsonObject;

    }


    public void guardarJugadorEnDisco(Jugador jugador) throws IOException {
        PrintWriter printWriter = new PrintWriter("jugadores/" + jugador.nombre + ".json");

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("nombre", jugador.nombre);
        jsonObject.put("etapaActual", etapaActual);
        jsonObject.put("progreso", progreso);
        jsonObject.put("pasos", pasos);

        printWriter.print(jsonObject);
        printWriter.close();

    }
}
