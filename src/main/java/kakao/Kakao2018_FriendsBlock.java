package kakao;

import java.util.*;

public class Kakao2018_FriendsBlock {
    static class Point{
        private int m;
        private int n;

        public Point(int m, int n) {
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

    private static char[][] matrix = null;
    private static int LIMIT_M = 0;
    private static int LIMIT_N = 0;
    private static final char DELETED_CHAR = ' ';

    public int solution(int m, int n, String[] board) {
        initMatrixInfo(m, n, board);
        LIMIT_M = m;
        LIMIT_N = n;

        while (true){
            List<Point> sameBlockPointList = getSameBlockPointList();
            if(sameBlockPointList.size() == 0){
                break;
            }
            setDeletedChar(sameBlockPointList);
            changLocation();
        }

        return getDeletedCnt();
    }

    private void initMatrixInfo(int m, int n, String[] board) {
        matrix = new char[m][n];

        for(int i = 0; i< m; i++){
            String str = board[i];
            for(int j = 0; j< n; j++){
                matrix[i][j] = str.charAt(j);
            }
        }
    }

    private static List<Point> getSameBlockPointList() {
        List<Point> points = new ArrayList<>();

        for(int i=0;i<LIMIT_M;i++){
            for(int j=0;j<LIMIT_N;j++){
                if(matrix[i][j] == DELETED_CHAR){
                    continue;
                }

                if(i+1 == LIMIT_M || j+1 == LIMIT_N){
                    continue;
                }

                char c = matrix[i][j];
                if(c == matrix[i][j+1] && c == matrix[i+1][j+1]
                        && c == matrix[i+1][j]){
                    points.add(new Point(i, j));
                    points.add(new Point(i, j+1));
                    points.add(new Point(i+1, j+1));
                    points.add(new Point(i+1, j));
                }
            }
        }

        return points;
    }

    private void setDeletedChar(List<Point> points){
        for (Point point : points) {
            int m = point.getM();
            int n = point.getN();
            matrix[m][n] = DELETED_CHAR;
        }
    }

    private void changLocation() {
        for(int i=LIMIT_M-1;i>=0;i--){
            for(int j=LIMIT_N-1;j>=0;j--){
                if(matrix[i][j] == DELETED_CHAR){
                    continue;
                }

                //밑으로 떨어뜨리기
                int currentM = i;
                while(currentM < LIMIT_M){
                    if(currentM+1 == LIMIT_M || matrix[currentM+1][j] != DELETED_CHAR){
                        break;
                    }
                    currentM++;
                }

                if(i != currentM){
                    matrix[currentM][j] = matrix[i][j];
                    matrix[i][j] = DELETED_CHAR;
                }
            }
        }
    }

    private int getDeletedCnt() {
        int cnt = 0;
        for(int i=0;i<LIMIT_M;i++){
            for(int j=0;j<LIMIT_N;j++){
                if(matrix[i][j] != DELETED_CHAR){
                    continue;
                }
                cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        String[] board = {
                "TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"
        };

        Kakao2018_FriendsBlock solution = new Kakao2018_FriendsBlock();
        int solution1 = solution.solution(board.length, board[0].length(), board);
        System.out.println(solution1);
    }
}
