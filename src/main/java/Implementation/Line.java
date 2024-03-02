package Implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Line {

    public int[] solution(int n, long k) {
        List<Integer> sortedList = new ArrayList<>();
        long fac = 1;
        for(int i=1;i<=n;i++){
            fac *= i;
            sortedList.add(i);
        }

        int[] answer = new int[n];
        int idx = 0;
        while(n > 0){
            fac /= n;
            long remain = k%fac;
            int lineOrder = k%fac == 0 ?  (int) (k/fac) : (int) (k/fac+1);
            int lineNumber = sortedList.get(lineOrder-1);

            answer[idx] = lineNumber;
            sortedList.remove(lineOrder-1);

            n -=1;
            idx++;
            k = remain == 0 ? fac : k%fac;
        }

        return answer;
    }


    public static void main(String[] args) {
        Line line = new Line();
        System.out.println(Arrays.toString(line.solution(3,5 )));
    }

}
