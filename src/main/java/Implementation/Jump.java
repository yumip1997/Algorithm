package Implementation;

public class Jump {

    public int solution(int n) {
        int answer = 0;
        while (n > 0){
            if(isEvenNumber(n)){
                n = n / 2;
            }else{
                n = n -1;
                answer++;
            }
        }
        return answer;
    }

    private boolean isEvenNumber(int number){
        return number%2 == 0;
    }


    public static void main(String[] args) {
        Jump jump = new Jump();
        System.out.println(jump.solution(5));
        System.out.println(jump.solution(6));
        System.out.println(jump.solution(5000));
    }
}
