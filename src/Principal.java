import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;


public class Principal {

    public static void main(String[] args) throws IOException, ParseException, UnsupportedAudioFileException, InterruptedException, LineUnavailableException {
        JSONObject[] jsonObjects = Archivo.leerArchivo();
        Etapa.LoopSound();
        boolean estaCorrecta;
        do {
            try {
                Menu.inicioPartida(jsonObjects);
                estaCorrecta = true;
            } catch (OpcionInvalidaException e) {
                System.out.println(e.getMessage());
                estaCorrecta = false;
            } catch (NumberFormatException e) {
                System.out.println("Por favor solo números..\n");
                estaCorrecta = false;
            }
        } while (!estaCorrecta);

    }


}
