package StackAndQueue;

import java.util.Comparator;
import java.util.PriorityQueue;

public class TriedDegree {

    public long solution(int n, int[] works) {
        long totalWorks = sumWorks(works);
        if(totalWorks <= n){
            return 0;
        }

        PriorityQueue<Integer> triedDegreeQueue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int work : works) {
            triedDegreeQueue.add(work);
        }

        while(n > 0 && !triedDegreeQueue.isEmpty()){
            int mostHigherDegree = triedDegreeQueue.poll();
            mostHigherDegree--;
            triedDegreeQueue.add(mostHigherDegree);
            n--;
        }

        long answer = 0;
        while (!triedDegreeQueue.isEmpty()){
            Integer remainedTriedDegree = triedDegreeQueue.poll();
            answer += ((long) remainedTriedDegree * remainedTriedDegree);
        }

        return answer;
    }

    private long sumWorks(int[] works){
        long totalWorks =  0;
        for (int work : works) {
            totalWorks += work;
        }
        return totalWorks;
    }

    public static void main(String[] args) {
        TriedDegree triedDegree = new TriedDegree();
        long solution = triedDegree.solution(1, new int[]{2, 1, 2});
        System.out.println(solution);
    }
}
