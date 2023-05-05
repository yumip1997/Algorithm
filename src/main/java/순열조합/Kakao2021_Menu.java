package 순열조합;

import java.util.*;
import java.util.stream.Collectors;

public class Kakao2021_Menu{

    private static final Map<String, Integer> COMB_MENU_CNT_MAP = new HashMap<>();
    private static final int MINIMUM_COMB_CNT = 2;

    public String[] solution(String[] orders, int[] course) {
        makeMenuCombination(orders, course);

        Map<Integer, List<String>> menuLengthMap = getMenuLengthMap();
        Map<Integer, Integer> menuLengthOrderCntMap = new HashMap<>();

        for (String combMenu : COMB_MENU_CNT_MAP.keySet()) {
            int len = combMenu.length();
            int orderCnt = COMB_MENU_CNT_MAP.get(combMenu);
            int maxOrderCnt = Math.max(orderCnt, menuLengthOrderCntMap.getOrDefault(len, 0));
            menuLengthOrderCntMap.put(len, maxOrderCnt);
        }

        List<String> totalMaxOrderMenuCombList = new ArrayList<>();
        for (Integer len : menuLengthOrderCntMap.keySet()) {
            Integer maxCnt = menuLengthOrderCntMap.get(len);
            if(maxCnt < MINIMUM_COMB_CNT){
                continue;
            }

            List<String> maxOrderMenuCombList = getMaxOrderMenuCombList(menuLengthMap, len, maxCnt);
            totalMaxOrderMenuCombList.addAll(maxOrderMenuCombList);
        }

        totalMaxOrderMenuCombList.sort(Comparator.naturalOrder());

        return totalMaxOrderMenuCombList.toArray(new String[0]);
    }

    private void makeMenuCombination(String[] orders, int[] course) {
        for (String order : orders) {

            char[] chars = order.toCharArray();
            Arrays.sort(chars);

            int n = chars.length;
            for (int r : course) {
                boolean[] visited = new boolean[n];
                makeCombination(chars, visited, 0, n, r);
            }
        }
    }

    private void makeCombination(char[] menus, boolean[] visited, int depth, int n, int r){
        if(r == 0){
            StringBuilder menuCombStrBuilder = new StringBuilder();
            for(int i=0;i<visited.length;i++){
                if(visited[i]){
                    menuCombStrBuilder.append(menus[i]);
                }
            }
            String menuCombStr = menuCombStrBuilder.toString();
            COMB_MENU_CNT_MAP.put(menuCombStr, COMB_MENU_CNT_MAP.getOrDefault(menuCombStr, 0) +1);
        }

        for(int i=depth;i<n;i++){
            if(!visited[i]){
                visited[i] = true;
                makeCombination(menus, visited, i+1, n, r-1);
                visited[i] = false;
            }
        }
    }

    private Map<Integer, List<String>> getMenuLengthMap() {
        return COMB_MENU_CNT_MAP.keySet().stream()
                .collect(Collectors.groupingBy(String::length));
    }

    private List<String> getMaxOrderMenuCombList(Map<Integer, List<String>> menuLengthMap, Integer len, Integer maxCnt) {
        return menuLengthMap.get(len)
                .stream()
                .filter(e -> Objects.equals(COMB_MENU_CNT_MAP.get(e), maxCnt))
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
