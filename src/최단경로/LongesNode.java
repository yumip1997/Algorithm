package 최단경로;

import java.util.Arrays;

public class LongesNode {

    private static final int INF = 10000;

    public int solution(int n, int[][] edge) {
        int[][] matrix = new int[n][n];

        initMatrix(n, matrix);
        setMatrixInfo(edge, matrix);

        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                if(matrix[0][i] > matrix[0][k] + matrix[k][i]){
                    matrix[0][i] = matrix[0][k] + matrix[k][i];
                }
            }
        }
        return getMaxCnt(matrix[0]);
    }

    private void initMatrix(int n, int[][] matrix) {
        for(int i = 0; i< n; i++){
            for(int j = 0; j< n; j++){
                if(i == j){
                    matrix[i][j] = 0;
                    continue;
                }
                matrix[i][j] = INF;
            }
        }
    }

    private void setMatrixInfo(int[][] edge, int[][] matrix) {
        for (int[] info : edge) {
            int a = info[0] - 1;
            int b = info[1] - 1;

            matrix[a][b] = 1;
            matrix[b][a] = 1;
        }
    }

    private int getMaxCnt(int[] arr){
        Arrays.sort(arr);
        int max = arr[arr.length-1];
        int cnt = 0;
        for(int i=arr.length-1;i>=0;i--){
            if(arr[i] != max){
                break;
            }
            cnt++;
        }
        return cnt;
    }

    public static void main(String[] args) {
        LongesNode solution = new LongesNode();
        solution.solution(6, new int[][] {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}});
    }
}
