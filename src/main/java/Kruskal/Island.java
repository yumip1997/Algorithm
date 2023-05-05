package Kruskal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/*
가장 적은 비용으로 모든 노드를 연결하기 위해 사용
 */
public class Island {

    class Edge implements Comparable<Edge> {
        private int nodeA;
        private int nodeB;
        private int distance;

        public Edge(int nodeA, int nodeB, int distance) {
            this.nodeA = nodeA;
            this.nodeB = nodeB;
            this.distance = distance;
        }

        public int getNodeA() {
            return nodeA;
        }

        public int getNodeB() {
            return nodeB;
        }

        public int getDistance() {
            return distance;
        }

        @Override
        public int compareTo(Edge o) {
            return Comparator.comparing(Edge::getDistance)
                    .compare(this,o);
        }
    }

    private final List<Edge> edgeList = new ArrayList<>();

    public int solution(int n, int[][] costs) {
        int[] parent = new int[n];
        for(int i=0;i<n;i++){
            parent[i] = i;
        }

        for (int[] cost : costs) {
            edgeList.add(new Edge(cost[0], cost[1], cost[2]));
        }

        //정렬
        edgeList.sort(Edge::compareTo);

        int totalCost = 0;
        for (Edge edge : edgeList) {
            int distance = edge.getDistance();
            int nodeA = edge.getNodeA();
            int nodeB = edge.getNodeB();

            if(findParent(nodeA, parent) == findParent(nodeB, parent)){
                continue;
            }

            unionParent(nodeA, nodeB, parent);
            totalCost += distance;

        }
        return totalCost;
    }

    private int findParent(int x, int[] parent){
        if(x == parent[x]){
            return x;
        }
        return findParent(parent[x], parent);
    }

    private void unionParent(int a, int b, int[] parent){
        int parentA = findParent(a, parent);
        int parentB = findParent(b, parent);

        if(parentA < parentB){
            parent[b] = parentA;
        }else{
            parent[a] = parentB;
        }
    }

    public static void main(String[] args) {
        Island island = new Island();
        island.solution(4, new int[][]{{0,1,1}, {0,2,2}, {1,2,5}, {1,3,1}, {2,3,8}});


        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();

        int[][] info = new int[n][3];

        int index = 0;
        while (true){
            int nodeA = scanner.nextInt();
            int nodeB = scanner.nextInt();
            if(nodeA ==0 && nodeB == 0){
                break;
            }
            int distance = scanner.nextInt();

            info[index][0] = nodeA;
            info[index][1] = nodeB;
            info[index][2] =  distance;
            index++;
        }

        int solution = island.solution(m, info);
        System.out.println(solution);
    }
}
