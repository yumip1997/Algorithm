package Regex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Kakao2018_Dart {

    private static final String NOT_NUMBER_REGEX = "[^0-9]+";
    private static final String NUMBER_REGEX = "[0-9]+";
    private static final Map<Character, Integer> plusMap = new HashMap<>();
    private static final Pattern SCORE_INFO = Pattern.compile("([0-9]+[SDT][//*//#]?)");

    public int solution(String dartResult) {
        initPlusMap();

        List<String> scoreInfoList = getScoreInfoList(dartResult);
        int[] scoreArr = new int[3];

        for (int i=0;i<scoreArr.length;i++) {
            String scoreInfo = scoreInfoList.get(i);
            int score = Integer.parseInt(scoreInfo.replaceAll(NOT_NUMBER_REGEX, ""));
            String bonus = scoreInfo.replaceAll(NUMBER_REGEX, "");

            for(int j=0;j<bonus.length();j++){
                char bounusChar = bonus.charAt(j);
                if(plusMap.containsKey(bounusChar)){
                    score = (int) Math.pow(score, (double) plusMap.get(bonus.charAt(j)));
                }

                if(bounusChar == '*'){
                    score *=2;

                    if(i!=0){
                        scoreArr[i-1] *=2;
                    }
                }

                if(bounusChar == '#'){
                    score *= -1;
                }

                scoreArr[i] = score;
            }
        }
        return scoreArr[0] + scoreArr[1] + scoreArr[2];
    }

    private void initPlusMap() {
        plusMap.put('S', 1);
        plusMap.put('D', 2);
        plusMap.put('T', 3);
    }

    private List<String> getScoreInfoList(String dartResult) {
        List<String> scoreInfoList = new ArrayList<>();
        Matcher matcher = SCORE_INFO.matcher(dartResult);
        while (matcher.find()){
            scoreInfoList.add(matcher.group());
        }
        return scoreInfoList;
    }

    public static void main(String[] args) {
        Kakao2018_Dart solution = new Kakao2018_Dart();
        solution.solution("1S2D*3T");
    }
}
