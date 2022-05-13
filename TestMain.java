public class TestMain {

    public static void main(String[] args) {
        
        MergeSortParallel test = new MergeSortParallel(4);
        long endTime, startTime;

        int[] sizeArray = {7000000, 8000000, 9000000, 10000000, 11000000, 12000000};
        for (int value : sizeArray) {

            int[] largeArray = new int[value];
        
            for(int i = 0; i<largeArray.length; i++) {
                largeArray[i] = (int)(Math.random()*value);
            }

            System.out.println("Array Size = " + value);
            startTime = System.currentTimeMillis();
            test.sort(largeArray);
            endTime = System.currentTimeMillis();
            System.out.println("Array Parallel sorting time = " + (endTime - startTime) + " ms");
            
            // MergeSort2 sorter = new MergeSort2();
            startTime = System.currentTimeMillis();
            // sorter.sort(largeArray);
            MergeSort.mergeSort(largeArray, 0, largeArray.length - 1);
            endTime = System.currentTimeMillis();
            System.out.println("Array Sequential sorting time = " + (endTime - startTime) + " ms");

        }
    }
}