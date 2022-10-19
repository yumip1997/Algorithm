package Implementation;

public class NextBigNumber {

    public int solution(int n) {
        int answer = 0;

        String binaryString = Integer.toBinaryString(n);
        int cnt = countOne(binaryString);
        while(true){
            String nextBinaryString = Integer.toBinaryString(++n);
            if(countOne(nextBinaryString) == cnt){
                break;
            }
        }

        return n;
    }

    private int countOne(String str){
        int cnt = 0;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i) == '1'){
                cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        NextBigNumber nextBigNumber = new NextBigNumber();

        int solution = nextBigNumber.solution(15);
        System.out.println(solution);
    }
}
