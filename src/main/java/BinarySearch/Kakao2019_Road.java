package BinarySearch;

public class Kakao2019_Road {

    public int solution(int[] stones, int k) {
        int min = 1;
        int max = 200000000;
        int result = 0;
        while (min <= max){
            int mid = (min + max) / 2;

            if(isPossibleCross(mid, k, stones)){
                min = mid + 1;
                result = mid;
            }else{
                max = mid - 1;
            }
        }
        return result;
    }

    private boolean isPossibleCross(int totalNumber, int k, int[] stones){
        int count = 0;
        for (int stone : stones) {
            if (stone < totalNumber) {
                count++;
                if (count >= k) {
                    return false;
                }
            } else {
                count = 0;
            }
        }

        return true;
    }


    public static void main(String[] args) {
        Kakao2019_Road kakao2019_road = new Kakao2019_Road();
        System.out.println(kakao2019_road.solution(new int[]{2, 4, 5, 3, 2, 1, 4, 2, 5, 1}, 3));
    }

}
