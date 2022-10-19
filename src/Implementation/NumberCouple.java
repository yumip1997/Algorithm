package Implementation;

import javafx.scene.input.InputMethodTextRun;

import java.util.*;

public class NumberCouple {

    private static final List<Integer> COUPLE_NUMBERS = new ArrayList<>();
    private static final String MINUS_ONE = "-1";
    private static final String ONE = "0";


    public String solution(String X, String Y) {
        String answer = "";
        Map<Character, Integer> xMap = makeCharCntMap(X);
        Map<Character, Integer> yMap = makeCharCntMap(Y);

        Set<Character> characters = xMap.keySet();
        for (Character character : characters) {
            if(yMap.containsKey(character)){
                int cnt =  Math.min(xMap.get(character), yMap.get(character));
                int numericValue = numberCharToInt(character);

                addNumberByCnt(numericValue, cnt);
            }
        }

        if(COUPLE_NUMBERS.isEmpty()){
            return MINUS_ONE;
        }

        return makeMaxNumString(COUPLE_NUMBERS);
    }

    public Map<Character, Integer> makeCharCntMap(String str){
        Map<Character, Integer> map = new HashMap<>();

        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            if(!map.containsKey(c)){
                map.put(c, 1);
            }else{
                map.replace(c, map.get(c)+1);
            }
        }

        return map;
    }

    private int numberCharToInt(char c){
        return c - '0';
    }

    private void addNumberByCnt(int number, int cnt){
        for(int i=0;i<cnt;i++){
            COUPLE_NUMBERS.add(number);
        }
    }

    private String makeMaxNumString(List<Integer> list){
        list.sort(Comparator.reverseOrder());

        if(list.get(0) == 0){
            return ONE;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (Integer integer : list) {
            stringBuilder.append(integer);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        NumberCouple numberCouple = new NumberCouple();
        String solution = numberCouple.solution("100", "2345");
        System.out.println(solution);

        String solution1 = numberCouple.solution("100", "203045");
        System.out.println(solution1);

        String solution2 = numberCouple.solution("100", "123450");
        System.out.println(solution2);

        String solution3 = numberCouple.solution("12321", "42531");
        System.out.println(solution3);

        String solution4 = numberCouple.solution("5525", "1255");
        System.out.println(solution4);
    }
}
