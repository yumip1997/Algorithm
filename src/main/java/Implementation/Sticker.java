package Implementation;

public class Sticker {

    public long solution(int sticker[]) {
        long firstDeleteSum = sumStickerArrElementFrom(0, sticker);
        long secondDeleteSum = sumStickerArrElementFrom(1, sticker);

        return Math.max(firstDeleteSum, secondDeleteSum);
    }

    private long sumStickerArrElementFrom(int start, int[] sticker){
        long sum = 0;
        for(int i=start;i<sticker.length;i=i+2){
            sum += sticker[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        Sticker sticker = new Sticker();
        sticker.solution(new int[]{14,6,5,11,3,9,2,10});
    }
}
