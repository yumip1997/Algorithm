package Implementation;

public class ConversionRepeat {
     static class Solution {
         public int[] solution(String s) {
             int cnt = 0;
             int totalRemovedZeroCnt = 0;
             while(!"1".equals(s)){
                 totalRemovedZeroCnt += countZero(s);
                 s = removeZero(s);
                 s = convertLengthTo2Number(s);
                 cnt++;
             }

             return new int[] {cnt, totalRemovedZeroCnt};
         }

         private int countZero(String str){
             int cnt = 0;
             for(int i=0;i<str.length();i++){
                 if(str.charAt(i) == '0'){
                     cnt++;
                 }
             }
             return cnt;
         }

        private String removeZero(String str){
            return str.replaceAll("0", "");
        }

        private String convertLengthTo2Number(String str){
            int len = str.length();
            return Integer.toString(len, 2);
        }
    }
}
