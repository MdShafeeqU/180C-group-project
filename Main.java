import java.util.Random;
import java.util.concurrent.ForkJoinPool;

class Main{
    public static void main(String[] args) {  
        int numberList1[] = generateRandom(90000000, 10000000);
        for(int i=0; i<6; i++)
        {
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");
            callThreadedQuicksort(numberList1);
            callQuicksort(numberList1);
        }
    }      

    public static void callThreadedQuicksort(int[] numbers){
        
        System.out.println("Performing Threaded Quicksort... \n");
        ForkJoinPool pool = ForkJoinPool.commonPool();
        int len = numbers.length-1;
        long start = System.currentTimeMillis();
        ThreadedQuicksort task = new ThreadedQuicksort(numbers,0,len);
        int res = pool.invoke(task);
        long end = System.currentTimeMillis();
        System.out.println("\nThreaded Quicksort Done.");
        System.out.print("\n");
        // printArray(numbers);
        System.out.println("\nTime taken: "+(end-start)+" ms");
        System.out.print("\n");
        // }
    }

    public static void callQuicksort(int[] numbers){
        System.out.println("Performing Quicksort... \n");
        int len = numbers.length-1;
        long start = System.currentTimeMillis();
        Quicksort task = new Quicksort(numbers,0,len);
        long end = System.currentTimeMillis();
        System.out.println("\nQuickSort Done.");
        System.out.print("\n");
        // printArray(numbers);
        System.out.println("\nTime taken: "+(end-start)+" ms");
        System.out.print("\n");
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