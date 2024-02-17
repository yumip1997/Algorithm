package 문제풀이;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Arrays;
import java.util.List;

public class Test1 {

    /*
     * Complete the 'getMinCost' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY cost
     *  2. INTEGER_ARRAY time
     */

    public static int getMinCost(int[] cost, int[] time) {
        int n = cost.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);

        // paid 서버에서 첫 번째 작업을 실행
        dp[0] = cost[0];

        for (int i = 1; i < n; i++) {
            // paid 서버에서 작업을 실행할 경우의 비용
            dp[i] = Math.min(dp[i], dp[i - 1] + cost[i]);

            // free 서버에서 작업을 실행할 경우의 비용
            if (i > 1) {
                dp[i] = Math.min(dp[i], dp[i - 2]);
            }

            // paid 서버에서 두 개의 작업을 실행한 경우의 비용
            dp[i] = Math.min(dp[i], dp[i - 1] + cost[i] + time[i - 1]);
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        int minCost = getMinCost(new int[]{1,1,3,4}, new int[]{3, 1, 2, 3});
        System.out.println(minCost);
    }

}

