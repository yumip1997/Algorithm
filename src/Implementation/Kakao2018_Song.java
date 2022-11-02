package Implementation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Kakao2018_Song {

    class SongInfo implements Comparable<SongInfo>{

        private int number;
        private int time;
        private String title;
        private String playedNotes;

        public SongInfo(int number, int time, String title, String playedNotes) {
            this.number = number;
            this.time = time;
            this.title = title;
            this.playedNotes = playedNotes;
        }

        public int getNumber(){
            return number;
        }

        public int getTime() {
            return time;
        }

        public String getTitle() {
            return title;
        }

        public String getPlayedNotes() {
            return playedNotes;
        }

        public boolean isMatched(String str){
            return playedNotes.matches(str);
        }

        @Override
        public int compareTo(SongInfo o) {
            if(this.time == o.time){
                return this.number = o.number;
            }
            return o.time - this.time;
        }
    }

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.KOREA);

    public String solution(String m, String[] musicinfos) {
        List<SongInfo> songInfoList = new ArrayList<>();
        int index = 0;
        for (String musicinfo : musicinfos) {
            String[] split = musicinfo.split(",");

            int timeDiff = getTimeDiff(split[0], split[1]);
            String title = split[2];
            String playedNotes = getPlayedNotes(split[3], timeDiff);

            songInfoList.add(new SongInfo(index++, timeDiff, title, playedNotes));
        }

        String strRegx = getStrRegx(m);
        List<SongInfo> resultList = getMatchedSongInfoList(songInfoList, strRegx);

        if(resultList.isEmpty()){
            return "(None)";
        }

        resultList.sort(SongInfo::compareTo);
        return resultList.get(0).getTitle();
    }

    private int getTimeDiff(String startTime, String endTime){
        try {
            Date startDateTime = simpleDateFormat.parse(startTime);
            Date endDateTime = simpleDateFormat.parse(endTime);
            long diff = endDateTime.getTime() - startDateTime.getTime();
            return (int) diff/ 60000;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private String getPlayedNotes(String title, int timeDiff) {
        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        int length = title.length();
        for(int i=0;i<timeDiff;i++){
            if(index+1 != length){
                if(title.charAt(index+1) == '#'){
                    stringBuilder.append(title.charAt(index) + "#");
                    index = index+2;
                }else{
                    stringBuilder.append(title.charAt(index));
                    index =  index+1;
                }
            }else{
                stringBuilder.append(title.charAt(index));
                index = index+1;
            }

            if(index == length){
                index = 0;
            }
        }
        return stringBuilder.toString();
    }

    private String getStrRegx(String m) {
        if(m.charAt(m.length()-1) == '#'){
            return ".*" + m + ".*";
        }else{
            return ".*" + m + "[^#]"+".*";
        }
    }

    private List<SongInfo> getMatchedSongInfoList(List<SongInfo> songInfoList, String strRegx) {
        return songInfoList.stream()
                .filter(e -> e.isMatched(strRegx))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Kakao2018_Song solution = new Kakao2018_Song();
        String abc = solution.solution("ABC#", new String[]{"12:00,12:03,HELLO,C#C#C"});
        System.out.println(abc);

        String reg = ".*ABC[^#].*";

        String test1 = "C#DEFGABC#DEFGAB";
        String test2 = "ABCDE";

        System.out.println(test1.matches(reg));
        System.out.println(test2.matches(reg));


    }
}
