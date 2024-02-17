package DFSBFS;

public class TravelRoute implements Test{
    
    public static void main(String[] args) {
        TravelRoute travelRoute = new TravelRoute();
        String name = TravelRoute.NAME;
        String[][] tickets = {
                {"ICN", "SFO"}
                , {"ICN", "ATL"}
                , {"SFO", "ATL"}
                , {"ATL", "ICN"}
                , {"ATL","SFO"}
        };
    }
}
