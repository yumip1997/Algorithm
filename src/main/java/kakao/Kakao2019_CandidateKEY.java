package kakao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Kakao2019_CandidateKEY {

    public static List<Set<Integer>> combList = new ArrayList<>();

    public static void makeCombination(int[] arr, boolean[] visited, int depth, int n, int r){
        if(r == 0){
            Set<Integer> tempCombSet = new HashSet<>();
            for(int i=0;i<visited.length;i++){
                if(visited[i]){
                    tempCombSet.add(arr[i]);
                }
            }
            combList.add(tempCombSet);
            return;
        }

        for(int i=depth;i<n;i++){
            if(!visited[i]){
                visited[i] = true;
                makeCombination(arr, visited, i+1, n, r-1);
                visited[i] = false;
            }
        }
    }

    private static List<List<Integer>> getCombList(){
        return new ArrayList<>();
    }

    private static List<Set<Integer>> makeUniqueKeyCombList(String[][] relation){
        List<Set<Integer>> uniqueKeyCombList = new ArrayList<>();
        int uniqueSize = relation.length;

        for (Set<Integer> combIndexes : combList) {
            Set<String> tempSet = new HashSet<>();
            for (String[] strings : relation) {
                String tempStr = "";
                for (int combIndex : combIndexes) {
                    tempStr += strings[combIndex];
                }
                tempSet.add(tempStr);
            }
            if (tempSet.size() == uniqueSize) {
                uniqueKeyCombList.add(combIndexes);
            }
        }

        return uniqueKeyCombList;
    }

    private static List<Set<Integer>> makeMinimumKeyCombList(List<Set<Integer>> uniqueKeyCombList){
        List<Set<Integer>> minimunKeyCombList = new ArrayList<>(uniqueKeyCombList);

        Set<Integer> removingIndexSet = new HashSet<>();
        for(int i=0;i<minimunKeyCombList.size();i++){
            Set<Integer> indexElements = minimunKeyCombList.get(i);

            for (int j=i+1;j<minimunKeyCombList.size();j++){
                Set<Integer> indexSet = minimunKeyCombList.get(j);
                if(indexSet.containsAll(indexElements)){
                    removingIndexSet.add(j);
                }
            }
        }

        int cnt = 0;
        for (int index : removingIndexSet) {
            Set<Integer> removing = minimunKeyCombList.get(index - cnt);
            minimunKeyCombList.remove(removing);
            cnt++;
        }

        return minimunKeyCombList;
    }

    public static void main(String[] args) {

        String[][] relation = {
                {"100", "ryan", "music", "2"},
                {"200", "apeach", "math", "2"},
                {"300", "tube", "computer", "3"},
                {"400", "con", "computer", "4"},
                {"500", "muzi", "music", "3"},
                {"600", "apeach", "music", "2"}
        };

        int n = relation[0].length;
        int[] indexArr = new int[n];
        for (int i = 0; i < n; i++) {
            indexArr[i] = i;
        }

        for (int i = 1; i <= n; i++) {
            boolean[] visited = new boolean[n];
            makeCombination(indexArr, visited, 0, n, i);
        }

        List<Set<Integer>> uniqueKeyComList = makeUniqueKeyCombList(relation);
        List<Set<Integer>> minimumKeyCombList = makeMinimumKeyCombList(uniqueKeyComList);
    }
}
