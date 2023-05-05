package kakao;

public class Kakao2018_KnumberGame {
    static class Solution {
        public String solution(int n, int t, int m, int p) {
            int totalCnt = t*m;
            char[] totalNumChars = new char[totalCnt];

            int index = 0;
            for(int i=0;i<totalCnt && index < totalCnt;i++){
                String kNumStr = Integer.toString(i, n);

                for(int j=0;j<kNumStr.length();j++){
                    if(index == totalCnt) break;
                    totalNumChars[index++] = kNumStr.charAt(j);
                }
            }

            StringBuilder resultStrBuilder = new StringBuilder();
            for(int i=0;i<t;i++){
                int orderIndex = ((m * i) + p) -1;
                resultStrBuilder.append(totalNumChars[orderIndex]);
            }

            return resultStrBuilder.toString().toUpperCase();
        }
    }
}
