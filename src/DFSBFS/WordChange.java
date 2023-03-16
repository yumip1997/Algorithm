package DFSBFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordChange {

    private static boolean isChangeable(String currentStr, String changeStr){
       int diffCnt = 0;
       for(int i=0;i<currentStr.length();i++){
           if(currentStr.charAt(i) != changeStr.charAt(i)){
               diffCnt++;
           }

           if(diffCnt > 1){
               return false;
           }
       }
       return true;
    }
    public static void main(String[] args) {

        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
        String begin = "hit";
        String target = "cog";

        Map<String, List<String>> stringListMap = initChangeableMap(begin, words);
        System.out.println(stringListMap.toString());

    }

    private static Map<String, List<String>> initChangeableMap(String target, String[] words) {
        Map<String, List<String>> changeableMap = new HashMap<>();

        changeableMap.put(target, new ArrayList<>());
        for (String word : words) {
            List<String> changeableStrList = changeableMap.get(target);
            if(isChangeable(target, word)){
                changeableStrList.add(word);
            }
        }

        for(int i = 0; i< words.length; i++){
            String currentStr = words[i];
            changeableMap.put(currentStr, new ArrayList<>());
            List<String> changeableStrList = changeableMap.get(currentStr);

            for(int j = 0; j< words.length; j++){
                if(i == j){
                    continue;
                }

                if(isChangeable(currentStr, words[j])){
                    changeableStrList.add(words[j]);
                }
            }
        }
        return changeableMap;
    }
}
