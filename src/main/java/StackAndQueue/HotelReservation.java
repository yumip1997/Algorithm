package StackAndQueue;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class HotelReservation {

    static class ReservationInfo implements Comparable<ReservationInfo>{
        private LocalTime startTime;
        private LocalTime endTime;
        private boolean isVisited;

        public ReservationInfo(LocalTime startTime, LocalTime endTime){
            this.startTime = startTime;
            this.endTime = endTime;
            this.isVisited = false;
        }

        public LocalTime plusCleanTimeAfterEnd(){
            return endTime.plusMinutes(10);
        }
        
        public void setVisited(boolean isVisited){
            this.isVisited = isVisited;
        }

        @Override
        public int compareTo(ReservationInfo other) {
            return this.startTime.compareTo(other.startTime);
        }
    }

    public int solution(String[][] book_time) {
        int answer = 0;
        List<ReservationInfo> reservationList = makeReservationInfoList(book_time);
        reservationList.sort(ReservationInfo::compareTo);

        for (int i=0;i<reservationList.size();i++) {
            if(reservationList.get(i).isVisited){
               continue;
            }

            Stack<ReservationInfo> stack = new Stack<>();
            stack.push(reservationList.get(i));

            for(int j=i+1;j<reservationList.size();j++){
                ReservationInfo targetReservation = reservationList.get(j);
                if(targetReservation.isVisited){
                    continue;
                }

                LocalTime nextReservationTime = stack.peek().plusCleanTimeAfterEnd();
                if(isSameOrBefore(nextReservationTime, targetReservation.startTime)){
                    stack.push(targetReservation);
                }
            }

            while (!stack.isEmpty()){
                ReservationInfo reservationInfo = stack.pop();
                reservationInfo.setVisited(true);
            }

            answer++;
        }
        return answer;
    }

    private List<ReservationInfo> makeReservationInfoList(String[][] book_time){
        List<ReservationInfo> reservationList = new ArrayList<>();
        for (String[] time : book_time) {
            LocalTime startTime = convertStringToDateTime(time[0]);
            LocalTime endTime = convertStringToDateTime(time[1]);

            reservationList.add(new ReservationInfo(startTime, endTime));
        }
        return reservationList;
    }

    private static LocalTime convertStringToDateTime(String timeString) {
        return LocalTime.parse(timeString);
    }

    private boolean isSameOrBefore(LocalTime time1, LocalTime time2){
        if(time1.compareTo(time2) == 0){
            return true;
        }

        return time1.isBefore(time2);
    }

    public static void main(String[] args) {
        HotelReservation hotelReservation = new HotelReservation();
        System.out.println(hotelReservation.solution(
                new String[][]{
                        {"10:00", "10:20"}, {"09:00", "09:20"}, {"09:20", "09:40"}, {"09:40", "10:00"}
                }
        ));
    }
}
