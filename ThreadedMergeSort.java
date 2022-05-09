import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ThreadedMergeSort {

    public static void threadedMergeSort(int[] array, int leftIndex, int rightIndex) {
        SortTask mainTask = new SortTask(array, leftIndex, rightIndex);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(mainTask);
    }

    private static class SortTask extends RecursiveAction {
        private int[] array;
        private int leftIndex;
        private int rightIndex;
    
        public SortTask(int[] array, int leftIndex, int rightIndex) {
            this.array = array;
            this.leftIndex = leftIndex;
            this.rightIndex = rightIndex;
        }
        @Override
        protected void compute() {
    
            if (leftIndex < rightIndex) {
        
                int middleIndex = leftIndex + (rightIndex-leftIndex)/2;
      
                // Recursively sort the two halves
                SortTask firstHalfTask = new SortTask(array, leftIndex, middleIndex);
                SortTask secondHalfTask = new SortTask(array, middleIndex + 1, rightIndex);
                // Invoke declared tasks
                invokeAll(firstHalfTask, secondHalfTask);
    
                //Merge firstHalf with secondHalf into our array
                MergeSort.merge(array, leftIndex, middleIndex, rightIndex);
            }
        }
    }

    static void prettyPrint(int[] array, String option) {
        long startTime = System.currentTimeMillis();
        if (option == "sequential") {
            MergeSort.mergeSort(array, 0, array.length - 1);
        }
        else {
            threadedMergeSort(array, 0, array.length - 1);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Array " + option + " sorting time = " + (endTime - startTime) + " ms");

    }
}
