package 최단경로;

import java.util.*;

public class Delivery {

    static class Node implements Comparable<Node>{
        private int index;
        private int distance;

        public Node(int index, int distance) {
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
            return this.distance - o.getDistance();
        }
    }
    public int solution(int N, int[][] road, int K) {
        List<List<Node>> graph = initGraph(N, road);

        int[] minCostArrByDijkstar = getMinCostArrByDijkstar(N, 0, graph);

        int cntOverK = getCntOverK(K, minCostArrByDijkstar);

        return cntOverK;
    }

    private List<List<Node>> initGraph(int N, int[][] road) {
        List<List<Node>> graph = new ArrayList<>();

        for(int i = 0; i< N; i++){
            graph.add(new ArrayList<>());
        }

        for (int[] roadInfo : road) {
            int startIndex = roadInfo[0]-1;
            int endIndex = roadInfo[1]-1;
            int distance = roadInfo[2];

            graph.get(startIndex).add(new Node(endIndex, distance));
            graph.get(endIndex).add(new Node(startIndex, distance));
        }
        return graph;
    }

    private int[] getMinCostArrByDijkstar(int N, int startIndex, List<List<Node>> graph){
        int[] costArr = new int[N];
        Arrays.fill(costArr, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(startIndex, 0));

        costArr[startIndex] = 0;
        while (!pq.isEmpty()){
            Node currentNode = pq.poll();

            int currentIndex =  currentNode.getIndex();
            int currentDistance = currentNode.getDistance();

            if(costArr[currentIndex] < currentDistance){
                continue;
            }

            for(int i=0;i<graph.get(currentIndex).size();i++){
                Node ajdNode = graph.get(currentIndex).get(i);
                int targetIndex = ajdNode.getIndex();

                int cost = costArr[currentIndex] + ajdNode.getDistance();
                if(cost < costArr[targetIndex]){
                    costArr[targetIndex] = cost;
                    pq.add(new Node(targetIndex, cost));
                }
            }
        }

        return costArr;
    }

    private int getCntOverK(int K, int[] minCostArrByDijkstar) {
        int cntOverK = 0;
        for(int i = 0; i< minCostArrByDijkstar.length; i++){
            if(minCostArrByDijkstar[i] <= K){
                cntOverK++;
            }
        }
        return cntOverK;
    }

    public static void main(String[] args) {
        Delivery delivery = new Delivery();
        System.out.println(delivery.solution(5, new int[][]{
                {1,2,1}
                ,{2,3,3}
                ,{5,2,2}
                ,{1,4,2}
                ,{5,3,1}
                ,{5,4,2}
        },3));;
    }
}
