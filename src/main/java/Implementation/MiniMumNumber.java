package Implementation;

import java.util.Arrays;

public class MiniMumNumber {

    public int solution(int []A, int []B) {
        Arrays.sort(A);
        Arrays.sort(B);

        int length = A.length;
        int sum = 0;
        for(int i=0;i<length;i++){
            sum += A[i] * B[length - 1 - i];
        }

        return sum;
    }

    public static void main(String[] args) {
        MiniMumNumber number = new MiniMumNumber();
        int solution = number.solution(new int[]{1, 4, 2}, new int[]{5, 4, 4});
        System.out.println(solution);
    }
}
