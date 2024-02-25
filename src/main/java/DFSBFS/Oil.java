package DFSBFS;

import java.util.LinkedList;
import java.util.Queue;

public class Oil {

    class Point {
        private int x;
        private int y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public int solution(int[][] land) {
        int row = land[0].length;
        int col = land.length;

        int max = Integer.MIN_VALUE;
        for (int y=0;y<row;y++) {
            boolean[][] visited = new boolean[col][row];
            Queue<Point> queue = new LinkedList<>();

            int cnt = 0;
            for(int x=0;x<col;x++){
                if(land[x][y] == 1 && !visited[x][y]){
                    visited[x][y] = true;
                    queue.add(new Point(x,y));
                    cnt++;

                    while (!queue.isEmpty()){
                        Point poll = queue.poll();
                        int currentX = poll.getX();
                        int currentY = poll.getY();

                        //상
                        if(currentX-1 >=0 && land[currentX-1][currentY] == 1 && !visited[currentX-1][currentY]){
                            visited[currentX-1][currentY] = true;
                            queue.add(new Point(currentX-1, currentY));
                            cnt++;
                        }
                        //하
                        if(currentX+1 < col && land[currentX+1][currentY] == 1 && !visited[currentX+1][currentY]){
                            visited[currentX+1][currentY] = true;
                            queue.add(new Point(currentX+1, currentY));
                            cnt++;
                        }

                        //좌
                        if(currentY-1 >= 0 && land[currentX][currentY-1] == 1 && !visited[currentX][currentY-1]){
                            visited[currentX][currentY-1] = true;
                            queue.add(new Point(currentX, currentY-1));
                            cnt++;
                        }

                        //우
                        if(currentY+1 < row && land[currentX][currentY+1] == 1 && !visited[currentX][currentY+1]){
                            visited[currentX][currentY+1] = true;
                            queue.add(new Point(currentX, currentY+1));
                            cnt++;
                        }
                    }
                }
            }

            if(max < cnt){
                max = cnt;
            }
        }
        return max;
    }


    public static void main(String[] args) {
        Oil oil = new Oil();
        System.out.println(oil.solution(new int[][]{
                {0, 0, 0, 1, 1, 1, 0, 0}
                , {0, 0, 0, 0, 1, 1, 0, 0}
                , {1, 1, 0, 0, 0, 1, 1, 0}
                , {1, 1, 1, 0, 0, 0, 0, 0}
                , {1, 1, 1, 0, 0, 0, 1, 1}
        }));
    }
}
