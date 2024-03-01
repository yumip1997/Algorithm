package DFSBFS;

import java.util.*;

public class Island {

    static class Point {
        private int x;
        private int y;

        public Point(int x, int y){
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

    public int[] solution(String[] maps) {
        int colLen = maps.length;
        int rowLen = maps[0].length();

        char[][] mapArr = new char[colLen][rowLen];
        boolean[][] visited = new boolean[colLen][rowLen];

        for(int i=0;i<colLen;i++){
            mapArr[i] = maps[i].toCharArray();
        }

        List<Integer> dayList = new ArrayList<>();
        for(int i=0;i<colLen;i++){
            for(int j=0;j<rowLen;j++){
                if(mapArr[i][j] != 'X' && !visited[i][j]){
                    Queue<Point> queue = new LinkedList<>();
                    visited[i][j] = true;
                    queue.add(new Point(i, j));
                    int cnt = mapArr[i][j] - '0';

                    while (!queue.isEmpty()){
                        Point point = queue.poll();
                        int currentX = point.getX();
                        int currentY = point.getY();

                        //상
                        if(currentX-1 >=0 && mapArr[currentX-1][currentY] != 'X' && !visited[currentX-1][currentY]){
                            visited[currentX-1][currentY] = true;
                            queue.add(new Point(currentX-1, currentY));
                            cnt += (mapArr[currentX-1][currentY] - '0');
                        }
                        //하
                        if(currentX+1 < colLen && mapArr[currentX+1][currentY] != 'X' && !visited[currentX+1][currentY]){
                            visited[currentX+1][currentY] = true;
                            queue.add(new Point(currentX+1, currentY));
                            cnt += (mapArr[currentX+1][currentY] - '0');
                        }

                        //좌
                        if(currentY-1 >= 0 && mapArr[currentX][currentY-1] != 'X' && !visited[currentX][currentY-1]){
                            visited[currentX][currentY-1] = true;
                            queue.add(new Point(currentX, currentY-1));
                            cnt += (mapArr[currentX][currentY-1] - '0');
                        }

                        //우
                        if(currentY+1 < rowLen && mapArr[currentX][currentY+1] != 'X' && !visited[currentX][currentY+1]){
                            visited[currentX][currentY+1] = true;
                            queue.add(new Point(currentX, currentY+1));
                            cnt +=(mapArr[currentX][currentY+1] - '0');
                        }
                    }

                    dayList.add(cnt);
                }
            }
        }

        if(dayList.isEmpty()){
            return new int[]{-1};
        }

        dayList.sort(Comparator.naturalOrder());
        return dayList.stream().mapToInt(e ->e).toArray();
    }

    public static void main(String[] args) {
        Island island = new Island();
        island.solution(new String[]{"X591X","X1X5X","X231X", "1XXX1"});
    }
}
