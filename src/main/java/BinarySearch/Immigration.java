package BinarySearch;

import java.util.Arrays;

public class Immigration {

    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long start = 0;
        long end = n * (long) times[times.length-1];
        long mid = 0;

        long minTime = Long.MAX_VALUE;
        while(start <= end){
            mid = (start + end) / 2;

            long totalPeople = 0;
            for(long time : times){
                totalPeople += mid / time;
            }

            if(totalPeople >= n){
                end = mid -1;
                minTime = Math.min(mid, minTime);
            }else{
                start =  mid+1;
            }
        }

        return minTime;
    }

}
