package Recursive;

public class TargetNumber {

    private static int TARGET_NUMBER = 0;
    private static int cnt = 0;
    private static int NUMBER_CNT = 0;
    private static int[] NUMBER_ARR = null;

    private static void recursive(int sum, int index){
        if(index == NUMBER_CNT){
            if(sum == TARGET_NUMBER){
                cnt++;
            }
            return;
        }
        recursive(sum + NUMBER_ARR[index], index+1);
        recursive(sum - NUMBER_ARR[index], index+1);
    }

    public static void main(String[] args) {

        int[] numbers = {1,1,1,1,1};
        int target = 3;

        TARGET_NUMBER = target;
        NUMBER_CNT = numbers.length;
        NUMBER_ARR = numbers;

        recursive(0,0);
    }
}
