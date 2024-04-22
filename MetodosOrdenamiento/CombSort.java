package MetodosOrdenamiento;// Java program for implementation of Comb Sort

import Herramientas.Generar;

public class CombSort
{
    // To find gap between elements
    static int getNextGap(int gap)
    {
        // Shrink gap by Shrink factor
        gap = (gap*10)/13;
        return Math.max(gap, 1);
    }

    // Function to sort arr[] using Comb Sort
    public static void sort(int[] arr)
    {
        int n = arr.length;

        // initialize gap
        int gap = n;

        // Initialize swapped as true to make sure that
        // loop runs
        boolean swapped = true;

        // Keep running while gap is more than 1 and last
        // iteration caused a swap
        while (gap != 1 || swapped)
        {
            // Find next gap
            gap = getNextGap(gap);

            // Initialize swapped as false so that we can
            // check if swap happened or not
            swapped = false;

            // Compare all elements with current gap
            for (int i=0; i<n-gap; i++)
            {
                if (arr[i] > arr[i+gap])
                {
                    // Swap arr[i] and arr[i+gap]
                    int temp = arr[i];
                    arr[i] = arr[i+gap];
                    arr[i+gap] = temp;

                    // Set swapped
                    swapped = true;
                }
            }
        }
    }

    // Driver method
    public static void main(String[] args)
    {
        int[] arr = Generar.cargar_arreglo("arreglo_30000000");

        double start_time = System.nanoTime();
        sort(arr);
        double stop_time = System.nanoTime();
        double total_time = stop_time - start_time;
        System.out.println("Tiempo de ejecucion del Timsort "+ (double)(total_time / 1000000000));
    }
}
/* This code is contributed by Rajat Mishra */
