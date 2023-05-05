package 순열조합;

import java.util.*;
import java.util.stream.Collectors;

public class 순열조합_NOTE {

    public static final List<List<Integer>> combList = new ArrayList<>();
    public static final List<List<Integer>> perList = new ArrayList<>();

    public static void makeCombination(int[] arr, boolean[] visited, int depth, int n, int r){
        if(r == 0){
            List<Integer> tempCombList = new ArrayList<>();
            for(int i=0;i<visited.length;i++){
                if(visited[i]){
                    tempCombList.add(arr[i]);
                }
            }
            combList.add(tempCombList);
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

    public static Map<Integer, List<List<Integer>>> makeCombNumCombListMap(){
        return combList.stream()
                .collect(Collectors.groupingBy(List::size));
    }

    public static void makePermutation(int[] arr, int[] output, boolean[] visited, int depth, int n, int r){
        if(depth == r){
            List<Integer> tempPerList = new ArrayList<>();
            for(int i=0;i<depth;i++){
               tempPerList.add(output[i]);
            }
            perList.add(tempPerList);
            return;
        }

        for(int i=0;i<n;i++){
            if(visited[i] !=true){
                visited[i] = true;
                output[depth] = arr[i];
                makePermutation(arr, output, visited, depth+1, n, r);
                visited[i] = false;
            }
        }
    }

    public static Map<Integer, List<List<Integer>>> makePerNumPerListMap(){
        return perList.stream()
                .collect(Collectors.groupingBy(List::size));
    }


    public static void main(String[] args) {
        int[] arr = {1,2,3,4};

        for(int i=1;i<=arr.length;i++){
            boolean[] visited = new boolean[arr.length];
            makeCombination(arr, visited, 0, arr.length, i);
        }

        for(int i=1;i<=arr.length;i++){
            boolean[] visited = new boolean[arr.length];
            int[] output = new int[i];
            makePermutation(arr, output, visited, 0, arr.length, i);
        }

        Map<Integer, List<List<Integer>>> combNumCombListMap = makeCombNumCombListMap();
        Map<Integer, List<List<Integer>>> perNumPerListMap = makePerNumPerListMap();
        System.out.println("확인");
    }
}
