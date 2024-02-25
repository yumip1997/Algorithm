package Implementation;

public class PlayExpect {

    public int solution(int n, int a, int b){

        int round = 0;
        int totalPlayCount = n/2;
        int targetA = Math.min(a, b);
        int targetB = Math.max(a,b);

        for(int i = 1;i<=totalPlayCount;i++){
            if(isOpponent(targetA, targetB)){
                round = i;
                break;
            }

            targetA = getNextNumber(targetA);
            targetB = getNextNumber(targetB);
        }

        return round;
    }

    private boolean isOpponent(int targetA, int targetB){
        if(!isEvenNumber(targetB)){
            return false;
        }

        return targetB - targetA == 1;
    }

    private boolean isEvenNumber(int number){
        return number%2 == 0;
    }

    private int getNextNumber(int target){
        if(isEvenNumber(target)){
            return target/2;
        }

        return (target+1)/2;
    }

    public static void main(String[] args) {
        PlayExpect playExpect = new PlayExpect();
        playExpect.solution(8, 4,7);
    }
}
