import java.util.Random;

class Main{
    public static void main(String[] args) {  
        int numberList1[] = generateRandom(10, 100);
    }      
    



    public static int[] generateRandom(int size, int range){
        int randomArray[] = new int[size];
        Random randNum = new Random();
        for (int i = 0;i < size; i++){
            randomArray[i] = randNum.nextInt(range)+1;
        }
        return randomArray;
    }
}