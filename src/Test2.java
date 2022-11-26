import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test2 {

    public static void main(String[] args) {
        String str = "1D2S#10S*";
        String regex = "[0-9]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        while (matcher.find()){
            String group = matcher.group();
            System.out.println(group);
        }

        String str2 = "window98, windowXP, window7, windowVista, window2000";
        String regex2 = "window(98|XP|7)";
        Pattern pattern2 = Pattern.compile(regex2);
        Matcher matcher2 = pattern2.matcher(str2);

        while (matcher2.find()){
            String group = matcher2.group();
            System.out.println(group);
        }
    }

}
