package kakao;
import java.util.*;

class BlackConsumer {
    private final String name;
    private final Set<String> reporters = new HashSet<>();

    public BlackConsumer(String name){
        this.name = name;
    }

    public void addReporter(String name){
        this.reporters.add(name);
    }

    public boolean isMoreReporters(int k){
        return reporters.size() >= k;
    }

    public Set<String> getReporters(){
        return this.reporters;
    }
}

public class Kakao_BlackConsumer {
    static class Solution {
        public int[] solution(String[] id_list, String[] report, int k) {
            Map<String, BlackConsumer> blackConsumerMap = new HashMap<>();
            for (String info : report) {
                String[] split = info.split("\\s");
                String reporter = split[0];
                String reportedId = split[1];

                if(!blackConsumerMap.containsKey(reportedId)){
                    blackConsumerMap.put(reportedId, new BlackConsumer(reportedId));
                }

                BlackConsumer blackConsumer = blackConsumerMap.get(reportedId);
                blackConsumer.addReporter(reporter);
            }

            Map<String, Integer> idReportCntMap = new LinkedHashMap<>();
            for (String id : id_list) {
                idReportCntMap.put(id, 0);
            }

            for (BlackConsumer blackConsumer : blackConsumerMap.values()) {
                if(!blackConsumer.isMoreReporters(k)) continue;

                for (String reporter : blackConsumer.getReporters()) {
                    idReportCntMap.replace(reporter, idReportCntMap.get(reporter)+1);
                }
            }

            return idReportCntMap.values().stream()
                    .mapToInt(e -> e)
                    .toArray();
        }
    }

}
