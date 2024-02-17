package DFSBFS;

import java.util.*;

public class DFSRecursive {

    private static final Map<Integer, List<Integer>> adjacentListMap = new HashMap<>();
    private static final Map<Integer, Boolean> visitedMap = new HashMap<>();

    public static void main(String[] args) {
        initAdjacentListMap();
        initVisitedMap();
        dfs(1);
    }

    private static void dfs(int start){
        System.out.println(start);
        visitedMap.put(start, true);

        List<Integer> list = adjacentListMap.get(start);
        for (int node : list) {
            if (!visitedMap.get(node)) {
                visitedMap.put(node, true);
                dfs(node);
            }
        }
    }

    private static Map<Integer, Boolean> initVisitedMap() {
        for(int i=1;i<=8;i++){
            visitedMap.put(i, false);
        }
        return visitedMap;
    }

    private static void initAdjacentListMap(){
        adjacentListMap.put(1, new ArrayList<>());

        adjacentListMap.get(1).add(2);
        adjacentListMap.get(1).add(3);
        adjacentListMap.get(1).add(8);

        adjacentListMap.put(2, new ArrayList<>());
        adjacentListMap.get(2).add(1);
        adjacentListMap.get(2).add(7);

        adjacentListMap.put(3, new ArrayList<>());
        adjacentListMap.get(3).add(4);
        adjacentListMap.get(3).add(5);

        adjacentListMap.put(4, new ArrayList<>());
        adjacentListMap.get(4).add(3);
        adjacentListMap.get(4).add(5);

        adjacentListMap.put(5, new ArrayList<>());
        adjacentListMap.get(5).add(3);
        adjacentListMap.get(3).add(4);

        adjacentListMap.put(6, new ArrayList<>());
        adjacentListMap.get(6).add(7);

        adjacentListMap.put(7, new ArrayList<>());
        adjacentListMap.get(7).add(2);
        adjacentListMap.get(7).add(6);
        adjacentListMap.get(7).add(8);

        adjacentListMap.put(8, new ArrayList<>());
        adjacentListMap.get(8).add(1);
        adjacentListMap.get(8).add(7);
    }
}
