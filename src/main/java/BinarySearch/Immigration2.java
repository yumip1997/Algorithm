package BinarySearch;

import java.util.Arrays;

public class Immigration2 {

    public long solution(int n, int[] times) {
        Arrays.sort(times);

        long min = 1;
        long max = (long) n * times[times.length-1];
        long mid = 0;

        long result = 0;
        while (min <= max){
            mid = (min+max)/2;

            if(isPossibleTime(n, times, mid)){
                max = mid - 1;
                result = mid;
            }else{
                min = mid + 1;
            }
        }

        return result;
    }

    private boolean isPossibleTime(int n, int[] times, long targetTime){
        long totalPeople = 0;
        for (int time : times) {
            totalPeople += targetTime / time;
        }

        return totalPeople >= n;
    }

    public static void main(String[] args) {
        Immigration2 immigration2 = new Immigration2();
        immigration2.solution(6, new int[]{7,10});
    }
}
