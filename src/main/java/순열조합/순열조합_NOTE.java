package 순열조합;

import java.util.*;
import java.util.stream.Collectors;

public class 순열조합_NOTE {

    public static final List<List<Integer>> permList = new ArrayList<>();
    public static final List<List<Integer>> duplicatPermList = new ArrayList<>();
    public static final List<List<Integer>> combList = new ArrayList<>();
    public static final List<List<Integer>> duplicateCombList = new ArrayList<>();

    private static void makePerm(int[] arr, int[] temp, int r, int current, boolean[] visited){
        if(r == current){
            List<Integer> tempCombList = new ArrayList<>();
            for(int i=0;i<r;i++){
                tempCombList.add(temp[i]);
            }
            permList.add(tempCombList);
            return;
        }

        for(int i=0;i<arr.length;i++){
            if(!visited[i]){
                visited[i] = true;
                temp[current] = arr[i];
                makePerm(arr, temp, r, current+1, visited);
                visited[i] = false;
            }
        }
    }

    private static void makeDuplicatePerm(int[] arr, int[] temp, int r, int current){
        if(r == current){
            List<Integer> tempCombList = new ArrayList<>();
            for(int i=0;i<r;i++){
                tempCombList.add(temp[i]);
            }
            duplicatPermList.add(tempCombList);
            return;
        }

        for(int i=0;i<arr.length;i++){
            temp[current] = arr[i];
            makeDuplicatePerm(arr, temp, r, current+1);
        }
    }

    private static void makeCombination(int[] arr, int[] temp, int r, int current, int start){
        if(r == current){
            List<Integer> tempCombList = new ArrayList<>();
            for(int i=0;i<r;i++){
                tempCombList.add(temp[i]);
            }
            combList.add(tempCombList);
            return;
        }

        for(int i=start;i<arr.length;i++){
            temp[current] = arr[i];
            makeCombination(arr, temp, r, current+1, i+1);
        }
    }

    private static void makeDupCombination(int[] arr, int[] temp, int r, int current, int start){
        if(r == current){
            List<Integer> tempCombList = new ArrayList<>();
            for(int i=0;i<r;i++){
                tempCombList.add(temp[i]);
            }
            duplicateCombList.add(tempCombList);
            return;
        }

        for(int i=start;i<arr.length;i++){
            temp[current] = arr[i];
            makeCombination(arr, temp, r, current+1, i);
        }
    }


    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        makePerm(arr, new int[arr.length], 2, 0, new boolean[arr.length]);

        for (List<Integer> integers : permList) {
            System.out.println(integers.toString());
        }
    }
}
