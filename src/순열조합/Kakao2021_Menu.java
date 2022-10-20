package 순열조합;

import java.util.*;
import java.util.stream.Collectors;

public class Kakao2021_Menu{

    static class MenuComb implements Comparable<MenuComb>{
        private Set<Character> menuSet;
        private int orderCnt;
        private String menuName;

        public MenuComb(Set<Character> menuSet){
            this.menuSet = menuSet;
            this.orderCnt = 0;
        }

        public MenuComb(Set<Character> menuSet, int orderCnt) {
            this.menuSet = menuSet;
            this.orderCnt = orderCnt;
        }

        public Set<Character> getMenuSet() {
            return menuSet;
        }

        public int getOrderCnt() {
            return orderCnt;
        }
        
        public boolean is2MoreOrderCnt(){
            return this.orderCnt >= 2;
        }

        public boolean isMaxCnt(int maxCnt){
            return this.orderCnt == maxCnt;
        }

        private String convertMenuSetToString(){
            StringBuilder stringBuilder = new StringBuilder();
            for (Character character : menuSet) {
                stringBuilder.append(character);
            }
            return stringBuilder.toString();
        }

        public String getMenuName(){
            if(this.menuName == null){
                this.menuName = convertMenuSetToString();
            }
            return this.menuName;
        }

        @Override
        public int compareTo(MenuComb o) {
            String str = getMenuName();
            String otherStr = o.getMenuName();
            return str.compareTo(otherStr);
        }
    }

    public static Map<Integer, List<Set<Character>>> combMap = new HashMap<>();

    public static void makeCombination(char[] arr, boolean[] visited, int depth, int n, int r){
        if(r == 0){
            Set<Character> tempCombSet = new HashSet<>();
            for(int i=0;i<visited.length;i++){
                if(visited[i]){
                    tempCombSet.add(arr[i]);
                }
            }
            List<Set<Character>> sets = combMap.get(tempCombSet.size());
            sets.add(tempCombSet);
            return;
        }

        for(int i=depth;i<n;i++){
            if(!visited[i]){
                visited[i] = true;
                makeCombination(arr, visited, i+1, n, r-1);
                visited[i] = false;
            }
        }
    }

    public String[] solution(String[] orders, int[] course) {
        List<MenuComb> alreadyOrderedMenuCombList = toMenuCombList(orders);

        char[] chars = toDistinctMenuCharArr(orders);
        int[] combCntArr = getPossibleCombCnt(course, getMaxLength(orders));

        initCombMap(combCntArr);

        int n = chars.length;
        List<MenuComb> allMaxOrderMenuCombList = new ArrayList<>();

        for (int r : combCntArr) {
            boolean[] visited = new boolean[n];
            makeCombination(chars, visited, 0, n, r);

            List<MenuComb> menuCombList = new ArrayList<>();
            int maxOrderCnt = 0;
            for (Set<Character> set : combMap.get(r)) {
                int orderCnt = 0;
                for (MenuComb menuComb : alreadyOrderedMenuCombList) {
                    boolean containsAll = menuComb.getMenuSet().containsAll(set);
                    if(containsAll){
                        orderCnt++;
                    }
                }

                if(orderCnt >= 2 ){
                    menuCombList.add(new MenuComb(set, orderCnt));
                }

                maxOrderCnt = Math.max(maxOrderCnt, orderCnt);
            }

            List<MenuComb> maxOrderedMenuCombList = getMaxOrderedMenuCombList(menuCombList, maxOrderCnt);
            if(!maxOrderedMenuCombList.isEmpty()){
                allMaxOrderMenuCombList.addAll(maxOrderedMenuCombList);
            }
        }

        allMaxOrderMenuCombList.sort(MenuComb::compareTo);

        return allMaxOrderMenuCombList.stream()
                .map(MenuComb::getMenuName)
                .toArray(String[]::new);
    }
    
    private  List<MenuComb> toMenuCombList(String[] orders) {
        List<MenuComb> menuCombList = new ArrayList<>();

        for (String orderStr : orders) {
            Set<Character> menuSet = new HashSet<>();

            for(int i=0;i<orderStr.length();i++){
                menuSet.add(orderStr.charAt(i));
            }

            menuCombList.add(new MenuComb(menuSet));
        }

        return menuCombList;
    }

    private char[] toDistinctMenuCharArr(String[] orders){
        Set<Character> menuSet = new HashSet<>();

        for (String orderStr : orders) {
            for(int i=0;i<orderStr.length();i++){
                menuSet.add(orderStr.charAt(i));
            }
        }

        char[] menuCharArr = new char[menuSet.size()];
        int index=0;
        for (Character character : menuSet) {
            menuCharArr[index++] = character;
        }

        return menuCharArr;
    }

    private int[] getPossibleCombCnt(int[] course, int maxLength){
        return Arrays.stream(course)
                .filter(e -> e<= maxLength)
                .toArray();
    }

    private int getMaxLength(String[] orders){
        int maxLength = 0;
        for (String order : orders) {
            int length = order.length();
            maxLength = Math.max(maxLength, length);
        }
        return maxLength;
    }

    private void initCombMap(int[] combCntArr) {
        for (int r : combCntArr) {
            combMap.put(r, new ArrayList<>());
        }
    }

    private List<MenuComb> getMaxOrderedMenuCombList(List<MenuComb> menuCombList, int maxOrderCnt) {
        return menuCombList.stream()
                .filter(e -> e.isMaxCnt(maxOrderCnt))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Kakao2021_Menu solution = new Kakao2021_Menu();

        String[] orders1 = {"AB", "BA", "ABCD"};
        int[] course1 = {2,3,4};
        String[] solution1 = solution.solution(orders1, course1);
        System.out.println(Arrays.toString(solution1));

        String[] orders2 = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
        int[] course2 = {2,3,5};
        String[] solution2 = solution.solution(orders2, course2);
        System.out.println(Arrays.toString(solution2));

        String[] orders3 = {"XYZ", "XWY", "WXA"};
        int[] course3 = {2,3,4};
        String[] solution3 = solution.solution(orders3, course3);
        System.out.println(Arrays.toString(solution3));
    }
}
