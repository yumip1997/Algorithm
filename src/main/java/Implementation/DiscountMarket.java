package Implementation;

import java.util.*;

public class DiscountMarket {

    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        List<Integer> cnt = new ArrayList<>();
        cnt.sort(Comparator.naturalOrder());
        Map<String, Integer> wantCntMap = new HashMap<>();
        for(int i=0;i<want.length;i++){
            wantCntMap.put(want[i], number[i]);
        }

        Map<String, Integer> discountCntMap = new HashMap<>();
        for(int i=0;i<10;i++){
            discountCntMap.put(discount[i], discountCntMap.getOrDefault(discount[i], 0)+1);
        }

        if(isAllMatch(wantCntMap, discountCntMap)){
            answer++;
        }

        for(int i=1;i<=discount.length-10;i++){
            discountCntMap.put(discount[i-1], discountCntMap.get( discount[i-1])-1);
            discountCntMap.put(discount[i+9], discountCntMap.getOrDefault(discount[i+9], 0)+1);

            if(isAllMatch(wantCntMap, discountCntMap)){
                answer++;
            }
        }

        return answer;
    }

    private boolean isAllMatch(Map<String, Integer> wantCntMap, Map<String, Integer> discountCntMap){
        for(String wantFood : wantCntMap.keySet()){
            int discountFoodCnt = discountCntMap.getOrDefault(wantFood, 0);
            int wantFoodCnt = wantCntMap.get(wantFood);

            if(wantFoodCnt > discountFoodCnt){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
