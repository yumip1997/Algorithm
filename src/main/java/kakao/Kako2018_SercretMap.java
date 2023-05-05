package kakao;

import java.util.Arrays;

public class Kako2018_SercretMap {

    private static final char WALL_CHAR = '#';
    private static final char BLANK_CHAR = ' ';

    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        char[][] matrix = new char[n][n];

        makeMatrix(arr1, matrix);
        makeMatrix(arr2, matrix);

        for (int i = 0; i < matrix.length; i++) {
            answer[i] = String.valueOf(matrix[i]);
        }

        return answer;
    }

    private void makeMatrix(int[] arr, char[][] matrix) {
        for (int i = 0; i < arr.length; i++) {
            String binaryStr = Integer.toString(arr[i], 2);
            String secretStr = addZero(binaryStr, matrix.length - binaryStr.length());
            for (int j = 0; j < secretStr.length(); j++) {
                if (matrix[i][j] == WALL_CHAR) {
                    continue;
                }
                matrix[i][j] = secretStr.charAt(j) == '0' ? BLANK_CHAR : WALL_CHAR;
            }
        }
    }

    private String addZero(String str, int cnt) {
        if(cnt == 0) return str;

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < cnt; i++) {
            stringBuilder.append(0);
        }
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Kako2018_SercretMap solution = new Kako2018_SercretMap();
        solution.solution(5, new int[]{9, 20, 28, 18, 11}, new int[]{30, 1, 21, 17, 28});
    }
}
