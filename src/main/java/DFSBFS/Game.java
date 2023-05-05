package DFSBFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;

public class Game {

    static class Point {
        private final int x;
        private final int y;
        private final int distance;

        public Point(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getDistance() {
            return distance;
        }
    }

    public int solution(int[][] maps) {
        int startX = 0;
        int startY = 0;

        int targetX = maps.length - 1;
        int targetY = maps[0].length - 1;

        int colLength = maps.length;
        int rowLength = maps[0].length;

        boolean[][] visited = new boolean[maps.length][maps[0].length];
        Point point = new Point(startX, startY, 1);
        Queue<Point> queue = new LinkedList<>();
        queue.add(point);
        visited[startX][startY] = true;

        int totalDistance = -1;
        while (!queue.isEmpty()){
            Point currentPoint = queue.poll();

            int x = currentPoint.getX();
            int y = currentPoint.getY();
            int distance = currentPoint.getDistance();

            if(x == targetX && y == targetY){
                totalDistance = distance;
                break;
            }

            //상
            if(x-1 >=0 && !visited[x-1][y] && maps[x-1][y] != 0){
                queue.add(new Point(x-1, y, distance+1));
                setVisited(x-1, y, visited);
            }
            //하
            if(x+1 < colLength && !visited[x+1][y] && maps[x+1][y] !=0){
                queue.add(new Point(x+1, y, distance+1));
                setVisited(x+1, y, visited);
            }
            //좌
            if(y-1 >=0 && !visited[x][y-1] && maps[x][y-1] != 0){
                queue.add(new Point(x, y-1, distance+1));
                setVisited(x, y-1, visited);
            }

            //우
            if(y+1 < rowLength && !visited[x][y+1] && maps[x][y+1] !=0){
                queue.add(new Point(x, y+1, distance+1));
                setVisited(x, y+1, visited);
            }
        }

        return totalDistance;
    }

    private void setVisited(int x, int y, boolean[][] visited){
        visited[x][y] = true;
    }

    public static void main(String[] args) {
//        Game game = new Game();
//        int solution1 = game.solution(new int[][]{
//                {1,0,1,1,1}
//        ,{1,0,1,0,1}
//        ,{1,0,1,1,1}
//        ,{1,1,1,0,1}
//        ,{0,0,0,0,1}
//        });

        ExecutorService pool = Executors.newFixedThreadPool(10);
        for(int i=0;i<10;i++){
            pool.execute(() -> {
                try{
                    TimeUnit.MICROSECONDS.sleep(300);
                    String name = Thread.currentThread().getName();
                    System.out.println("Thread : " + name);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }
    }
}
