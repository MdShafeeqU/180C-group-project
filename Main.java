import java.util.Random;

class Main{
    public static void main(String[] args) {  
        int n = 10, range = 100;
        int randomArray[] = new int[n];
        Random randNum = new Random();
        for (int i = 0;i < n; i++){
            randomArray[i] = randNum.nextInt(range)+1;
        }

        for (int i: randomArray){
            System.out.println(i);
        }
    }       
}