package DFSBFS;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DFSStack {

    public static void main(String[] args) {
        List<List<Integer>> adjacentNodeList = initAdjacentNodeList();

        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[adjacentNodeList.size()];

        List<Integer> trackNodeList = new ArrayList<>();

        int firstNode = 1;
        stack.push(firstNode);
        visited[firstNode] = true;

        trackNodeList.add(firstNode);

        while (!stack.isEmpty()){
            int top = stack.peek();

            boolean isAllVisited = true;
            for (int node : adjacentNodeList.get(top)) {
                if(!visited[node]){
                    stack.push(node);
                    visited[node] = true;
                    isAllVisited = false;
                    trackNodeList.add(node);
                    break;
                }
            }

            if(isAllVisited){
                stack.pop();
            }

        }

        System.out.println(trackNodeList.toString());
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
