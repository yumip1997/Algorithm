package DFSBFS;

import java.util.Scanner;
import java.util.Stack;

public class SafeZone {

    static class Zone {
        private final int m;
        private final int n;

        public Zone(int m, int n){
            this.m = m;
            this.n = n;
        }

        public int getM() {
            return m;
        }

        public int getN() {
            return n;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[][] matrix = new int[N][N];
        int maxHeight = 0;
        int minHeight = Integer.MAX_VALUE;

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                matrix[i][j] = scanner.nextInt();

                if(matrix[i][j] < minHeight){
                    minHeight = matrix[i][j];
                }

                if(matrix[i][j] > maxHeight){
                    maxHeight = matrix[i][j];
                }
            }
        }

        int max = 0;
        for(int k=minHeight;k<=maxHeight;k++){
            boolean[][] visited = new boolean[N][N];
            int safeZoneCnt = 0;

            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(matrix[i][j] < k || visited[i][j]){
                        continue;
                    }

                    Stack<Zone> stack = new Stack<>();
                    stack.push(new Zone(i,j));

                    while(!stack.isEmpty()){
                        Zone topZone = stack.pop();

                        int m = topZone.getM();
                        int n = topZone.getN();

                        //상
                        if(m-1 >= 0 && !visited[m-1][n] && matrix[m-1][n] >= k){
                            visited[m-1][n] = true;
                            stack.push(new Zone(m-1,n));
                        }
                        //하
                        if(m+1 < N && !visited[m+1][n] && matrix[m+1][n] >= k){
                            visited[m+1][n] = true;
                            stack.push(new Zone(m+1, n));
                        }
                        //좌
                        if(n-1 >= 0 && !visited[m][n-1] && matrix[m][n-1] >= k){
                            visited[m][n-1] = true;
                            stack.push(new Zone(m,n-1));
                        }
                        //우
                        if(n+1 < N && !visited[m][n+1] && matrix[m][n+1] >= k){
                            visited[m][n+1] = true;
                            stack.push(new Zone(m,n+1));
                        }
                    }

                    safeZoneCnt++;
                }
            }

            if(safeZoneCnt >= max){
                max = safeZoneCnt;
            }
        }

        System.out.println(max);

        scanner.close();
    }
}
