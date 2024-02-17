package StackAndQueue;

import java.util.*;

public class Defense {

    public int solution(int n, int k, int[] enemy) {
        List<List<Integer>> list = new ArrayList<>();
        list.sort(Comparator.comparingInt(List::size));
        int answer = k;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0;i<k;i++){
            pq.add(enemy[i]);
        }

        for(int i=k;i<enemy.length;i++){
            pq.add(enemy[i]);

            if(pq.isEmpty()){
                break;
            }

            if(pq.peek() <= n){
                n = n - pq.poll();
                answer++;
            }else{
                break;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Defense defense = new Defense();
        defense.solution(7,100, new int[]{4, 2, 4, 5, 3, 3, 1});
    }
}
