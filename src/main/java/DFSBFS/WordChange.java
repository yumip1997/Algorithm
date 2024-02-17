package DFSBFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.*;
import java.util.stream.Collectors;

class WordChange {

    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        Map<String, List<String>> adjacentWordsMap = new HashMap<>();
        List<String> beginAdjacentList = new ArrayList<>();
        Map<String, Boolean> wordVisitedCheckMap = new HashMap<>();
        
        wordVisitedCheckMap.put(begin, false);
        adjacentWordsMap.put(begin, beginAdjacentList);

        for(int i=0;i<words.length;i++){
            if(isChangeable(begin, words[i])){
                beginAdjacentList.add(words[i]);
            }
        }

        for(int i=0;i<words.length;i++){
            String key = words[i];
            wordVisitedCheckMap.put(key, false);
            adjacentWordsMap.put(key, new ArrayList<String>());

            for(int j=0;j<words.length;j++){
                if(i == j){
                    continue;
                }

                if(isChangeable(key, words[j])){
                    List<String> adjacentList = adjacentWordsMap.get(key);
                    adjacentList.add(words[j]);
                }

            }
        }

        for(String key : adjacentWordsMap.keySet()){
            System.out.println(key + " : " + adjacentWordsMap.get(key).toString());
        }

        Map<String, Integer> wordTrackCntMap = new HashMap<>();


        Queue<String> queue = new LinkedList<>();
        wordVisitedCheckMap.put(begin, true);
        queue.add(begin);
        wordTrackCntMap.put(begin, 0);


        int result = 0;
        while(!queue.isEmpty()){
            String startWord = queue.poll();
            if(startWord.equals(target)){
                result = wordTrackCntMap.get(startWord);
                break;
            }
            List<String> adjacentList = adjacentWordsMap.get(startWord);
            int trackCnt = wordTrackCntMap.get(startWord);

            for(int i=0;i<adjacentList.size();i++){
                String word = adjacentList.get(i);

                if(!wordVisitedCheckMap.get(word)){
                    queue.add(word);
                    wordVisitedCheckMap.put(word, true);
                    wordTrackCntMap.put(word, trackCnt+1);
                }
            }

        }

        return result;
    }

    private boolean isChangeable(String str1, String str2){
        int diff = 0;
        for(int i=0;i<str1.length();i++){
            if(str1.charAt(i) != str2.charAt(i)){
                diff++;
            }

            if(diff > 1){
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        WordChange wordChange = new WordChange();
        int solution = wordChange.solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log", "cog"});
        System.out.println(solution);
    }
}
