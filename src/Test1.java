import java.util.*;

public class Test1 {

    public int solution(int[] A) {
        int[] sortedA = Arrays.stream(A)
                .distinct()
                .sorted()
                .toArray();

        int length = sortedA.length;
        if (sortedA[length - 1] < 0) {
            return 1;
        }

        for (int i = 0; i < length - 1; i++) {
            int current = sortedA[i];
            int next = sortedA[i + 1];

            if (current + 1 != next) {
                return current + 1;
            }
        }

        return sortedA[length - 1] + 1;
    }

    public static void main(String[] args) {
        Test1 test = new Test1();
        int solution = test.solution(new int[]{1, 3, 6, 4, 1, 2});
        System.out.println(solution);
    }
}
