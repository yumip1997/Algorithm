package DFSBFS;

import java.util.*;
import java.util.stream.Collectors;

class WordChange2 {
    private static final Map<String, List<String>> changeableWordMap = new HashMap<>();
    private static final Map<String, Boolean> wordVisitedMap = new HashMap<>();
    private static final List<Integer> resultList = new ArrayList<>();

    public int solution(String begin, String target, String[] words) {
        Set<String> wordSet = Arrays.stream(words).collect(Collectors.toSet());
        if(!wordSet.contains(target)){
            return 0;
        }
        initChangeableWordList(begin, words);

        for(String word : words){
            initChangeableWordList(word, words);
        }

        initWordVisitedMap(changeableWordMap.keySet());

        dfs(begin, target, 0);

        resultList.sort(Comparator.naturalOrder());
        return resultList.get(0);
    }

    private void initChangeableWordList(String word, String[] words){
        changeableWordMap.put(word, new ArrayList<>());

        for(int i=0;i<words.length;i++){
            if(word.equals(words[i])){
                continue;
            }

            if(isChangeable(word, words[i])){
                List<String> changeableWordList = changeableWordMap.get(word);
                changeableWordList.add(words[i]);
            }
        }

    }

    private boolean isChangeable(String str1, String str2){
        int sameCharCnt = 0;

        for(int i=0;i<str1.length();i++){
            if(str1.charAt(i) != str2.charAt(i)){
                sameCharCnt++;
            }

            if(sameCharCnt > 1){
                return false;
            }
        }

        return true;
    }

    private void initWordVisitedMap(Set<String> wordSet){
        for(String word : wordSet){
            wordVisitedMap.put(word,false);
        }
    }

    private void dfs(String start, String target, int cnt){
        wordVisitedMap.put(start, true);

        if(start.equals(target)){
            resultList.add(cnt);
        }

        List<String> changeableWordList = changeableWordMap.get(start);
        for(int i=0;i<changeableWordList.size();i++){
            String word = changeableWordList.get(i);

            if(!wordVisitedMap.get(word)){
                dfs(word, target, cnt+1);
            }
        }
    }

    public static void main(String[] args) {
        WordChange2 wordChange2 = new WordChange2();
        int solution = wordChange2.solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log", "cog"});
        System.out.println(solution);
    }
}
