package Implementation;

public class Elevator {

    public int solution(int storey) {
        int answer = 0;

        String str = String.valueOf(storey);
        int lastNum = str.charAt(str.length()-1) - '0';

        if(lastNum > 5){
            int up = (10 -lastNum);
            answer += up;
            storey += up;
        }else{
            answer += lastNum;
            storey -= lastNum;
        }

        int numCnt = getNumCnt(storey);
        for(int i=numCnt-1;i>=1;i--){
            int downNum = (int) Math.pow(10,i);
            int result = storey /downNum;

            answer += result;
            storey -= (result*downNum);
        }

        return answer;
    }

    private int getNumCnt(int number){
        int count = 0;
        while( number > 0){
            number /= 10;
            count++;
        }
        return count;
    }


    public static void main(String[] args) {
        Elevator elevator = new Elevator();
        elevator.solution(2554);
    }
}
