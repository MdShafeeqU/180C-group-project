import java.util.Random;
import java.util.concurrent.ForkJoinPool;

class Main{
    public static void main(String[] args) {  
        // Test 1
        System.out.println("----------------------------------\n\nTEST1\nSize: 10000\n");

        int numberList1[] = generateRandom(10, 10000);
        long threadedQTest1 = callThreadedQuicksort(numberList1);
        long regularQTest1 = callQuicksort(numberList1);
        compareVal(threadedQTest1,regularQTest1);

        // Test 2
        System.out.println("----------------------------------\n\nTEST2\nSize: 10000\n");
        int numberList2[] = generateRandom(100, 10000);
        long threadedQTest2 = callThreadedQuicksort(numberList2);
        long regularQTest2 = callQuicksort(numberList2);
        compareVal(threadedQTest2,regularQTest2);

        // Test 3
        System.out.println("----------------------------------\n\nTEST3\nSize: 10000\n");
        int numberList3[] = generateRandom(1000, 10000);
        long threadedQTest3 = callThreadedQuicksort(numberList3);
        long regularQTest3 = callQuicksort(numberList3);
        compareVal(threadedQTest3,regularQTest3);

        // Test 4
        System.out.println("----------------------------------\n\nTEST4\nSize: 10000\n");
        int numberList4[] = generateRandom(10000, 10000);
        long threadedQTest4 = callThreadedQuicksort(numberList4);
        long regularQTest4 = callQuicksort(numberList4);
        compareVal(threadedQTest4,regularQTest4);

        // Test 5
        System.out.println("----------------------------------\n\nTEST5\nSize: 10000\n");
        int numberList5[] = generateRandom(100000, 10000);
        long threadedQTest5 = callThreadedQuicksort(numberList5);
        long regularQTest5 = callQuicksort(numberList5);
        compareVal(threadedQTest5,regularQTest5);
    }      

    public static void compareVal(long tVal, long rVal){
        if (tVal < rVal){
            System.out.println("Threaded computation is faster!");
        }
        else{
            System.out.println("Non-threaded computation is faster!");
        }
    }

    
    public static long callThreadedQuicksort(int[] numbers){
        System.out.println("Performing Threaded Quicksort... \n");
        ForkJoinPool pool = ForkJoinPool.commonPool();
        int len = numbers.length-1;
        long start = System.currentTimeMillis();
        ThreadedQuicksort task = new ThreadedQuicksort(numbers,0,len);
        int res = pool.invoke(task);
        long end = System.currentTimeMillis();
        System.out.println("Threaded Quicksort Done.");
        System.out.print("\n");
        // printArray(numbers);
        System.out.println("Time taken: "+(end-start)+" ms");
        // System.out.print("\n");
        return (end-start);
    }

    public static long callQuicksort(int[] numbers){
        System.out.println("Performing Quicksort... \n");
        int len = numbers.length-1;
        long start = System.currentTimeMillis();
        Quicksort task = new Quicksort(numbers,0,len);
        long end = System.currentTimeMillis();
        System.out.println("QuickSort Done.");
        System.out.print("\n");
        // printArray(numbers);
        System.out.println("Time taken: "+(end-start)+" ms");
        // System.out.print("\n");
        return (end-start);
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
}