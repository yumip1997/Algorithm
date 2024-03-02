package StackAndQueue;

import java.util.Comparator;
import java.util.PriorityQueue;

public class TiredDegree2 {

    public long solution(int n, int[] works) {
        long sumWorkAmount = sumWorkAmount(works);
        if(sumWorkAmount <= n){
            return 0;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int work : works) {
            pq.add(work);
        }

        while (n > 0 && !pq.isEmpty()){
            int maxWork = pq.poll();
            maxWork--;
            pq.add(maxWork);
            n--;
        }

        long totalTriedDegree =0;
        while (!pq.isEmpty()){
            int remainTriedDegree = pq.poll();
            totalTriedDegree += ((long) remainTriedDegree * remainTriedDegree);
        }
        return totalTriedDegree;
    }

    private long sumWorkAmount(int[] works){
        long sum = 0;
        for (int work : works) {
            sum += work;
        }
        return sum;
    }

    public static void main(String[] args) {
        TiredDegree2 tiredDegree2 = new TiredDegree2();
        long solution = tiredDegree2.solution(4, new int[]{4, 3, 3});
        System.out.println(solution);
    }
}
