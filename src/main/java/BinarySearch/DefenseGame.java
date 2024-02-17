package BinarySearch;

import java.util.Arrays;

public class DefenseGame {

    public int solution(int n, int k, int[] enemy) {
        int start = 0;
        int end = enemy.length-1;

        int maxRound = Integer.MIN_VALUE;
        while (start <= end){
            int mid = (start + end) /2;

            if(mid <= k){
                start = mid + 1;
                maxRound = Math.max(mid, maxRound);
                continue;
            }

            int[] tempArr = Arrays.copyOfRange(enemy, 0, mid+1);
            Arrays.sort(tempArr);

            long enemySum = 0;
            for(int i=0;i<=mid-k;i++){
                enemySum += tempArr[i];
            }

            if(enemySum <= n){
                start = mid +1;
                maxRound = Math.max(mid, maxRound);
            }else{
                end = mid -1;
            }
        }
        return maxRound+1;
    }

    public static void main(String[] args) {
        DefenseGame defenseGame = new DefenseGame();
        int[] ints = {4, 2, 4, 5, 3, 3, 1};
        int[] ints1 = Arrays.copyOfRange(ints, 0, 3);
        System.out.println(Arrays.toString(ints1));
        defenseGame.solution(7, 3, new int[]{4, 2, 4, 5, 3, 3, 1});

        int i = 1000000;
        System.out.println(Long.MAX_VALUE >= i);
    }
}
