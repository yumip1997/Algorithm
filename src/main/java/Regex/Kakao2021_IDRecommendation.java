package Regex;

public class Kakao2021_IDRecommendation {
    static class Solution {
        public String solution(String new_id) {
            new_id = new_id.toLowerCase();
            new_id = new_id.replaceAll("[^a-z\\d._\\-]", "");
            new_id = new_id.replaceAll("[.]{2,}", ".");
            new_id = new_id.replaceAll("^[.]|[.]$", "");

            if("".equals(new_id)){
                new_id = "a";
            }

            if(new_id.length() >= 16){
                new_id = new_id.substring(0, 15);
            }

            StringBuilder idBuilder = new StringBuilder(new_id.replaceAll("[.]$", ""));

            while(idBuilder.length() < 3){
                idBuilder.append(idBuilder.charAt(idBuilder.length() -1));
            }

            return idBuilder.toString();
        }
    }
}
