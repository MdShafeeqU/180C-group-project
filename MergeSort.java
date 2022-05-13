public class MergeSort 
{
    public static void mergeSort(int array[], int leftIndex, int rightIndex)
    {
        if (leftIndex < rightIndex) {
    
            int middleIndex = leftIndex + (rightIndex-leftIndex)/2;
  
            mergeSort(array, leftIndex, middleIndex);
            mergeSort(array, middleIndex + 1, rightIndex);
            merge(array, leftIndex, middleIndex, rightIndex);
        }
    }

    public static void merge(int array[], int leftIndex, int middleIndex, int rightIndex)
    {
        int leftSize = middleIndex - leftIndex + 1;
        int rightSize = rightIndex - middleIndex;

        /* Temp Arrays */
        int leftArray[] = new int[leftSize];
        int rightArray[] = new int[rightSize];
  
        for (int i = 0; i < leftSize; ++i)
            leftArray[i] = array[leftIndex + i];
        for (int j = 0; j < rightSize; ++j)
            rightArray[j] = array[middleIndex + 1 + j];
  
        // Merging
        int i = 0, j = 0, k = leftIndex;
        while (i < leftSize && j < rightSize) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            }
            else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }
  
        /* Carry Leftover elements if any */
        while (i < leftSize) {
            array[k] = leftArray[i];
            i++; k++;
        }
  
        while (j < rightSize) {
            array[k] = rightArray[j];
            j++; k++;
        }
    }
}