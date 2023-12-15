package subway.domain;

import java.util.List;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

public class RouteRepository {
    private static final WeightedMultigraph<String, DefaultWeightedEdge> distance
            = new WeightedMultigraph<>(DefaultWeightedEdge.class);
    private static final WeightedMultigraph<String, DefaultWeightedEdge> time
            = new WeightedMultigraph<>(DefaultWeightedEdge.class);

    public static void addAllStationsByName(List<String> stationNames) {
        stationNames.forEach(name -> {
            distance.addVertex(name);
            time.addVertex(name);
        });
    }

    public static void addAllRoutes(List<String> routes) {
        routes.forEach(route -> {
            String[] details = route.split(",");
            distance.setEdgeWeight(distance.addEdge(details[0], details[1]), Integer.parseInt(details[2]));
            time.setEdgeWeight(time.addEdge(details[0], details[1]), Integer.parseInt(details[3]));
        });
    }
}
