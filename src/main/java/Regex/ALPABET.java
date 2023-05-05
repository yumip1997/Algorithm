package Regex;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ALPABET {

    private static final String ALPA_REGEX = "(c=|c-|dz=|d-|lj|nj|s=|z=|[a-z])";

    public static void main(String[] args) {
        Pattern compile = Pattern.compile(ALPA_REGEX);

        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        Matcher matcher = compile.matcher(str);
        int cnt = 0;
        while (matcher.find()){
            cnt++;
        }

        System.out.println(cnt);
    }
}
