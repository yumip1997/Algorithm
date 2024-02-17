package DP;

import java.util.Arrays;

class TransWord {
    public int solution(int x, int y, int n) {
        int answer = 0;
        int[] dp = new int[y+1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[x] = 0;
        for(int i=x+1;i<=y;i++){
            if(i-n >= x && dp[i-n] != Integer.MAX_VALUE){
                dp[i] = Math.min(dp[i], dp[i-n]+1);
            }

            if(i%2 == 0 && dp[i/2] != Integer.MAX_VALUE){
                dp[i] = Math.min(dp[i], dp[i/2]+1);
            }

            if(i%3 == 0 && dp[i/3] != Integer.MAX_VALUE){
                dp[i] = Math.min(dp[i], dp[i/3]+1);
            }
        }

        return dp[y] == Integer.MAX_VALUE ? -1 : dp[y];
    }
}