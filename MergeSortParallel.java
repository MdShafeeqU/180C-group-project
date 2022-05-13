import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;


public class MergeSortParallel {
    private final ForkJoinPool pool;

    private static class MergeSortTask extends RecursiveAction {
        private int[] array;
        private int start, end;
        private static int THRESHOLD;

        protected MergeSortTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
            THRESHOLD = array.length / Runtime.getRuntime().availableProcessors();
        }

        @Override
        protected void compute() {
            if (end - start <= THRESHOLD) {
                Arrays.sort(array, start, end);
                return;
            }
            int middle = start + ((end - start) >> 1);
            invokeAll(
                new MergeSortTask(array, start, middle), 
                new MergeSortTask(array, middle, end));

            merge(middle);
        }

        private void merge(int middle) {
            if (array[middle - 1] < array[middle]) {
                return; 
            }
            int[] copy = new int[end - start];
            System.arraycopy(array, start, copy, 0, copy.length);
            int copyLow = 0;
            int copyHigh = end - start;
            int copyMiddle = middle - start;

            for (int i = start, p = copyLow, q = copyMiddle; i < end; i++) {
                if (q >= copyHigh || (p < copyMiddle && copy[p] < copy[q]) ) {
                    array[i] = copy[p++];
                } else {
                    array[i] = copy[q++];
                }
            }
        }
    }

    /**
     * Creates a {@code MergeSort} containing a ForkJoinPool with the indicated parallelism level
     * @param parallelism the parallelism level used
     */
    public MergeSortParallel(int parallelism) {
        pool = new ForkJoinPool(parallelism);
    }

    /**
     * Sorts all the elements of the given array using the ForkJoin framework
     * @param array the array to sort
     */
    public void sort(int[] array) {
        ForkJoinTask<Void> job = pool.submit(new MergeSortTask(array, 0, array.length));
        job.join();
    }
}