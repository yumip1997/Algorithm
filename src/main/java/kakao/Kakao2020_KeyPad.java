package kakao;

import java.util.HashMap;
import java.util.Map;

public class Kakao2020_KeyPad {

    class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    enum HandCode {
        R("right", "R"),
        L("left", "L");

        private String handStr;
        private String handCode;

        HandCode(String handStr, String handCode) {
            this.handStr =  handStr;
            this.handCode = handCode;
        }

        private static String findHandCode(String handStr){
            if ("right".equals(handStr)) {
                return R.handCode;
            }else{
                return L.handCode;
            }
        }
    }

    private static final Map<Integer, Point> KEY_POINT_MAP = new HashMap<>();

    public String solution(int[] numbers, String hand) {
        initKeyPointMap();

        Point currentRPoint = new Point(3,2);
        Point currentLPoint = new Point(3,0);
        String handCode = HandCode.findHandCode(hand);
        StringBuilder handLogStrBuilder = new StringBuilder();

        for (int number : numbers) {
            if(isRArea(number)){
                handLogStrBuilder.append(HandCode.R.handCode);
                currentRPoint = KEY_POINT_MAP.get(number);
                continue;
            }

            if(isLArea(number)){
                handLogStrBuilder.append(HandCode.L.handCode);
                currentLPoint = KEY_POINT_MAP.get(number);
                continue;
            }

            //거리 계산
            double rDistance = calculateDistance(currentRPoint, KEY_POINT_MAP.get(number));
            double lDistance = calculateDistance(currentLPoint, KEY_POINT_MAP.get(number));

            if(rDistance < lDistance){
                handLogStrBuilder.append(HandCode.R.handCode);
                currentRPoint = KEY_POINT_MAP.get(number);
            }else if(rDistance > lDistance){
                handLogStrBuilder.append(HandCode.L.handCode);
                currentLPoint = KEY_POINT_MAP.get(number);
            }else{
               handLogStrBuilder.append(handCode);
               if(HandCode.R.handCode.equals(handCode)){
                   currentRPoint = KEY_POINT_MAP.get(number);
               }else{
                   currentLPoint = KEY_POINT_MAP.get(number);
               }
            }
        }

        return handLogStrBuilder.toString();
    }

    private double calculateDistance(Point p1, Point p2) {
        int x = p1.getX() - p2.getX();
        int y = p1.getY() - p2.getY();
        return Math.abs(x) + Math.abs(y);
    }

    private void initKeyPointMap(){
        KEY_POINT_MAP.put(1, new Point(0,0));
        KEY_POINT_MAP.put(2, new Point(0,1));
        KEY_POINT_MAP.put(3, new Point(0,2));
        KEY_POINT_MAP.put(4, new Point(1,0));
        KEY_POINT_MAP.put(5, new Point(1,1));
        KEY_POINT_MAP.put(6, new Point(1,2));
        KEY_POINT_MAP.put(7, new Point(2,0));
        KEY_POINT_MAP.put(8, new Point(2,1));
        KEY_POINT_MAP.put(9, new Point(2,2));
        KEY_POINT_MAP.put(0, new Point(3,1));
    }

    private boolean isRArea(int number){
        return number == 3 || number == 6 || number == 9;
    }

    private boolean isLArea(int number){
        return number == 1 || number == 4 || number == 7;
    }

    public static void main(String[] args) {
        Kakao2020_KeyPad solution = new Kakao2020_KeyPad();
        solution.solution(new int[]{1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5}, "right");

    }
}
