package BinarySearch;

import java.util.Scanner;

public class TreeCutting {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int M = scanner.nextInt();

        int max = 0;
        int[] arr = new int[N];
        for(int i=0;i<N;i++){
            int temp = scanner.nextInt();
            arr[i] = temp;
            if(max < arr[i]){
                max = arr[i];
            }
        }

        int start = 0;
        int end = max;

        long maxRemainHeight = Long.MIN_VALUE;
        while(start <=  end){
            int mid = (start + end) / 2;

            long remainCuttingHeight = 0;
            for(int height : arr){
                if(height > mid){
                    remainCuttingHeight += (height - mid);
                }
            }

            if(remainCuttingHeight >= M){
                start = mid + 1;
                maxRemainHeight = Math.max(mid, maxRemainHeight);
            }else{
                end = mid -1;
            }
        }

        System.out.println(maxRemainHeight);
    }
}
