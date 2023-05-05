package kakao;

import java.util.Arrays;

public class Kako_KNumber {

    static class Solution {
        public boolean isPrime(long num) {
            if (num == 1) return false;

            for (long i = 2; i * i <= num; i++) {
                if (num % i == 0) {
                    return false;
                }
            }
            return true;
        }

        public int solution(int n, int k) {
            String kNumber = Integer.toString(n, k);
            String[] split = kNumber.split("0");

            return (int) Arrays.stream(split)
                    .filter(e -> !e.isEmpty())
                    .map(Long::parseLong)
                    .filter(this::isPrime)
                    .count();
        }
    }
}

