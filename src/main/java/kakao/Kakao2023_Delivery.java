package kakao;

import java.util.Stack;

public class Kakao2023_Delivery {

    static class HomeDelivery {
        private int location;
        private int cnt;

        public HomeDelivery(int location, int cnt){
            this.location =location;
            this.cnt = cnt;
        }

        public int getLocation() {
            return location;
        }

        public int getCnt() {
            return cnt;
        }

        public void setCnt(int cnt){
            this.cnt = cnt;
        }
    }

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {

        Stack<HomeDelivery> deliveryStack = new Stack<>();
        Stack<HomeDelivery> pickupStack = new Stack<>();

        for(int i=0;i<n;i++){
            if(deliveries[i] != 0){
                deliveryStack.push(new HomeDelivery(i+1, deliveries[i]));
            }

            if(pickups[i] != 0){
                pickupStack.push(new HomeDelivery(i+1, pickups[i]));
            }
        }

        int totalDistance = 0;
        while (!deliveryStack.isEmpty() || !pickupStack.isEmpty()){
            int deliveryLocation = deliveryStack.isEmpty() ? 0 : deliveryStack.peek().getLocation();
            int pickupLocation = pickupStack.isEmpty() ? 0 : pickupStack.peek().getLocation();

            int maxLocation = Math.max(deliveryLocation, pickupLocation);
            // 배달, 수거 거리
            totalDistance += (2*maxLocation);

            popUntilCap(deliveryStack, cap);
            popUntilCap(pickupStack, cap);
        }


        return totalDistance;
    }

    private void popUntilCap(Stack<HomeDelivery> stack, int cap){
        if(stack.isEmpty()){
            return;
        }

        int currentDeliveryCnt = 0;
        while (true){
            HomeDelivery peek = stack.peek();
            if(currentDeliveryCnt + peek.getCnt() <= cap){
                HomeDelivery pop = stack.pop();
                currentDeliveryCnt += pop.getCnt();
            }else{
                HomeDelivery pop = stack.pop();
                int minusCnt = pop.getCnt() - (cap - currentDeliveryCnt);
                pop.setCnt(minusCnt);

                stack.push(pop);
                break;
            }

            if(stack.isEmpty()){
                break;
            }
        }
    }

    public static void main(String[] args) {
        Kakao2023_Delivery delivery = new Kakao2023_Delivery();
        System.out.println(delivery.solution(2,2, new int[]{0,6}, new int[]{0,0}));
    }
}
