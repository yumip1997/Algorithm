package 최단경로;

import java.util.*;

public class Army {

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0;i<n;i++){
            graph.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            int startIndex = road[0] - 1;
            int endIndex = road[1] - 1;

            graph.get(startIndex).add(endIndex);
            graph.get(endIndex).add(startIndex);
        }

        int destinationIndex = destination-1;
        int[] dist = makeDistanceArr(n);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(destinationIndex);
        dist[destinationIndex] = 0;

        while (!queue.isEmpty()){
            Integer currentIndex = queue.poll();

            for(int i=0;i<graph.get(currentIndex).size();i++){
                // 현재지점을 거쳐서 인접 지점에 도착하는 비용
                int cost = dist[currentIndex]+1;
                int adjIndex = graph.get(currentIndex).get(i);

                if(cost < dist[adjIndex]){
                    dist[adjIndex] = cost;
                    queue.add(adjIndex);
                }

            }
        }

        int[] answer = new int[sources.length];
        for(int i=0;i<sources.length;i++){
            int sourceIndex = sources[i]-1;
            if(dist[sourceIndex] == Integer.MAX_VALUE){
                answer[i] = -1;
            }else{
                answer[i] = dist[sourceIndex];
            }
        }

        return answer;
    }

    private int[] makeDistanceArr(int n){
        int[] distanceArr = new int[n];
        Arrays.fill(distanceArr, Integer.MAX_VALUE);
        return distanceArr;
    }

    public static void main(String[] args) {
        Army army = new Army();
        System.out.println(Arrays.toString(army.solution(5, new int[][]{{1,2},{1,4}, {2,4}, {2,5}, {4,5}}, new int[]{1,3,5}, 5)));
    }
}
