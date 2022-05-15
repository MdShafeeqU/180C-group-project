import java.util.Random;
import java.util.concurrent.ForkJoinPool;

class Main{
    public static void main(String[] args) {  
        int numberList1[] = generateRandom(90000000, 10000000);
        for(int i=0; i<3; i++)
        {
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");
            long q1 = callThreadedQuicksort(numberList1);
            long q2 = callQuicksort(numberList1);
            compareVal(q1,q2);
        }

        callMergesort();
    }      

    public static long callThreadedQuicksort(int[] numbers){
        ForkJoinPool pool = ForkJoinPool.commonPool();
        int len = numbers.length-1;
        long start = System.currentTimeMillis();
        ThreadedQuicksort task = new ThreadedQuicksort(numbers,0,len);
        int res = pool.invoke(task);
        long end = System.currentTimeMillis();
        System.out.println("\nThreaded Quicksort Time taken: "+(end-start)+" ms");
        return (end-start);
    }

    public static long callQuicksort(int[] numbers){
        int len = numbers.length-1;
        long start = System.currentTimeMillis();
        Quicksort task = new Quicksort(numbers,0,len);
        long end = System.currentTimeMillis();
        System.out.println("Quicksort Time taken: "+(end-start)+" ms");
        return (end-start);
    }


    public static void callMergesort(){
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
            long t1 = (endTime-startTime);
            // MergeSort2 sorter = new MergeSort2();
            startTime = System.currentTimeMillis();
            // sorter.sort(largeArray);
            MergeSort.mergeSort(largeArray, 0, largeArray.length - 1);
            endTime = System.currentTimeMillis();
            System.out.println("Array Sequential sorting time = " + (endTime - startTime) + " ms");
            long t2 = (endTime-startTime);
            compareVal(t1,t2);
        }
    }

    public static int[] generateRandom(int size, int range){
        int randomArray[] = new int[size];
        Random randNum = new Random();
        for (int i = 0;i < size; i++){
            randomArray[i] = randNum.nextInt(range)+1;
        }
        return randomArray;
    }

    public static void printArray(int[] array){
        for(int element: array){
            System.out.print(element+" ");
        }
    }

    public static void compareVal(long tVal, long rVal){
        if(tVal<rVal){
            System.out.println("Threaded is faster!");
        }
        else{
            System.out.println("Non-threaded is faster!");
        }
    }
}