package Greedy;

import java.util.Arrays;
import java.util.Comparator;

public class Camera {

    public int solution(int[][] routes) {
        int answer = 1;
        Arrays.sort(routes, Comparator.comparingInt(o -> o[1]));
        int currentCam = routes[0][1];
        for(int i=1;i<routes.length;i++){
            if(routes[i][0] > currentCam){
                currentCam = routes[i][1];
                answer++;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Camera camera = new Camera();
        camera.solution(new int[][]{
                {-20,-15}
                , {-14,-5}
                , {-18,-13}
                , {-5,-3}
        });
    }
}
