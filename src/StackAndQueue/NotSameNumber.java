package StackAndQueue;

import java.util.Deque;
import java.util.LinkedList;

public class NotSameNumber {

    public int[] solution(int []arr) {
        Deque<Integer> deque = new LinkedList<>();
        for (int i : arr) {
            if (!deque.isEmpty() && deque.peekLast() == i){
                continue;
            }
            deque.addLast(i);
        }

        int[] answer = new int[deque.size()];
        int index = 0;
        while (!deque.isEmpty()){
            answer[index++] = deque.pollFirst();
        }
        return answer;
    }

    public static void main(String[] args) {
        NotSameNumber solution = new NotSameNumber();
        solution.solution(new int[]{1,1,3,3,0,1});
    }
}
