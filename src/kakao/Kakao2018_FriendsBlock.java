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
    private static final char DELETED_CHAR = 'X';

    public static void main(String[] args) {
        String[] board = {
                "AAAAA","AUUUA","AUUAA","AAAAA"
        };

        int m = board.length;
        int n = board[0].length();

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

        int cnt = getDeletedCnt();
        System.out.println(getDeletedCnt());
    }

    private static int getDeletedCnt() {
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

    private static void initMatrixInfo(int m, int n, String[] board) {
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
                //현재위치
                int currentM = i;
                int currentN = j;

                //현재위치 -> 오른쪽
                int rightM = i;
                int rightN = j+1;
                if(rightN == LIMIT_N) continue;
                while (matrix[rightM][rightN] == DELETED_CHAR){
                    rightN++;

                    if(rightN == LIMIT_N){
                        break;
                    }
                }
                if(rightN == LIMIT_N) continue;

                //현재위치 -> 오른쪽 -> 아래
                int rightDownM = i+1;
                int rightDownN = j+1;
                if(rightDownM == LIMIT_M) continue;
                while (matrix[rightDownM][rightDownN] == DELETED_CHAR){
                    rightDownM++;

                    if(rightDownM == LIMIT_M){
                        break;
                    }
                }
                if(rightDownM == LIMIT_M) continue;

                //현재위채 -> 오른쪽 -> 아래 -> 왼쪽
                int rightDownLeftM = i+1;
                int rightDownLeftN = j;
                if(rightDownLeftM == LIMIT_M) continue;
                while (matrix[rightDownLeftM][rightDownLeftN] == DELETED_CHAR){
                    rightDownLeftM++;

                    if(rightDownLeftM == LIMIT_M){
                        break;
                    }
                }
                if(rightDownLeftM == LIMIT_M) continue;

                char c = matrix[currentM][currentN];
                if(c == matrix[rightM][rightN] && c == matrix[rightDownM][rightDownN]
                        && c == matrix[rightDownLeftM][rightDownLeftN]){
                    points.add(new Point(currentM, currentN));
                    points.add(new Point(rightM, rightN));
                    points.add(new Point(rightDownM, rightDownN));
                    points.add(new Point(rightDownLeftM, rightDownLeftN));
                }
            }
        }

        return points;
    }

    private static void setDeletedChar(List<Point> points){
        for (Point point : points) {
            int m = point.getM();
            int n = point.getN();
            matrix[m][n] = DELETED_CHAR;
        }
    }

    private static void changLocation() {
        for(int i=LIMIT_M-1;i>0;i--){
            for(int j=LIMIT_N-1;j>0;j--){
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
}
