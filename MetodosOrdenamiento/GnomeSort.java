package MetodosOrdenamiento;// Java Program to implement Gnome Sort

import Herramientas.Generar;

public class GnomeSort {

    static void gnomeSort(int[] arr, int n)
    {
        int index = 0;

        while (index < n) {
            if (index == 0)
                index++;
            if (arr[index] >= arr[index - 1])
                index++;
            else {
                int temp = 0;
                temp = arr[index];
                arr[index] = arr[index - 1];
                arr[index - 1] = temp;
                index--;
            }
        }
        return;
    }

    // Driver program to test above functions.
    public static void main(String[] args)
    {
        int[] arr = Generar.cargar_arreglo("arreglo_60000000");

        long startTime = System.nanoTime(); // Capturing start time
        gnomeSort(arr, arr.length);
        long endTime = System.nanoTime(); // Capturing end time
        double duration = (endTime - startTime) / 1_000_000_000.0; // Calculating duration in seconds
        System.out.println("Time taken for sorting: " + duration + " seconds");
    }
}

// Code Contributed by Mohit Gupta_OMG
