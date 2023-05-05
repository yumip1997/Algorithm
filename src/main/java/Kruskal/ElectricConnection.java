package Kruskal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ElectricConnection {

    static class Edge implements Comparable<Edge> {
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true){
            int m = scanner.nextInt();
            int n = scanner.nextInt();

            if(m == 0 && n ==0){
                break;
            }

            List<Edge> edgeList = new ArrayList<>();
            for(int i=0;i<n;i++){
                int nodeA = scanner.nextInt();
                int nodeB = scanner.nextInt();
                int distance = scanner.nextInt();

                edgeList.add(new Edge(nodeA, nodeB, distance));
            }
            System.out.println(getSaveCost(m, edgeList));
        }


    }

    public static int getSaveCost(int nodeCnt, List<Edge> edgeList){
        return getTotalCost(edgeList) - getMinimumCost(nodeCnt, edgeList);
    }

    private static int getTotalCost(List<Edge> edgeList){
        int result = 0;
        for (Edge edge : edgeList) {
            result += edge.getDistance();
        }
        return result;
    }


    private static int getMinimumCost(int n, List<Edge> edgeList) {
        int[] parent = new int[n];
        for(int i=0;i<n;i++){
            parent[i] = i;
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

    private static int findParent(int x, int[] parent){
        if(x == parent[x]){
            return x;
        }
        return findParent(parent[x], parent);
    }

    private static void unionParent(int a, int b, int[] parent){
        int parentA = findParent(a, parent);
        int parentB = findParent(b, parent);

        if(parentA < parentB){
            parent[parentB] = parentA;
        }else{
            parent[parentA] = parentB;
        }
    }
}
