package 최단경로;

import java.util.*;

public class DigPrac1 {

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

    private static int[] makeDistanceArr(int n){
        int[] distanceArr = new int[n];
        Arrays.fill(distanceArr, Integer.MAX_VALUE);
        return distanceArr;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int X = scanner.nextInt();

        List<List<Node>> graph = new ArrayList<>();
        for(int i=0;i<N;i++){
            graph.add(new ArrayList<>());
        }

        int destinationIndex = X-1;
        for(int i=0;i<M;i++){
            int startIndex = scanner.nextInt()-1;
            int endIndex = scanner.nextInt()-1;
            int distance = scanner.nextInt();

            graph.get(startIndex).add(new Node(endIndex, distance));
        }

        int max = Integer.MIN_VALUE;
        int[] xDist = dijkstra(N, graph, destinationIndex);
        for(int i=0;i<N;i++){
            if(i == destinationIndex){
                continue;
            }

            int[] dist = dijkstra(N, graph, i);
            int minDistance = dist[destinationIndex] + xDist[i];
            max = Math.max(max, minDistance);
        }

        System.out.println(max);
    }

    private static int[] dijkstra(int N, List<List<Node>> graph, int start) {
        int[] dist = makeDistanceArr(N);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start,0));

        dist[start] = 0;
        while (!pq.isEmpty()){
            Node node = pq.poll();

            int currentDistance = node.getDistance();
            int currentIndex = node.getIndex();

            if(dist[currentIndex] < currentDistance){
                continue;
            }

            for(int j = 0; j< graph.get(currentIndex).size(); j++){
                // 현재 노드를 거쳐서 인접 노드까지 가는 경우
                Node adjNode = graph.get(currentIndex).get(j);

                int targetIndex = adjNode.getIndex();
                int cost = dist[currentIndex] + adjNode.getDistance();

                if(cost < dist[targetIndex]){
                    dist[targetIndex] = cost;
                    pq.add(new Node(targetIndex, cost));
                }
            }
        }
        return dist;
    }
}
