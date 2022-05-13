import java.util.Arrays;
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
    int THRESHOLD;

    public ThreadedQuicksort(int[] arr, int start, int end)
    {
        this.array = arr;
        lowIndex = start;
        highIndex = end;
        THRESHOLD = 1000000;

    }

    private static void quicksort(int[] array, int THRESHOLD) 
    {
        quicksort(array, 0, array.length - 1, THRESHOLD);
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
            quicksort(array,THRESHOLD);
            return 1;
        }
    }
        
    private static void quicksort(int[] array, int lowIndex, int highIndex, int THRESHOLD) 
    {

        

        if (lowIndex >= highIndex) {
        return;
        }

        if (highIndex - lowIndex <= THRESHOLD) 
        {
                Arrays.sort(array, lowIndex, highIndex);
                return;
        }

        int pivotIndex = new Random().nextInt(highIndex - lowIndex) + lowIndex;
        int pivot = array[pivotIndex];
        swap(array, pivotIndex, highIndex);

        int leftPointer = partition(array, lowIndex, highIndex, pivot);

        quicksort(array, lowIndex, leftPointer - 1, THRESHOLD);
        quicksort(array, leftPointer + 1, highIndex, THRESHOLD);
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
