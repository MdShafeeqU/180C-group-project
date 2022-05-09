import java.util.Random;
import java.util.concurrent.ForkJoinPool;

class Main{
    public static void main(String[] args) {  
        int numberList1[] = generateRandom(10, 100);
        callQuicksort(numberList1);
        
    }      

    public static void callQuicksort(int[] numbers){
        System.out.println("Performing Quicksort... \n");
        ForkJoinPool pool = ForkJoinPool.commonPool();
        int len = numbers.length-1;
        ThreadedQuicksort task = new ThreadedQuicksort(numbers,0,len);
        int res = pool.invoke(task);
        System.out.println("\nQuickSort Done.");
        System.out.print("\n");
        printArray(numbers);
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