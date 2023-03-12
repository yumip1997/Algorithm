package DFSBFS;

import java.util.*;

public class Network {

    public static void main(String[] args) {

        int[][] computers = {
                {1, 1, 0}
                , {1, 1, 0}
                , {0, 0, 1}
        };

        int groupCntByDfs = dfsByStack(computers);
    }

    private static int dfsByRecursive(int[][] computers){
        int groupCnt = 0;

        boolean[] visited = new boolean[computers.length];
        for(int i=0;i<computers.length;i++){
            if(visited[i]){
                continue;
            }

            dfs(i, computers, visited);
            groupCnt++;

        }
        return groupCnt;
    }

    private static void dfs(int current, int[][] computers, boolean[] visited){
        visited[current] = true;

        for(int i=0;i<computers[current].length;i++){
            if(current == i | computers[current][i] == 0){
                continue;
            }

            if(!visited[i]){
                dfs(i, computers, visited);
            }
        }
    }

    private static int dfsByStack(int[][] computers) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[computers.length];
        int groupCnt = 0;
        for(int i = 0; i< computers.length; i++){
            if(visited[i]){
                continue;
            }

            stack.push(i);
            visited[i] = true;

            while (!stack.isEmpty()){
                int top = stack.peek();

                boolean isAllVisited = true;
                for(int j = 0; j< computers[top].length; j++){
                    if(j == top || computers[top][j] == 0){
                        continue;
                    }

                    if(!visited[j]){
                        visited[j] = true;
                        stack.push(j);

                        isAllVisited = false;
                        break;
                    }
                }

                if(isAllVisited){
                    stack.pop();
                }
            }

            groupCnt++;
        }
        return groupCnt;
    }
}
