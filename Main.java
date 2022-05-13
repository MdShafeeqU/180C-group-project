import java.util.Random;
import java.util.concurrent.ForkJoinPool;

class Main{
    public static void main(String[] args) {  
        int numberList1[] = generateRandom(90000000, 10000000);
        for(int i=0; i<3; i++)
        {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");
        callThreadedQuicksort(numberList1);
        callQuicksort(numberList1);
        }
    }      

    public static void callThreadedQuicksort(int[] numbers){
        ForkJoinPool pool = ForkJoinPool.commonPool();
        int len = numbers.length-1;
        long start = System.currentTimeMillis();
        ThreadedQuicksort task = new ThreadedQuicksort(numbers,0,len);
        int res = pool.invoke(task);
        long end = System.currentTimeMillis();
        System.out.println("\nThreaded Quicksort Time taken: "+(end-start)+" ms");
        // }
    }

    public static void callQuicksort(int[] numbers){
        int len = numbers.length-1;
        long start = System.currentTimeMillis();
        Quicksort task = new Quicksort(numbers,0,len);
        long end = System.currentTimeMillis();
        System.out.println("Quicksort Time taken: "+(end-start)+" ms");
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