package Implementation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Kakao2013_Privacy {

    private final String DATE_FORMAT = "yyyy.MM.dd";
    private final Map<String, Integer> termsMap = new HashMap<>();

    public int[] solution(String today, String[] terms, String[] privacies) {
        initTermsMap(terms);

        List<Integer> deletedPrivacies = new ArrayList<>();
        LocalDate todayLocalDate = LocalDate.parse(today, DateTimeFormatter.ofPattern(DATE_FORMAT));

        for(int i=0;i< privacies.length;i++){
            String privacy = privacies[i];

            String[] privacyArr = privacy.split(" ");
            String date = privacyArr[0];
            String term = privacyArr[1];
            int monthOfTerm = termsMap.get(term);

            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));
            LocalDate deleteDate = localDate.plusMonths(monthOfTerm);

            if(todayLocalDate.compareTo(deleteDate) >= 0){
                deletedPrivacies.add(i+1);
            }

        }
        return deletedPrivacies.stream().mapToInt(e -> e).toArray();
    }

    private void initTermsMap(String[] terms) {
        for (String term : terms) {
            String[] termArr = term.split(" ");
            termsMap.put(termArr[0], Integer.parseInt(termArr[1]));
        }
    }

    public static void main(String[] args) {
        Kakao2013_Privacy solution = new Kakao2013_Privacy();
        int[] solution1 = solution.solution("2022.05.19", new String[]{"A 6", "B 12", "C 3"}, new String[]{"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"});

    }
}
