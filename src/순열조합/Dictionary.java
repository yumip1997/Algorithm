package 순열조합;

import java.util.*;

public class Dictionary {
    private static final List<String> WORD_LIST = new ArrayList<>();

    public int solution(String word) {
        int answer = 0;
        char[] arr = {'A', 'E', 'I', 'O', 'U'};
        int n = arr.length;
        for (int i=1;i<=n;i++) {
            char[] output = new char[i];
            boolean[] visited = new boolean[n];
            makePermutation(arr, output, visited, 0, n, i);
        }
        WORD_LIST.sort(Comparator.naturalOrder());

        return WORD_LIST.indexOf(word)+1;
    }

    private void makePermutation(char[] arr, char[] output, boolean[] visited, int depth, int n, int r){
        if(depth == r){
            WORD_LIST.add(String.valueOf(output));
            return;
        }

        for(int i=0;i<n;i++){
            visited[i] = true;
            output[depth] = arr[i];
            makePermutation(arr, output, visited, depth+1, n, r);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        int i = dictionary.solution("I");
        System.out.println(i);
    }
}
