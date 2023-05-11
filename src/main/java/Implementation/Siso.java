package Implementation;
import java.util.*;

public class Siso {

    public long solution(int[] weights) {
        long answer = 0;
        Map<Integer, Integer> cntMap = new TreeMap<>(Comparator.reverseOrder());

        for(int i=0;i<weights.length;i++){
            cntMap.put(weights[i], cntMap.getOrDefault(weights[i], 0) +1);
        }

        for(int w : cntMap.keySet()){
            if(cntMap.get(w) > 1){
                answer += (((long) cntMap.get(w) * (cntMap.get(w) -1))/2);
            }

            if(w%2 ==0){
                answer += cntMap.getOrDefault(w / 2, 0);

            }

            if(w%3 ==0){
                answer += cntMap.getOrDefault(w / 3 * 2, 0);
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Siso siso = new Siso();
        long solution = siso.solution(new int[]{100, 100, 100, 150, 150, 200, 300});
        System.out.println(solution);

    }
}
