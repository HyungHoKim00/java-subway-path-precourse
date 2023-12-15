package subway.validator;

import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultWeightedEdge;

public class RouteValidator {
    private static final String SAME_STATION = "출발역과 도착역이 동일합니다.";
    private static final String NO_ROUTE = "경로가 존재하지 않습니다.";

    public static void validateByStation(String startStation, String endStation) {
        if (startStation.equals(endStation)) {
            throw new IllegalArgumentException(SAME_STATION);
        }
    }

    public static void validateByRoute(GraphPath<String, DefaultWeightedEdge> shortestRoute) {
        if (shortestRoute == null) {
            throw new IllegalArgumentException(NO_ROUTE);
        }
    }
}
