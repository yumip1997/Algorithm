package Implementation;

import java.util.Arrays;

public class Kakao_Road {

    public int solution(int[] stones, int k) {
        int rangeZeroCnt = Integer.MAX_VALUE;
        for(int i=0;i<=stones.length-k;i++){
            int[] copiedArr = Arrays.copyOfRange(stones, i, i + 3);
            Arrays.sort(copiedArr);
            rangeZeroCnt = Math.min(rangeZeroCnt, copiedArr[2]);
        }
        return rangeZeroCnt;
    }

    public static void main(String[] args) {
        Kakao_Road road = new Kakao_Road();
        road.solution(new int[]{2, 4, 5, 3, 2, 1, 4, 2, 5, 1}, 3);
    }
}
