package Regex;

import java.util.Scanner;

public class SecretNote {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String replacedStr= str.replaceAll("([aeoui])p[aeoui]", "$1");
        System.out.println(replacedStr);
    }

}
