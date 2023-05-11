package Implementation;

import java.util.*;

public class Tangerine {

    public int solution(int k, int[] tangerine) {
        Map<Integer, Integer> sizeCntMap = initSizeCntMap(tangerine);
        List<Integer> tangerineList = new ArrayList<>(sizeCntMap.keySet());
        tangerineList.sort(Comparator.comparingInt(sizeCntMap::get));

        int sum = sizeCntMap.values().stream().mapToInt(i -> i).sum();
        int removingCnt = sum - k;
        int kindCnt = sizeCntMap.size();

        for (Integer tangerineSize : tangerineList) {
            int sizeCnt = sizeCntMap.get(tangerineSize);
            if(sizeCnt <= removingCnt){
                removingCnt -= sizeCnt;
                kindCnt--;
            }else{
                break;
            }
        }
        return kindCnt;
    }

    private Map<Integer, Integer> initSizeCntMap(int[] tangerine) {
        Map<Integer, Integer> sizeCntMap = new HashMap<>();
        for (int i : tangerine) {
            if(sizeCntMap.containsKey(i)){
                sizeCntMap.put(i, sizeCntMap.get(i)+1);
            }else{
                sizeCntMap.put(i, 1);
            }
        }
        return sizeCntMap;
    }

    public static void main(String[] args) {
        Tangerine tangerine = new Tangerine();
        int solution = tangerine.solution(2, new int[]{1, 1, 1, 1, 2, 2, 2, 3});
        System.out.println(solution);
    }
}
