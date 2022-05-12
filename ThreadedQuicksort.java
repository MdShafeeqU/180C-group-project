import java.util.Random;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

class globalvars
{
    public static int threadflag=0;
}



class ThreadedQuicksort extends RecursiveTask<Integer>
{
    int[] array;
    int highIndex;
    int lowIndex;

    public ThreadedQuicksort(int[] arr, int start, int end)
    {
        this.array = arr;
        lowIndex = start;
        highIndex = end;
    }

    private static void quicksort(int[] array) 
    {
        quicksort(array, 0, array.length - 1);
    }

    @Override
    protected Integer compute()
    {
        if(globalvars.threadflag==0)
        {
            globalvars.threadflag=1;
            int pivotIndex = new Random().nextInt(highIndex - lowIndex) + lowIndex;
            int pivot = array[pivotIndex];
            swap(array, pivotIndex, highIndex);
            int leftPointer = partition(array, lowIndex, highIndex, pivot);

            ThreadedQuicksort task1 =  new ThreadedQuicksort(array, lowIndex, leftPointer - 1);
            ThreadedQuicksort task2 =  new ThreadedQuicksort(array, leftPointer + 1, highIndex); 

            task1.fork();
            task2.fork(); 
            task1.join();
            task2.join();
            return 1;
        }
        else
        {
            quicksort(array);
            return 1;
        }
    }
        
    private static void quicksort(int[] array, int lowIndex, int highIndex) 
    {

        if (lowIndex >= highIndex) {
        return;
        }

        int pivotIndex = new Random().nextInt(highIndex - lowIndex) + lowIndex;
        int pivot = array[pivotIndex];
        swap(array, pivotIndex, highIndex);

        int leftPointer = partition(array, lowIndex, highIndex, pivot);

        quicksort(array, lowIndex, leftPointer - 1);
        quicksort(array, leftPointer + 1, highIndex);
    }

    private static int partition(int[] array, int lowIndex, int highIndex, int pivot) 
    {
        int leftPointer = lowIndex;
        int rightPointer = highIndex - 1;

        while (leftPointer < rightPointer) 
        {
            while (array[leftPointer] <= pivot && leftPointer < rightPointer) {leftPointer++;}
            while (array[rightPointer] >= pivot && leftPointer < rightPointer) {rightPointer--;}
            swap(array, leftPointer, rightPointer);
        }
        
        if(array[leftPointer] > array[highIndex]) {swap(array, leftPointer, highIndex);}
        else {leftPointer = highIndex;}
        return leftPointer;
    }

    private static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    private static void printArray(int[] numbers) 
    {
        for (int i = 0; i < numbers.length; i++) 
        {
        System.out.println(numbers[i]);
        }
    }
}



// class Main
// {
//     public static int[] generateRandom(int size, int range)
//     {
//         int randomArray[] = new int[size];
//         Random randNum = new Random();
//         for (int i = 0;i < size; i++){
//             randomArray[i] = randNum.nextInt(range)+1;
//         }
//         return randomArray;
//     }

//     public static void main(String[] args)
//     {   
//         ForkJoinPool pool = ForkJoinPool.commonPool();
//         int numbers[] = generateRandom(100000, 100);
//         int len = numbers.length-1;
//         ThreadedQuicksort task = new ThreadedQuicksort(numbers,0,len);
//         long start = System.currentTimeMillis();
//         int res = pool.invoke(task);
//         long end = System.currentTimeMillis();
//         System.out.println("\nTime taken: "+(end-start)+" ms");
//         System.out.println("Done.");
//     }
// }
