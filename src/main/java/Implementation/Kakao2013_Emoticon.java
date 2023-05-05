package Implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Kakao2013_Emoticon {

    class EmoticonSelling implements Comparable<EmoticonSelling>{

        private int infiniteEmoticonUserCnt;
        private int totalSellingPrice;

        public EmoticonSelling(int infiniteEmoticonUserCnt, int totalSellingPrice){
            this.infiniteEmoticonUserCnt = infiniteEmoticonUserCnt;
            this.totalSellingPrice = totalSellingPrice;
        }

        public int getInfiniteEmoticonUserCnt() {
            return infiniteEmoticonUserCnt;
        }

        public int getTotalSellingPrice() {
            return totalSellingPrice;
        }

        @Override
        public int compareTo(EmoticonSelling o) {
            if(this.infiniteEmoticonUserCnt == o.infiniteEmoticonUserCnt){
                return o.totalSellingPrice - this.totalSellingPrice;
            }else{
                return o.infiniteEmoticonUserCnt - this.infiniteEmoticonUserCnt;
            }
        }
    }

    private final static int[] DISCOUNT_RATE_ARR = {10,20,30,40};
    private List<int[]> DISCOUNT_COMBINATION_LIST = new ArrayList<>();
    private List<EmoticonSelling> emoticonSellingList = new ArrayList<>();

    public int[] solution(int[][] users, int[] emoticons) {
        permutation(DISCOUNT_RATE_ARR, new int[emoticons.length], 0, emoticons.length);
        calculateSelling(users, emoticons);
        emoticonSellingList.sort(EmoticonSelling::compareTo);
        return new int[]{emoticonSellingList.get(0).getInfiniteEmoticonUserCnt(), emoticonSellingList.get(1).getTotalSellingPrice()};
    }

    private void calculateSelling(int[][] users, int[] emoticons){
        for (int[] discountRates : DISCOUNT_COMBINATION_LIST) {
            int infiniteEmoticonSellingCnt = 0;
            int totalSellingPrice = 0;

            for (int[] user : users) {
                int rate = user[0];
                int limitPriceToSignUpInfiniteEmoticon = user[1];

                int totalPriceUpperRate = 0;

                for (int j = 0; j < discountRates.length; j++) {
                    if (discountRates[j] < rate) {
                        continue;
                    }

                    double discountedPrice = emoticons[j] - (emoticons[j] * discountRates[j] * 0.01);
                    totalPriceUpperRate += discountedPrice;
                }

                if (totalPriceUpperRate >= limitPriceToSignUpInfiniteEmoticon) {
                    infiniteEmoticonSellingCnt++;
                } else {
                    totalSellingPrice += totalPriceUpperRate;
                }
            }

            emoticonSellingList.add(new EmoticonSelling(infiniteEmoticonSellingCnt, totalSellingPrice));
        }
    }

    public void permutation(int[] arr, int[] result, int depth, int r){
        if(depth == r){
            DISCOUNT_COMBINATION_LIST.add(Arrays.copyOf(result, result.length));
            return;
        }

        for (int j : arr) {
            result[depth] = j;
            permutation(arr, result, depth + 1, r);
        }
    }

    public static void main(String[] args) {
       Kakao2013_Emoticon solution = new Kakao2013_Emoticon();
       solution.solution(new int[][]{{40, 10000}, {25, 10000}}, new int[]{7000,9000});
    }
}
