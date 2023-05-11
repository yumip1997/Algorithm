package Implementation;

import java.util.Arrays;

public class HashValue {

    public int solution(int[][] data, int col, int row_begin, int row_end) {
        Arrays.sort(data, (o1,o2) -> o1[col-1] == o2[col-1] ? o2[0] - o1[0] : o1[col-1] - o2[col-1]);

        int sum = 0;
        for(int i=row_begin-1;i<=row_end-1;i++){
            int modSum = 0;
            for(int j=0;j<data[i].length;j++){
                modSum += data[i][j] % (i+1);
            }
            sum ^= modSum;
        }

        return sum;
    }

    public static void main(String[] args) {
        HashValue hashValue = new HashValue();
        int solution = hashValue.solution(new int[][]{
                {2, 2, 6},
                {1, 5, 10},
                {4, 2, 9},
                {3, 8, 3}}, 2, 2, 3);
        System.out.println(solution);
    }
}
