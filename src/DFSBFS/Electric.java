package DFSBFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Electric {

    private static int CNT = 0;
    private static final Map<Integer, List<Integer>> WIRE_CONNECT_LIST_MAP = new HashMap<>();
    
    public int solution(int n, int[][] wires) {
        initWireConnectListMap(n);

        for (int[] wire : wires) {
            addConnectedWired(wire[0], wire[1]);
            addConnectedWired(wire[1], wire[0]);
        }

        int min = Integer.MAX_VALUE;
        for (int[] wire : wires) {
            boolean[] visited = new boolean[n+1];
            setTrueWire(wire, visited);

            int v1Cnt = executeDfs(wire[0], visited);
            int v2Cnt = executeDfs(wire[1], visited);
            
            min = Math.min(min, Math.abs(v1Cnt - v2Cnt));
            
        }
        return min;
    }
    
    private void initWireConnectListMap(int n) {
        for(int i = 1; i<= n; i++){
            WIRE_CONNECT_LIST_MAP.put(i, new ArrayList<>());
        }
    }
    private void addConnectedWired(int v1, int v2) {
        List<Integer> v1List = WIRE_CONNECT_LIST_MAP.get(v1);
        v1List.add(v2);
    }
    private void setTrueWire(int[] wire, boolean[] visited) {
        visited[wire[0]] = true;
        visited[wire[1]] = true;
    }
    private int executeDfs(int start, boolean[] visited) {
        CNT = 0;
        dfs(start, visited);
        return CNT;
    }
    private void dfs(int start, boolean[] visited) {
        CNT++;
        visited[start] = true;

        List<Integer> connectedList = WIRE_CONNECT_LIST_MAP.get(start);
        if(connectedList.isEmpty()){
            return;
        }

        for (Integer connectedWire : connectedList) {
            if(!visited[connectedWire]){
                dfs(connectedWire, visited);
            }
        }
    }

    public static void main(String[] args) {
        Electric electric = new Electric();
        int solution = electric.solution(7, new int[][]{
                {1,2},
                {2,7},
                {3,7},
                {3,4},
                {4,5},
                {6,7}
        });
        System.out.println(solution);
    }

}
