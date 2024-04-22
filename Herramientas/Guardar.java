package Herramientas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Guardar
{
    public static void guardar(String cadena, String rutaArchivo)
    {

        try
        {
            File archivo = new File(getPath(rutaArchivo));

            if (!archivo.exists()) {
                archivo.createNewFile();
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true));

            bw.write(cadena + "\n");

            bw.close();
        }
        catch(IOException e)
        {
            System.err.println(e.getMessage());
        }
    }

    private static String getPath ( String ruta )
    {
        String raiz = System.getProperty("user.dir");
        return raiz + "/" + ruta;
    }
}