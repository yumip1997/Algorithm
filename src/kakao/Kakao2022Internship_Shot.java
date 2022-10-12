package kakao;

import java.util.*;

public class Kakao2022Internship_Shot {
    static class ShotRecord implements Comparable<ShotRecord>{
        private final int[] shotInfo;
        private final int score;
        private int peachScore;
        public ShotRecord(int[] shotInfo, int score){
            this.shotInfo = shotInfo;
            this.score = score;
        }

        public int[] getShotInfo() {
            return shotInfo;
        }

        public int getScore() {
            return score;
        }

        private int getScoreDiff(){
            return this.getScore() - this.getPeachScore();
        }
        private int getPeachScore(){
            if(peachScore == 0){
                this.peachScore = calculatePeachScore();
            }
            return peachScore;
        }

        private int calculatePeachScore(){
            int sum = 0;
            for (int i=0; i< PEACH_INFO.length;i++) {
                if(PEACH_INFO[i] == 0) continue;

                if(PEACH_INFO[i] >= shotInfo[i]){
                    sum += (10 -i);
                }
            }
            return sum;
        }

        @Override
        public int compareTo(ShotRecord o) {
            int scoreCompare = o.getScoreDiff() - this.getScoreDiff();
            if(scoreCompare == 0){
                return getScoreComparing(o,SCORE_CNT-1);
            }
            return scoreCompare;
        }

        private int getScoreComparing(ShotRecord shotRecord, int n){
            if(n < 0) return 0;

            int[] shotInfo = this.getShotInfo();
            int[] otherShotInfo = shotRecord.getShotInfo();

            int compare = otherShotInfo[n] - shotInfo[n];
            if(compare == 0){
                return getScoreComparing(shotRecord, n-1);
            }
            return compare;
        }
    }

    private static int SHOT_LIMIT_CNT = 0;
    private static int[] PEACH_INFO = null;
    private static final int SCORE_CNT = 11;
    private static final List<ShotRecord> shotRecords = new ArrayList<>();

    private void recursive(int[] shotInfo, int n, int shotCnt, int score){
        if(shotCnt > SHOT_LIMIT_CNT) return;

        if(shotCnt == SHOT_LIMIT_CNT){
            shotRecords.add(new ShotRecord(shotInfo.clone(), score));
            return;
        }

        if(n == SCORE_CNT){
            int[] clone = shotInfo.clone();
            clone[10] = SHOT_LIMIT_CNT - shotCnt;
            shotRecords.add(new ShotRecord(clone, score));
            return;
        }

        shotInfo[n] = PEACH_INFO[n] + 1;
        recursive(shotInfo, n+1, shotCnt + (PEACH_INFO[n] + 1), score+ (10-n));
        shotInfo[n] = 0;
        recursive(shotInfo, n+1, shotCnt, score);
    }

    public int[] solution(int n, int[] info) {
        SHOT_LIMIT_CNT = n;
        PEACH_INFO = info;

        recursive(new int[SCORE_CNT], 0, 0, 0);

        shotRecords.sort(ShotRecord::compareTo);
        ShotRecord shotRecord = shotRecords.get(0);
        if(shotRecord.getScoreDiff() <=0){
            return new int[]{-1};
        }
        return shotRecord.getShotInfo();
    }
}
