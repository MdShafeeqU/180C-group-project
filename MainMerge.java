public class MainMerge {
    public static void main(String[] args) {
        
        int[] smallArray = {5, 3, 2, 6, 1, 7};

        ThreadedMergeSort.prettyPrint(smallArray, "parallel");
        ThreadedMergeSort.prettyPrint(smallArray, "sequential");
        
        // Large Array
        int[] largeArray = new int[7000000];
        for(int i = 0; i<largeArray.length; i++){
            largeArray[i] = (int)(Math.random()*7000000);
        }

        ThreadedMergeSort.prettyPrint(largeArray, "parallel");
        ThreadedMergeSort.prettyPrint(largeArray, "sequential");
    }
}
