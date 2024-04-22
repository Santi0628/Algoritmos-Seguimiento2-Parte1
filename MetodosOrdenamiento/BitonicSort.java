package MetodosOrdenamiento;

import Herramientas.Generar;

/* Java program for Bitonic Sort. Note that this program
works only when size of input is a power of 2. */
public class BitonicSort
{
    /* The parameter dir indicates the sorting direction,
    ASCENDING or DESCENDING; if (a[i] > a[j]) agrees
    with the direction, then a[i] and a[j] are
    interchanged. */
    static void compAndSwap(int[] a, int i, int j, int dir)
    {
        if ( (a[i] > a[j] && dir == 1) ||
                (a[i] < a[j] && dir == 0))
        {
            // Swapping elements
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }

    /* It recursively sorts a bitonic sequence in ascending
    order, if dir = 1, and in descending order otherwise
    (means dir=0). The sequence to be sorted starts at
    index position low, the parameter cnt is the number
    of elements to be sorted.*/
    static void bitonicMerge(int[] a, int low, int cnt, int dir)
    {
        if (cnt>1)
        {
            int k = cnt/2;
            for (int i=low; i<low+k; i++)
                compAndSwap(a,i, i+k, dir);
            bitonicMerge(a,low, k, dir);
            bitonicMerge(a,low+k, k, dir);
        }
    }

    /* This function first produces a bitonic sequence by
    recursively sorting its two halves in opposite sorting
    orders, and then calls bitonicMerge to make them in
    the same order */
    static void bitonicSort(int[] a, int low, int cnt, int dir)
    {
        if (cnt>1)
        {
            int k = cnt/2;

            // sort in ascending order since dir here is 1
            bitonicSort(a, low, k, 1);

            // sort in descending order since dir here is 0
            bitonicSort(a,low+k, k, 0);

            // Will merge whole sequence in ascending order
            // since dir=1.
            bitonicMerge(a, low, cnt, dir);
        }
    }

    /*Caller of bitonicSort for sorting the entire array
    of length N in ASCENDING order */
    static void sort(int[] a, int N, int up)
    {
        bitonicSort(a, 0, N, up);
    }

    /* A utility function to print array of size n */
    static void printArray(int[] arr)
    {
        for (int j : arr) System.out.print(j + " ");
        System.out.println();
    }

    // Driver method
    public static void main(String[] args)
    {
        int[] a = Generar.cargar_arreglo("arreglo_500000");
        int up = 1;

        long startTime = System.nanoTime(); // Capturing start time
        sort(a, a.length, up);
        long endTime = System.nanoTime(); // Capturing end time
        double duration = (endTime - startTime) / 1_000_000_000.0; // Calculating duration in seconds
        System.out.println("Time taken for sorting: " + duration + " seconds");
    }
}
