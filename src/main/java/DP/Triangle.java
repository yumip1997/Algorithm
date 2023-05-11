package DP;

public class Triangle {

    public int solution(int[][] triangle) {
        int len = triangle.length;
        int[][] matrix = initMatrix(triangle, len);

        int[][] dp = new int[len][len];

        dp[0][0] = triangle[0][0];

        for(int i=1;i<triangle.length;i++){
            for(int j=0;j<triangle[i].length;j++){
                int currentNum = matrix[i][j];
                if(j == 0){
                    dp[i][j] =  currentNum + dp[i-1][j];
                }else{
                    dp[i][j] =  currentNum + Math.max(dp[i-1][j-1], dp[i-1][j]);
                }
            }
        }

        int max = findMaxNum(dp[len - 1]);

        return max;
    }

    private int findMaxNum(int[] ints) {
        int max = Integer.MIN_VALUE;
        for (int i : ints) {
            if(i > max){
                max = i;
            }
        }
        return max;
    }

    private int[][] initMatrix(int[][] triangle, int len) {
        int[][] matrix = new int[len][len];
        for(int i = 0; i< triangle.length; i++){
            for(int j = 0; j< triangle[i].length; j++){
                matrix[i][j] = triangle[i][j];
            }
        }
        return matrix;
    }


    public static void main(String[] args) {
        Triangle triangle = new Triangle();
        int solution = triangle.solution(new int[][]{
                {7},
                {3, 8},
                {8, 1, 0},
                {2, 7, 4, 4},
                {4, 5, 2, 6, 5}
        });

        System.out.println(solution);
    }
}
