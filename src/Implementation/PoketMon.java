package Implementation;

import java.util.*;
import java.util.stream.Collectors;

public class PoketMon {

    class PoketMonInfo implements Comparable<PoketMonInfo>{
        private int number;
        private int cnt;

        public PoketMonInfo(int number, int cnt){
            this.number = number;
            this.cnt = cnt;
        }

        public int getNumber() {
            return number;
        }

        public int getCnt() {
            return cnt;
        }

        public void setCnt(int cnt) {
            this.cnt = cnt;
        }

        public void plusCnt(){
            cnt++;
        }

        public void minusCnt(){
            cnt--;
        }

        @Override
        public int compareTo(PoketMonInfo o) {
            return Comparator.comparing(PoketMonInfo::getCnt)
                    .compare(this, o);
        }
    }

    public int solution(int[] nums) {
        Map<Integer, PoketMonInfo> poketMonCntMap = new HashMap<>();
        for (int num : nums) {
            if(poketMonCntMap.containsKey(num)){
                PoketMonInfo poketMonInfo = poketMonCntMap.get(num);
                poketMonInfo.plusCnt();
            }else{
                poketMonCntMap.put(num , new PoketMonInfo(num, 1));
            }
        }

        List<PoketMonInfo> poketMonInfoList = getValueList(poketMonCntMap);
        poketMonInfoList.sort(PoketMonInfo::compareTo);

        Set<Integer> poketMonSet = new HashSet<>();
        int targetCnt = nums.length / 2;
        int cnt = 0;
        while (cnt != targetCnt){
            for (PoketMonInfo poketMonInfo : poketMonInfoList) {
                if(poketMonInfo.getCnt() == 0){
                    continue;
                }

                poketMonSet.add(poketMonInfo.getNumber());
                poketMonInfo.minusCnt();
                cnt++;

                if(cnt == targetCnt){
                    break;
                }
            }
        }

        return poketMonSet.size();
    }

    private List<PoketMonInfo> getValueList(Map<Integer, PoketMonInfo> poketMonCntMap) {
        return poketMonCntMap.entrySet().stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        PoketMon poketMon = new PoketMon();
        poketMon.solution(new int[]{4,4,4,4,4,4,2,2,2,2,2,2});
    }
}
