package Herramientas;

import java.io.*;
import java.lang.*;
import java.util.*;


public class OrdenarResultados2 {

    // Método para ordenar un HashMap por sus valores de mayor a menor
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValueDesc(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
        list.sort((o1, o2) -> (o2.getValue()).compareTo(o1.getValue()));

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    // Método para guardar los HashMap ordenados en un archivo de texto
    public static void guardarOrdenado(Map<Integer, Double> estructura1, Map<Integer, Double> estructura2, String filePath) {
        // Ordenar estructura1 por sus valores de mayor a menor
        Map<Integer, Double> estructura1Ordenada = sortByValueDesc(estructura1);

        // Ordenar estructura2 por sus valores de mayor a menor
        Map<Integer, Double> estructura2Ordenada = sortByValueDesc(estructura2);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Escribir contenido de estructura1 ordenada
            writer.write("30000000:\n");
            for (Integer key : estructura1Ordenada.keySet()) {
                writer.write(key + ": " + estructura1Ordenada.get(key) + "\n");
            }
            writer.write("\n");

            // Escribir contenido de estructura2 ordenada
            writer.write("40000000:\n");
            for (Integer key : estructura2Ordenada.keySet()) {
                writer.write(key + ": " + estructura2Ordenada.get(key) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        String filePath = "resultados/resultados.txt";

        HashMap<Integer, Double> estructura1 = new HashMap<>();
        HashMap<Integer, Double> estructura2 = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean primeraEstructuraCompleta = false;

            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }

                if (line.startsWith(">")) {
                    // Cambiar a la segunda estructura
                    primeraEstructuraCompleta = true;
                    continue;
                }

                String[] parts = line.split(":");

                if (parts.length != 2) {
                    // Ignorar líneas que no contienen un solo ":"
                    continue;
                }

                int identificador = Integer.parseInt(parts[0]);
                double valor = Double.parseDouble(parts[1]);

                if (!primeraEstructuraCompleta) {
                    estructura1.put(identificador, valor);
                } else {
                    estructura2.put(identificador, valor);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Imprimir contenido de las estructuras para verificar
        System.out.println("Contenido de la estructura 1:");
        for (Integer key : estructura1.keySet()) {
            System.out.println(key + ": " + estructura1.get(key));
        }

        System.out.println("\nContenido de la estructura 2:");
        for (Integer key : estructura2.keySet()) {
            System.out.println(key + ": " + estructura2.get(key));
        }

        String filePathF = "resultados/resultadosOrdenados.txt"; // Ruta del archivo de texto de salida
        guardarOrdenado(estructura1, estructura2, filePathF);
        System.out.println("Contenido ordenado guardado en " + filePath);

    }
}

