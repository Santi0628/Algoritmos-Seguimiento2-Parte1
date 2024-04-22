package Herramientas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Esta clase se usa como herramienta para crear los casos
 */
public class Generar 
{
   
    public static void main(String[] args) 
    {
         int[] casos = {40000000, 30000000};

         for (int i : casos)
         {
             generar(i, "arreglo_" + i);
         }
    }

    private static void generar (int size, String file)
    {
        Random r = new Random();
        
        try (FileWriter fileWriter = new FileWriter(getPath("/arreglos/" + file + ".txt"))) {
            for (int i = 0; i < size; i++) {
                fileWriter.write(r.nextInt(10000000, 99999999 + 1) + ";");
                System.out.println("Creando: " + i + "/" + size);
            }
            System.out.println("¡HECHO!\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int[] cargar_arreglo (String file)
    {
        List<String> lines = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(getPath("/arreglos/" + file + ".txt")))) {
            
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }


        String texto = lines.get(0);
        String[] values = texto.split(";");
        int[] arreglo = new int[values.length];

        for (int i = 0; i < values.length; i++) {

            arreglo[i] = Integer.parseInt(values[i]);
        }
        
        return arreglo;
    }
    
    private static String getPath ( String ruta )
	{
		String raiz = System.getProperty("user.dir");
		String filePath = raiz + "/" + ruta;
		return filePath;
	}
}

