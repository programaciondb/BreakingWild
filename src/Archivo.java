import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Archivo {

    private String[] etapasEncriptadas;


    public Archivo() {
    }

    public static JSONObject[] leerArchivo() throws ParseException, FileNotFoundException {
        String[] etapasStringJSON;
        int i;

        //Declaracion array JSONObject
        JSONObject[] jsonObjects = new JSONObject[65];

        // Leer archivo y desencriptar1
        Archivo archivo = new Archivo();
        archivo.leer();
        etapasStringJSON = archivo.desencriptar();

        //Llenar array de JSONObjects con las etapas
        try {
            for (i = 0; i < jsonObjects.length; i++) {
                Object object = new JSONParser().parse(etapasStringJSON[i]);
                jsonObjects[i] = (JSONObject) object;
            }
        } catch (ParseException ex) {
            System.out.println("Error al cargar las etapas.");
            ex.getLocalizedMessage();
            throw ex;
        }
        return jsonObjects;
    }

    public void leer() throws FileNotFoundException {
        File file = new File("encriptadoFinal.txt");
        Scanner scanner = new Scanner(file);
        int i = 0;

        //Cambiar cantidad de etapas
        String[] listaEtapas = new String[65];
        etapasEncriptadas = listaEtapas;
        while (scanner.hasNext()) {
            etapasEncriptadas[i] = scanner.nextLine();
            i++;
        }
        scanner.close();
    }

    public String[] desencriptar() {


        String[][] arrayStringLetrasEncriptadas = new String[etapasEncriptadas.length][];
        String[] stringJSONFinal = new String[etapasEncriptadas.length];
        char[][] arrayLetrasDesencriptadas = new char[etapasEncriptadas.length][5500];
        int i, j;
        for (i = 0; i < etapasEncriptadas.length; i++) {
            arrayStringLetrasEncriptadas[i] = etapasEncriptadas[i].split(" ");
        }
        int[][] arrayNumerosDesencriptados = new int[etapasEncriptadas.length][5500];

        for (i = 0; i < stringJSONFinal.length; i++) {
            stringJSONFinal[i] = "";
        }

        for (i = 0; i < arrayStringLetrasEncriptadas.length; i++) {
            for (j = 0; j < arrayStringLetrasEncriptadas[i].length; j++) {
                arrayNumerosDesencriptados[i][j] = arrayStringLetrasEncriptadas[i][j].charAt(0);
                arrayNumerosDesencriptados[i][j] = ((arrayNumerosDesencriptados[i][j] * 2) + 40) / 4;
                arrayLetrasDesencriptadas[i][j] = (char) arrayNumerosDesencriptados[i][j];
                stringJSONFinal[i] = stringJSONFinal[i].concat(String.valueOf(arrayLetrasDesencriptadas[i][j]));

            }
            System.out.println(" ");
        }
        return stringJSONFinal;

    }

}
