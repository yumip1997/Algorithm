package Regex;

public class Regex_Note {

    public static void main(String[] args) {
        //문자열 - 자음 포함x
        String isNotVowel = "^[^aeiou]+";

        //문자열 - 자음 3번 반복 or 모음 3번 반복 확인
        String isRepeated3times = ".*([aeiou]{3}|[^aeiou]{3}).*";

        //문자열 - 같은 문자 반복 여부 확인
        String isRepeated = ".*([a-z])\\1+.*";

    }
}
