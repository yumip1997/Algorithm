package 순열조합;
import java.util.*;

public class Test {

    private static final List<List<Integer>> PERM_LIST = new ArrayList<>();
    private static final List<List<Integer>> COMB_LIST = new ArrayList<>();

    private static void makePerm(int[] arr, int[] temp, int r, int current, boolean[] visited){
        if(r == current){
            return;
        }

        for(int i=0;i<arr.length;i++){
            if(!visited[i]){
                visited[i] = true;
                makePerm(arr, temp, r, current+1, visited);
                visited[i] = false;
            }
        }
    }
    private static void makeComb(int[] arr, int[] temp, int r, int current, int start){
        if(r == current){
            List<Integer> tempList = new ArrayList<>();
            for(int i=0;i<r;i++){
                tempList.add(temp[i]);
            }
            COMB_LIST.add(tempList);
            return;
        }

        for(int i=start;i<arr.length;i++){
            temp[current] = arr[i];
            makeComb(arr, temp, r, current+1, i+1);
        }
    }

    public static void main(String[] args) {
        int[] combArr = new int[]{1,2,3,4};
        makeComb(combArr, new int[combArr.length], 4, 0,0);
        for(List<Integer> list : COMB_LIST){
            System.out.println(list.toString());

        }
    }
}
