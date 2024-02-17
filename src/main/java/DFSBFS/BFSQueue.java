package DFSBFS;

import java.util.*;

public class BFSQueue {

    public static void main(String[] args) {
        Map<Integer, List<Integer>> adjacentListMap = initAdjacentListMap();
        Map<Integer, Boolean> visitedMap = initVisitedMap();

        Queue<Integer> queue = new LinkedList<>();
        int start = 1;

        queue.add(start);
        visitedMap.put(start, true);

        while (!queue.isEmpty()){
            int first = queue.poll();
            System.out.println(first);

            List<Integer> nodes = adjacentListMap.get(first);
            for (int node : nodes) {
                if (!visitedMap.get(node)) {
                    queue.add(node);
                    visitedMap.put(node, true);
                }
            }
        }
    }

    private static Map<Integer, Boolean> initVisitedMap() {
        Map<Integer, Boolean> map = new HashMap<>();
        for(int i=1;i<=8;i++){
            map.put(i, false);
        }
        return map;
    }

    private static Map<Integer, List<Integer>> initAdjacentListMap(){
        Map<Integer, List<Integer>> map = new HashMap<>();

        map.put(1, new ArrayList<>());

        map.get(1).add(2);
        map.get(1).add(3);
        map.get(1).add(8);

        map.put(2, new ArrayList<>());
        map.get(2).add(1);
        map.get(2).add(7);

        map.put(3, new ArrayList<>());
        map.get(3).add(4);
        map.get(3).add(5);

        map.put(4, new ArrayList<>());
        map.get(4).add(3);
        map.get(4).add(5);

        map.put(5, new ArrayList<>());
        map.get(5).add(3);
        map.get(3).add(4);

        map.put(6, new ArrayList<>());
        map.get(6).add(7);

        map.put(7, new ArrayList<>());
        map.get(7).add(2);
        map.get(7).add(6);
        map.get(7).add(8);

        map.put(8, new ArrayList<>());
        map.get(8).add(1);
        map.get(8).add(7);

        return map;
    }
}
