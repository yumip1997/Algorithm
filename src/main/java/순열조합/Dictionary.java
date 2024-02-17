package 순열조합;

import java.util.*;

public class Dictionary {
    private final List<String> wordList = new ArrayList<>();

    private void makeDuplicatePerm(char[] arr, char[] temp, int r, int current){
        if(r == current){
            StringBuilder builder = new StringBuilder();
            for(int i=0;i<r;i++){
                builder.append(temp[i]);
            }
            wordList.add(builder.toString());
            return;
        }

        for(int i=0;i<arr.length;i++){
            temp[current] = arr[i];
            makeDuplicatePerm(arr, temp, r, current+1);
        }


    }
    public int solution(String word) {
        char[] charArr = {'A', 'E', 'I', 'O', 'U'};
        makeDuplicatePerm(charArr, new char[charArr.length], 1, 0);
        wordList.sort(Comparator.naturalOrder());
        return wordList.indexOf(word)+1;
    }

    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        dictionary.solution("A");
    }
}
