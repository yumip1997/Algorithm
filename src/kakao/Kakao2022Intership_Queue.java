package kakao;

import java.util.LinkedList;
import java.util.Queue;

public class Kakao2022Intership_Queue {
    static class Solution {
        public int solution(int[] queue1, int[] queue2) {

            long firstSum = getSum(queue1);
            long secondSum = getSum(queue2);

            if((firstSum + secondSum) % 2 != 0) return -1;

            Queue<Integer> firstQueue = toQueue(queue1);
            Queue<Integer> secondQueue = toQueue(queue2);

            int firstQueueCnt = 0;
            int secondQueueCnt = 0;
            int limitCnt = firstQueue.size();
            long targetSum = (firstSum + secondSum) / 2;

            while (!(firstSum == targetSum)){
                if(firstQueueCnt >= limitCnt && secondQueueCnt >= limitCnt){
                    return -1;
                }

                if(firstSum > secondSum){
                    firstSum -= firstQueue.peek();
                    secondSum += firstQueue.peek();
                    secondQueue.add(firstQueue.poll());
                    firstQueueCnt++;
                }else {
                    firstSum += secondQueue.peek();
                    secondSum -= secondQueue.peek();
                    firstQueue.add(secondQueue.poll());
                    secondQueueCnt++;
                }
            }

            return firstQueueCnt + secondQueueCnt;
        }

        public long getSum(int[] queue){
            long sum = 0;
            for (int i : queue) {
                sum += i;
            }
            return sum;
        }

        private Queue<Integer> toQueue(int[] arr){
            Queue<Integer> queue = new LinkedList<>();
            for (int i : arr) {
                queue.add(i);
            }
            return queue;
        }
    }
}
