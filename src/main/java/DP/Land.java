package DP;

public class Land {

    public int solution(int[][] land){
        int[][] dp = new int[land.length][4];
        dp[0][0] = land[0][0];
        dp[0][1] = land[0][1];
        dp[0][2] = land[0][2];
        dp[0][3] = land[0][3];

        for(int i=1;i<land.length;i++){
            dp[i][0] = land[i][0] + getMax(dp[i-1][1],dp[i-1][2], dp[i-1][3]);
            dp[i][1] = land[i][1] + getMax(dp[i-1][0],dp[i-1][2], dp[i-1][3]);
            dp[i][2] = land[i][2] + getMax(dp[i-1][0],dp[i-1][1], dp[i-1][3]);
            dp[i][3] = land[i][3] + getMax(dp[i-1][0],dp[i-1][1], dp[i-1][2]);
        }

        int max = Integer.MIN_VALUE;
        for(int i=0;i<4;i++){
            if(max < dp[land.length-1][i]){
                max = dp[land.length-1][i];
            }
        }

        return max;
    }

    private int getMax(int a, int b, int c){
        int max = Math.max(a,b);
        return Math.max(max, c);
    }
    public static void main(String[] args) {
        Land land = new Land();
        land.solution(new int[][]{
                {1,2,3,5},
                {5,6,7,8},
                {4,3,2,1}
        });
    }
}
