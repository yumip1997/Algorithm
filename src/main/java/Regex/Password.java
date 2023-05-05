package Regex;

import java.util.Scanner;

public class Password {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true){
            String str = scanner.nextLine();
            if(str.equals("end")){
                break;
            }

            String result = isNotValid(str) ? "not acceptable" : "acceptable";
            System.out.println("<"+str+"> is "+result+".");
        }

    }

    public static boolean isNotValid(String str){
        String isNotVowel = "^[^aeiou]+";
        String isRepeated3times = ".*([aeiou]{3}|[^aeiou]{3}).*";
        String isRepeatedExcept = ".*([^eo])\\1+.*";
        return str.matches(isNotVowel) | str.matches(isRepeated3times) | str.matches(isRepeatedExcept);
    }
}
