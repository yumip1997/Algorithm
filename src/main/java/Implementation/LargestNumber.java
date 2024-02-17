package Implementation;

import java.util.Arrays;

public class LargestNumber {

    public int[] solution(int[] numbers) {
        int len =  numbers.length;
        int[] answer = new int[len];
        for(int i=0;i<len;i++){
            answer[i] = -1;
            for(int j=i+1;j<len;j++){
                if(numbers[i] < numbers[j]){
                    answer[i] = numbers[j];
                    break;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        LargestNumber largestNumber = new LargestNumber();
        System.out.println(Arrays.toString(largestNumber.solution(new int[]{2, 3, 3, 5})));
        System.out.println(Arrays.toString(largestNumber.solution(new int[]{9, 1, 5, 3, 6, 2})));
    }
}
