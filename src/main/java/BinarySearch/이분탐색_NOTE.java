package BinarySearch;

import java.util.Arrays;

public class 이분탐색_NOTE {

    private static int binary_search(int[] arr, int start, int end, int target){
        while(start <= end){
            int mid = (start + end) / 2;

            if(arr[mid] == target){
                return mid;
            }else if(arr[mid] > target){
                end = mid - 1;
            }else{
                start = mid +1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {10,9,8,7,1};
        Arrays.sort(arr);

        System.out.println(Arrays.toString(arr));
        int i = binary_search(arr, 0, arr.length - 1, 10);
        System.out.println(i);
    }
}
