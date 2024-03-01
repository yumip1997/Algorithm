package Implementation;

public class Wifi {

    public int solution(int n, int[] stations, int w) {
        int spreadRange = 2*w+1;

        int answer=0;
        int len = stations.length;
        for(int i=0;i<len;i++){
            if(i+1 >= len){
                break;
            }

            int start = (stations[i]-1)+w+1;
            int end = (stations[i+1]-1)-w-1;

            int range = calculateRange(start, end);
            answer += totalWifiWithinRange(range, spreadRange);
        }

        if (stations[0] != 0){
            int start = 0;
            int end = (stations[0]-1)-w-1;

            int range = calculateRange(start, end);
            answer += totalWifiWithinRange(range, spreadRange);
        }

        if(stations[len-1] != n){
            int start = (stations[len-1]-1)+w+1;
            int end = n-1;

            int range = calculateRange(start, end);
            answer+= totalWifiWithinRange(range, spreadRange);
        }
        return answer;
    }

    private int calculateRange(int start, int end){
        return end-start+1;
    }

    private int totalWifiWithinRange(int range, int spreadRange){
        if(range < spreadRange){
            return 1;
        }

        if(range%spreadRange == 0){
            return range/spreadRange;
        }

        int reaming = range%spreadRange;
        return reaming + (range-reaming)/spreadRange;
    }

    public static void main(String[] args) {
        Wifi wifi = new Wifi();
        int solution = wifi.solution(16, new int[]{9}, 2);
        System.out.println(solution);
    }
}
