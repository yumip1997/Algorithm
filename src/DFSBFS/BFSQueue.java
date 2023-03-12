package DFSBFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSQueue {

    public static void main(String[] args) {
        List<List<Integer>> adjacentNodeList = initAdjacentNodeList();
        List<Integer> tracker = new ArrayList<>();


        boolean[] visited = new boolean[adjacentNodeList.size()];
        Queue<Integer> queue = new LinkedList<>();
        int start = 1;

        queue.add(start);
        visited[start] = true;
        tracker.add(start);


        while (!queue.isEmpty()){
            int first = queue.poll();

            List<Integer> nodes = adjacentNodeList.get(first);
            for (int node : nodes) {
                if (!visited[node]) {
                    queue.add(node);
                    visited[node] = true;
                    tracker.add(node);
                }
            }
        }

        System.out.println(tracker.toString());
    }
    private static List<List<Integer>> initAdjacentNodeList() {
        List<List<Integer>> adjacentNodeList = new ArrayList<>();
        adjacentNodeList.add(new ArrayList<>());
        adjacentNodeList.add(new ArrayList<>());

        adjacentNodeList.get(1).add(2);
        adjacentNodeList.get(1).add(3);
        adjacentNodeList.get(1).add(8);

        adjacentNodeList.add(new ArrayList<>());
        adjacentNodeList.get(2).add(1);
        adjacentNodeList.get(2).add(7);

        adjacentNodeList.add(new ArrayList<>());
        adjacentNodeList.get(3).add(4);
        adjacentNodeList.get(3).add(5);

        adjacentNodeList.add(new ArrayList<>());
        adjacentNodeList.get(4).add(3);
        adjacentNodeList.get(4).add(5);

        adjacentNodeList.add(new ArrayList<>());
        adjacentNodeList.get(5).add(3);
        adjacentNodeList.get(3).add(4);

        adjacentNodeList.add(new ArrayList<>());
        adjacentNodeList.get(6).add(7);

        adjacentNodeList.add(new ArrayList<>());
        adjacentNodeList.get(7).add(2);
        adjacentNodeList.get(7).add(6);
        adjacentNodeList.get(7).add(8);

        adjacentNodeList.add(new ArrayList<>());
        adjacentNodeList.get(8).add(1);
        adjacentNodeList.get(8).add(7);

        return adjacentNodeList;
    }
}
