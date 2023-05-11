package 순열조합;
import java.util.*;

public class IndexPlus {

    private static final List<List<Integer>> indexCombList = new ArrayList<>();

    public static void makeComb(int[] arr, int[] temp, int r, int current, int start){
        if(current == r){
            List<Integer> tempList = new ArrayList<>();
            for(int i=0;i<r;i++){
                tempList.add(temp[i]);
            }
            indexCombList.add(tempList);
            return;
        }

        for(int i=start;i<arr.length;i++){
            temp[current] = arr[i];
            makeComb(arr, temp, r, current+1, i+1);
        }
    }

    public int[] solution(int[] numbers) {
        Set<Integer> set = new TreeSet<>();
        makeComb(numbers, new int[numbers.length], 2, 0,0);
        for(List<Integer> indexComb : indexCombList){
            System.out.println(indexComb.toString());
            set.add(indexComb.get(0) + indexComb.get(1));
        }


        return set.stream().mapToInt(e -> e).toArray();
    }

    public static void main(String[] args){
        int[] arr = {3, 4, 6};
        makeComb(arr, new int[arr.length], 2, 0,0);
        for (List<Integer> integers : indexCombList) {
            System.out.println(integers.toString());
        }
    }

}
