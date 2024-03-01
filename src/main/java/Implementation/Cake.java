package Implementation;

import java.util.HashMap;
import java.util.Map;

public class Cake {

    public int solution(int[] topping) {
        int answer = 0;
        Map<Integer, Integer> toppingCntMap1 = new HashMap<>();
        Map<Integer, Integer> toppingCntMap2 = new HashMap<>();

        initToppingCntMap2(topping, toppingCntMap2);

        for (int i : topping) {
            toppingCntMap1.put(i, toppingCntMap1.getOrDefault(i,0)+1);
            toppingCntMap2.put(i, toppingCntMap2.get(i)-1);

            if(toppingCntMap2.get(i) == 0){
                toppingCntMap2.remove(i);
            }

            if(toppingCntMap1.size() == toppingCntMap2.size()){
                answer++;
            }
        }
        return answer;
    }

    private void initToppingCntMap2(int[] topping, Map<Integer, Integer> toppingCntMap2) {
        for (int i : topping) {
            toppingCntMap2.put(i, toppingCntMap2.getOrDefault(i,0)+1);
        }
    }

    public static void main(String[] args) {
        Cake cake = new Cake();
        System.out.println(cake.solution(new int[]{1, 2, 1, 3, 1, 4, 1, 2}));
    }
}
