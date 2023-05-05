package kakao;

import java.util.*;

public class Kakao2018_Cache {
    private static final int HIT_TIME = 1;
    private static final int MISS_TIME = 5;

    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Set<String> storedCitySet = new HashSet<>();
        List<String> store= new LinkedList<>();

        for (String city : cities) {
            String lowerCaseCity =  city.toLowerCase();
            if(storedCitySet.contains(lowerCaseCity)){
                answer += HIT_TIME;
                store.remove(lowerCaseCity);
            }else{
                answer += MISS_TIME;
                if(cacheSize == 0) continue;

                if(isFullStore(store, cacheSize)){
                    storedCitySet.remove(store.get(0));
                    store.remove(0);
                }

                storedCitySet.add(lowerCaseCity);
            }
            store.add(lowerCaseCity);
        }

        return answer;
    }


    private boolean isFullStore(List<String> store, int cacheSize){
        return store.size() == cacheSize;
    }
    
    public static void main(String[] args) {
        Kakao2018_Cache solution = new Kakao2018_Cache();
        int solution1 = solution.solution(2, new String[]{"Jeju", "Pangyo", "NewYork", "newyork"});
        System.out.println(solution1);
    }
}
