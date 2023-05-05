package Implementation;

import java.util.ArrayList;
import java.util.List;

public class Dungeons {

    private static final List<List<Integer>> perList = new ArrayList<>();

    public int solution(int k, int[][] dungeons) {
        int answer = -1;

        int len = dungeons.length;
        int[] indexArr = new int[len];
        int[] indexPermutationArr = new int[len];
        boolean[] visited = new boolean[len];

        for(int i=0;i<len;i++){
            indexArr[i] = i;
        }

        makePermutation(indexArr, indexPermutationArr, visited, 0, len, len);

        int maxCnt = 0;
        for (List<Integer> permutationIndexList : perList) {
            int remain = k;
            int cnt = 0;
            for (int index : permutationIndexList) {
                int[] dungeon = dungeons[index];

                if(remain <  dungeons[index][0]){
                    break;
                }

                remain -=  dungeons[index][1];
                cnt++;
            }

            if(maxCnt < cnt){
                maxCnt = cnt;
            }
        }
        return maxCnt;
    }


    private void makePermutation(int[] arr, int[] output, boolean[] visited, int depth, int n, int r){
        if(depth == r){
            List<Integer> elements = new ArrayList<>();
            for(int i=0;i<depth;i++){
                elements.add(output[i]);
            }
            perList.add(elements);
            return;
        }

        for(int i=0;i<n;i++){
            if(!visited[i]){
                visited[i] = true;
                output[depth] = arr[i];
                makePermutation(arr, output, visited, depth+1, n, r);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Dungeons dungeons = new Dungeons();
        int solution = dungeons.solution(80, new int[][]{{80, 20}, {50, 40}, {30, 10}});
    }

}
