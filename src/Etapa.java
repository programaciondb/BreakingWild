import org.json.simple.JSONObject;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Etapa {
    String nombre;
    String historia;
    String asciiArt;

    //constructor vacio
    public Etapa() {
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }


    public void setAsciiArt(String asciiArt) {
        this.asciiArt = asciiArt;
    }

    @Override
    public String toString() {
        String menuString;
        menuString = asciiArt + "\n" + historia + "\n";

        return menuString;
    }

    public static int buscar(String nombreEtapa, JSONObject[] jsonObjects) throws OpcionInvalidaException {
        int i, indice = -1;
        for (i = 0; i < jsonObjects.length; i++) {
            if (jsonObjects[i].get("levelActual").equals(nombreEtapa)) {
                indice = i;
                break;
            }
        }
        if (indice == -1) throw new OpcionInvalidaException("por favor seleccione una opcion del menu..");
        return indice;
    }

    public static void empezarElJuego(JSONObject[] jsonObjects, JSONObject jugadorJSON) throws IOException, OpcionInvalidaException {
        int indice, pasos;
        String etapasiguiente = "", ir, pasosStr;
        indice = buscar((String) jugadorJSON.get("etapaActual"), jsonObjects);
        pasosStr = jugadorJSON.get("pasos").toString();
        pasos = Integer.parseInt(pasosStr);
        Jugador jugador = new Jugador((String) jugadorJSON.get("nombre"), (String) jugadorJSON.get("etapaActual"), (Double) jugadorJSON.get("progreso"), pasos);
        Etapa.construirEtapa(jsonObjects[indice], jugador);
        do {
            try {
                ir = Menu.leerOpcionUsuario();
                etapasiguiente = (String) jsonObjects[indice].get(ir);
                indice = buscar(etapasiguiente, jsonObjects);
                Etapa.construirEtapa(jsonObjects[indice], jugador);
            } catch (OpcionInvalidaException e) {
                System.out.println(e.getMessage());
                pasos--;
            }
            pasos++;
            System.out.println("    Pasos: " + pasos + "        Jugador:  " + jugador.getNombre());
            System.out.println(" ");
            // guardado automatico 1
            if (jsonObjects[indice].get("levelActual").equals("zooJaula_20")) {
                jugador.setEtapaActual("zooJaula_20");
                jugador.setPasos(pasos);
                jugador.guardarJugadorEnDisco(jugador);
                jugador.setProgreso(15.9d);
                System.out.println("Juego guardado exitosamente");
                System.out.println("Progeso del juego: " + jugador.getProgreso() + "%");
            }
            //guardado automatico 2
            if (jsonObjects[indice].get("levelActual").equals("zooJaula_0")) {
                jugador.setEtapaActual("zooJaula_0");
                jugador.setPasos(pasos);
                jugador.guardarJugadorEnDisco(jugador);
                jugador.setProgreso(0d);
                System.out.println("Juego guardado exitosamente");
                System.out.println("Progeso del juego: " + jugador.getProgreso() + "%");
            }
            // guardado automático 3
            if (jsonObjects[indice].get("levelActual").equals("zooJaula_21")) {
                jugador.setEtapaActual("zooJaula_21");
                jugador.setPasos(pasos);
                jugador.guardarJugadorEnDisco(jugador);
                jugador.setProgreso(15.8d);
                System.out.println("Juego guardado exitosamente");
                System.out.println("Progeso del juego: " + jugador.getProgreso() + "%");

            }
            //guardados de jugador
            if (jsonObjects[indice].get("levelActual").equals("GUARDAR_Pajaro")) {
                jugador.setEtapaActual("zooPajaro_0");
                jugador.setPasos(pasos);
                jugador.guardarJugadorEnDisco(jugador);
                jugador.setProgreso(30.6d);
                System.out.println("Juego guardado exitosamente");
                System.out.println("Progeso del juego: " + jugador.getProgreso() + "%");
            }
            if (jsonObjects[indice].get("levelActual").equals("GUARDAR_Granja")) {
                jugador.setEtapaActual("zooGranja_0");
                jugador.setPasos(pasos);
                jugador.guardarJugadorEnDisco(jugador);
                jugador.setProgreso(35.3d);
                System.out.println("Juego guardado exitosamente");
                System.out.println("Progeso del juego: " + jugador.getProgreso() + "%");
            }
            if (jsonObjects[indice].get("levelActual").equals("GUARDAR_Estacion")) {
                jugador.setEtapaActual("zooEstacion_0");
                jugador.setPasos(pasos);
                jugador.guardarJugadorEnDisco(jugador);
                jugador.setProgreso(70.5d);
                System.out.println("Juego guardado exitosamente");
                System.out.println("Progeso del juego: " + jugador.getProgreso() + "%");
            }
            if (jsonObjects[indice].get("levelActual").equals("GUARDAR_Acuario")) {
                jugador.setEtapaActual("zooAcuario_0");
                jugador.setPasos(pasos);
                jugador.guardarJugadorEnDisco(jugador);
                jugador.setProgreso(65.3d);
                System.out.println("Juego guardado exitosamente");
                System.out.println("Progeso del juego: " + jugador.getProgreso() + "%");
            }
        } while (!etapasiguiente.equals("FINDELJUEGO") & !etapasiguiente.equals("GANASTE"));


    }


    public static void construirEtapa(JSONObject json, Jugador jugador) {

        Etapa a1 = new Etapa();
        String historiaSinNombre, historiaConNombre;
        a1.setAsciiArt((String) json.get("asciiArt"));
        a1.setHistoria((String) json.get("historia"));
        a1.setNombre((String) json.get("levelActual"));
        historiaSinNombre = a1.getHistoria();
        historiaConNombre = historiaSinNombre.replace("BABA", jugador.nombre);
        a1.setHistoria(historiaConNombre);
        System.out.println(a1);
        Menu m = new Menu();
        m.setTexto1((String) json.get("menuA"));
        m.setTexto2((String) json.get("menuB"));
        m.setTexto3((String) json.get("menuC"));
        m.setTexto4((String) json.get("menuD"));
        m.setTexto5((String) json.get("menuE"));
        System.out.println(m);
    }

    public static void LoopSound() throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        System.out.println("Cargando....");
        try{
            File file = new File("jungle.wav");
            Clip clip = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.
                    getAudioInputStream( file );
            clip.open(ais);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            Thread.sleep(clip.getMicrosecondLength()/1000);
        } catch (FileNotFoundException ex) {
            System.out.println("Error al cargar sonido");
        }
        System.out.println("Listo!");
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
    }

}

