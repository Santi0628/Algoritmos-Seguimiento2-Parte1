package MetodosOrdenamiento;

import Herramientas.Generar;
import Herramientas.Guardar;

import java.util.concurrent.*;

public class MegaTiempo {

    private static final TreeSort tree = new TreeSort();

    public static void main(String[] args) {

        int[] casos = {30000000, 40000000};
        String[] algoritmos = {"1. TimSort", "2. CombSort", "3. TreeSort", "4. PigeonholeSort", "5. HeapSort",
                "6. BitonicSort", "7. GnomeSort"};

        for (int caso : casos) {

            System.out.println("Caso: " + caso + " * " + caso + "\n");
            Guardar.guardar(caso + ";", "resultados/resultados.txt");

            for (int i = 0; i < 7; i++) {
                System.out.println(algoritmos[i]);

                int[] m = Generar.cargar_arreglo("arreglo_" + caso);
                int n = m.length;

                double start_time = System.nanoTime();

                metodo(i + 1, m, n);

                double stop_time = System.nanoTime();

                double total_time = stop_time - start_time;

                Guardar.guardar((i + 1) + ":" + (double) (total_time / 1000000000), "resultados/resultados.txt");

                System.out.println("\nTiempo: " + (double) (total_time / 1000000000) + "s\n\n");
            }
            Guardar.guardar(">", "resultados/resultados.txt");
        }
        System.exit(0);  // detiene la ejecución de la JVM después de procesar todos los casos


    }

    private static void metodo(int opc, int[] arreglo, int n) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<?> future = executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    switch (opc) {
                        case 1:
                            TimSort.timSort(arreglo, n);
                            break;
                        case 2:
                            CombSort.sort(arreglo);
                            break;
                        case 3:
                            tree.treeins(arreglo);
                            tree.inorderRec(tree.root);
                            break;
                        case 4:
                            PigeonholeSort.pigeonhole_sort(arreglo, n);
                            break;
                        case 5:
                            HeapSort.sort(arreglo);
                            break;
                        case 6:
                            BitonicSort.sort(arreglo, n, 1);
                            break;
                        case 7:
                            GnomeSort.gnomeSort(arreglo, n);
                            break;
                    }
                } catch (StackOverflowError s) {
                    System.err.println("StackOverFlow!");
                }
            }
        });

        try {
            future.get(100, TimeUnit.SECONDS);  // intenta ejecutar durante 100 segundos
        } catch (TimeoutException e) {
            future.cancel(true);  // detiene la ejecución si sobrepasa los 5 segundos
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdownNow();
    }
}