package Implementation;

import java.util.HashMap;
import java.util.Map;

public class Kakao2024_Gift {

    public int solution(String[] friends, String[] gifts) {
        Map<String, Map<String, Integer>> giveFriendsMap = initGiveFriendsMap(friends);
        Map<String, Integer> giveCntMap = new HashMap<>();
        Map<String, Integer> receiveCntMap = new HashMap<>();

        for (String gift : gifts) {
            String[] split = gift.split("\\s+");

            String giver = split[0];
            String receiver = split[1];

            Map<String, Integer> receiverFriendsMap = giveFriendsMap.get(giver);
            plusCntOfMap(receiverFriendsMap, receiver);
            plusCntOfMap(giveCntMap, giver);
            plusCntOfMap(receiveCntMap, receiver);
        }

        Map<String, Integer> giftIndexMap = makeGiftIndexMap(friends, giveCntMap, receiveCntMap);

        int max = Integer.MIN_VALUE;
        for (String myself : giveFriendsMap.keySet()) {
            int toGiveCnt = 0;
            for (String friend : friends) {
                if(myself.equals(friend)){
                    continue;
                }

                int giveCnt = giveFriendsMap.get(myself).getOrDefault(friend, 0);
                int receiveCnt = giveFriendsMap.get(friend).getOrDefault(myself, 0);
                if(giveCnt == receiveCnt){
                    toGiveCnt += getGiftCntByGiftIndex(myself, friend, giftIndexMap);
                    continue;
                }

                if(giveCnt > receiveCnt){
                    toGiveCnt++;
                }
            }

            max = Math.max(max, toGiveCnt);
        }


        return max;
    }

    private Map<String, Map<String, Integer>> initGiveFriendsMap(String[] friends){
        Map<String, Map<String, Integer>> giveFriendsMap = new HashMap<>();
        for (String friend : friends) {
            giveFriendsMap.put(friend, new HashMap<>());
        }
        return giveFriendsMap;
    }

    private void plusCntOfMap(Map<String, Integer> cntMap, String key){
        cntMap.put(key, cntMap.getOrDefault(key, 0) +1);
    }

    private Map<String, Integer> makeGiftIndexMap(String[] friends, Map<String, Integer> giverCntMap, Map<String, Integer> receiverCntMap){
        Map<String, Integer> giftIndexMap = new HashMap<>();
        for (String friend : friends) {
            giftIndexMap.put(friend, giverCntMap.getOrDefault(friend,0) - receiverCntMap.getOrDefault(friend, 0));
        }
        return giftIndexMap;
    }

    private int getGiftCntByGiftIndex(String myself, String friend, Map<String, Integer> giftIndexMap){
        return giftIndexMap.get(myself) > giftIndexMap.get(friend) ? 1 : 0;
    }


    public static void main(String[] args) {
        Kakao2024_Gift gift = new Kakao2024_Gift();
        gift.solution(new String[]{"muzi", "ryan", "frodo", "neo"}, new String[]{
                "muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"
        });
    }
}
