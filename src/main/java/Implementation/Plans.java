package Implementation;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

class Plan implements Comparable<Plan>{

    private String name;
    private LocalTime startTime;
    private long durationMin;
    private long remainMin;
    private LocalTime endTime;

    public Plan(String name, String startTimeStr, String duration) {
        this.name = name;
        this.startTime = LocalTime.parse(startTimeStr);
        this.durationMin = Long.parseLong(duration);
        this.remainMin = this.durationMin;
        this.endTime = startTime.plusMinutes(this.durationMin);
    }

    public String getName() {
        return name;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public long getRemainMin() {
        return remainMin;
    }

    public void setRemainMin(long remainMin) {
        this.remainMin = remainMin;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    @Override
    public int compareTo(Plan o) {
        return this.startTime.compareTo(o.startTime);
    }
}

public class Plans {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public String[] solution(String[][] plans) {
        Queue<Plan> planQueue = new PriorityQueue<>(Plan::compareTo);

        for (String[] planStr : plans) {
            String name = planStr[0];
            String startTime = planStr[1];
            String durationHour = planStr[2];

            Plan plan = new Plan(name, startTime, durationHour);
            planQueue.add(plan);
        }

        Stack<Plan> stopPlanStack = new Stack<>();
        LocalTime currentTime = planQueue.peek().getStartTime();

        List<String> result = new ArrayList<>();
        while (!planQueue.isEmpty()){

            Plan targetPlan = planQueue.peek();

            if(currentTime.compareTo(targetPlan.getStartTime()) >= 0){
                Plan currentPlan = planQueue.poll();
                Plan nextPlan = planQueue.peek();

                if(nextPlan == null){
                    result.add(currentPlan.getName());
                    currentTime = currentPlan.getEndTime();
                    continue;
                }

                LocalTime endTime = currentPlan.getEndTime();
                LocalTime nextPlanStartTime = nextPlan.getStartTime();

                if(endTime.isAfter(nextPlanStartTime)){
                    //과제를 한 시간
                    long planDoingMin = ChronoUnit.MINUTES.between(currentTime, nextPlanStartTime);
                    //남은 시간 갱신
                    currentPlan.setRemainMin(currentPlan.getRemainMin() - planDoingMin);
                    //멈춘 과제 Stack 추가
                    stopPlanStack.push(currentPlan);
                    //현재 시간 갱신
                    currentTime = nextPlanStartTime;
                }else{
                    result.add(currentPlan.getName());
                    currentTime = currentPlan.getEndTime();
                }
            }else{
                if(stopPlanStack.isEmpty()){
                    continue;
                }

                //멈춘 과제 Stack에서 꺼내기
                Plan stopPlan = stopPlanStack.pop();
                LocalTime stopPlanEndTime =  currentTime.plusMinutes(stopPlan.getRemainMin());

                Plan nextPlan = planQueue.peek();

                if(nextPlan == null){
                    result.add(stopPlan.getName());
                    currentTime = stopPlanEndTime;
                    continue;
                }

                LocalTime nextPlanStartTime = nextPlan.getStartTime();
                if(stopPlanEndTime.isAfter(nextPlanStartTime)){
                    //과제를 한 시간
                    long planDoingMin = ChronoUnit.MINUTES.between(currentTime, nextPlanStartTime);
                    //남은 시간 갱신
                    stopPlan.setRemainMin(stopPlan.getRemainMin() -planDoingMin);
                    //멈춘 과제 Stack 추가
                    stopPlanStack.push(stopPlan);
                    //현재시간 갱신
                    currentTime = nextPlanStartTime;
                }else{
                    result.add(stopPlan.getName());
                    currentTime = stopPlanEndTime;
                }
            }
        }

        while(!stopPlanStack.isEmpty()){
            Plan stopPlan = stopPlanStack.pop();
            result.add(stopPlan.getName());
        }

        return result.toArray(new String[]{});
    }

    public static void main(String[] args) {
        Plans plans = new Plans();
        String[] solution = plans.solution(new String[][]{
                {"aaa", "12:00", "20"},
                {"bbb", "12:10", "30"},
                {"ccc", "12:40", "10"}
        });

        LocalTime localTime = LocalTime.parse("12:40");
        LocalTime next = LocalTime.parse("14:00");
        long between = ChronoUnit.MINUTES.between(localTime, next);
        System.out.println(between);

    }
}
