package BinarySearch;

import java.util.Arrays;

public class Stone {

    public long solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        int min = 0;
        int max = distance;
        int mid = 0;

        int result = 0;
        while (min <= max){
            mid = (min + max) /2;

            int removeCnt = getRemoveRockCnt(rocks, n, mid, distance);
            if(removeCnt == n) {
                result = mid;
                min = mid+1;
            }else if(removeCnt < n){
                min = mid+1;
            }else{
                max = mid-1;
            }

        }

        return result;
    }

    private int getRemoveRockCnt(int[] rocks, int n,long minRange, long distance){
        int tmpRocks = 0;
        int removeCnt = 0;
        for(int rock : rocks){
            if(rock - tmpRocks < minRange){
                removeCnt++;
            }else{
                tmpRocks = rock;
            }
        }

        if(distance - tmpRocks < minRange){
            removeCnt++;
        }
        return removeCnt;
    }

    public static void main(String[] args) {
        Stone stone = new Stone();
        stone.solution(25, new int[]{2, 14, 11, 21, 17}, 2);
    }
}
