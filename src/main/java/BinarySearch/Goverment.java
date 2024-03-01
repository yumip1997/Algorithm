package BinarySearch;

import java.util.Arrays;
import java.util.Scanner;

public class Goverment {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N =  scanner.nextInt();

        long[] requestAmt = new long[N];
        for(int i=0;i<N;i++){
            requestAmt[i] = scanner.nextInt();
        }

        Arrays.sort(requestAmt);
        int gvrTotalAmt = scanner.nextInt();
        if(sumRequestAmt(requestAmt) <= gvrTotalAmt){
            System.out.println(requestAmt[N-1]);
            return;
        }

        long min = 1;
        long max = requestAmt[N-1];
        long mid = 0;
        long maxResult = 0;
        while (min <= max){
            mid = (min + max) / 2;

            if(isPossibleAmt(gvrTotalAmt, requestAmt, mid)){
                min = mid + 1;
                maxResult = mid;
            }else{
                max = mid - 1;
            }
        }


        System.out.println(maxResult);
    }

    private static long sumRequestAmt(long[] requestAmt){
        long sum = 0;
        for (long amt : requestAmt) {
            sum += amt;
        }
        return sum;
    }

    private static boolean isPossibleAmt(long gvrTotalAMt, long[] requestAmt, long targetAmt){
        long sum = 0;
        for (long amt : requestAmt) {
            sum += Math.min(amt, targetAmt);
        }
        return sum <= gvrTotalAMt;
    }
}
