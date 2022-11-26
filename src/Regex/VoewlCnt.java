package Regex;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VoewlCnt {

    private static final String VOWEL_REGEX = "(a|e|i|o|u)";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Pattern pattern = Pattern.compile(VOWEL_REGEX);

        while (true){
            String str = scanner.nextLine();
            String lowerCase = str.toLowerCase();

            if(lowerCase.equals("#")){
                break;
            }

            Matcher matcher = pattern.matcher(lowerCase);
            int cnt = 0;
            while(matcher.find()){
                cnt++;
            }

            System.out.println(cnt);
        }
    }
}
