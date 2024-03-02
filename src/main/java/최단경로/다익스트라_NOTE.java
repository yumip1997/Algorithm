package 최단경로;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class 다익스트라_NOTE {

    static class Node implements Comparable<Node>{
        private int index;
        private int distance;

        public Node(int index, int distance){
            this.index = index;
            this.distance = distance;
        }

        public int getIndex() {
            return index;
        }

        public int getDistance() {
            return distance;
        }

        @Override
        public int compareTo(Node o) {
            return this.distance - o.distance;
        }
    }

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        List<List<Node>> graph = new ArrayList<>();
        for (int i=0;i<n;i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            int startIndex = road[0]-1;
            int endIndex = road[1]-1;

            graph.get(startIndex).add(new Node(endIndex, 1));
            graph.get(endIndex).add(new Node(startIndex, 1));
        }


        int[] answer = new int[sources.length];
        int armyIndex = destination-1;
        for (int i=0;i<sources.length;i++) {
            int startIndex = sources[i]-1;
            int[] distance = makeDistanceArr(n);

            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.add(new Node(startIndex, 0));

            distance[startIndex] = 0;
            while (!pq.isEmpty()){
                Node node = pq.poll();

                int currentDistance = node.getDistance();
                if(currentDistance == armyIndex){
                    break;
                }
                int currentIndex = node.getIndex();

                if(distance[currentIndex] < currentDistance){
                    continue;
                }

                for(int j=0;j<graph.get(currentIndex).size();j++){
                    // 현재 노드를 거쳐서 인접 노드까지 가는 경우
                    Node adjNode = graph.get(currentIndex).get(j);

                    int targetIndex = adjNode.getIndex();
                    int cost = distance[currentIndex] + adjNode.getDistance();

                    if(cost < distance[targetIndex]){
                        distance[targetIndex] = cost;
                        pq.add(new Node(targetIndex, cost));
                    }
                }
            }

            answer[i] = distance[armyIndex] == Integer.MAX_VALUE ? -1 : distance[armyIndex];
        }

        return answer;
    }

    private int[] makeDistanceArr(int n){
        int[] distanceArr = new int[n];
        Arrays.fill(distanceArr, Integer.MAX_VALUE);
        return distanceArr;
    }
}
