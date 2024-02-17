package StackAndQueue;

import java.util.Stack;

public class BaackLargeNumber {

    public int[] solution(int[] numbers) {
        int len = numbers.length;
        Stack<Integer> stack = new Stack<>();
        int[] answers = new int[len];

        for(int i=len-1;i>0;i--){
            while (!stack.isEmpty()){
                if(stack.peek() > numbers[i]){
                    answers[i] = stack.peek();
                    break;
                }else{
                    stack.pop();
                }
            }

            if(stack.isEmpty()){
                answers[i] = -1;
            }

            stack.push(numbers[i]);
        }

        return answers;
    }

    public static void main(String[] args) {
        BaackLargeNumber number = new BaackLargeNumber();
        System.out.println(number.solution(new int[]{2, 3, 3, 5}));
    }
}
