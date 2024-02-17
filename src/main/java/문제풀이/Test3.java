package 문제풀이;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Test3 {
    public static int findShortestSubstring(String s) {
        if(isDistinctStr(s)){
            return 0;
        }

        int minLength = Integer.MAX_VALUE;

        for(int left=0;left<s.length()-1;left++){
            int right = left;

            while(right < s.length()-1){
                String subString;
                if(left == 0){
                    subString = s.substring(right+1);
                }else{
                    subString = s.substring(0, left) + s.substring(right+1);
                }

                if(isDistinctStr(subString)){
                    minLength = Math.min(minLength, right - left +1);
                }

                right++;
            }
        }
        return minLength;
    }

    private static boolean isDistinctStr(String s){
        Set<Character> set = new HashSet<>();
        for(int i=0;i<s.length();i++){
            set.add(s.charAt(i));
        }

        return s.length() == set.size();
    }



    public static void main(String[] args) {
        String input2 = "abcbbk";
        System.out.println(findShortestSubstring(input2));

        String input = "abbc";
        System.out.println(findShortestSubstring(input));
    }

}
