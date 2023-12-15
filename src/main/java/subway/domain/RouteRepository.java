package subway.domain;

import java.util.List;
import java.util.stream.IntStream;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.validator.RouteValidator;

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

    public static boolean containsRoute(String[] startAndEndStation) {
        return distance.getEdge(startAndEndStation[0], startAndEndStation[1]) != null;
    }

    public static List<String> selectRouteBySelectionCriteria(String selectionCriteria, String[] startAndEndStation) {
        if (selectionCriteria.equals("1")) {
            return selectShortestDistanceRoute(startAndEndStation);
        }
        if (selectionCriteria.equals("2")) {
            return selectShortestTimeRoute(startAndEndStation);
        }
        return null;
    }

    private static List<String> selectShortestDistanceRoute(String[] startAndEndStation) {
        DijkstraShortestPath<String, DefaultWeightedEdge> shortestPath = new DijkstraShortestPath<>(distance);
        return createDetails(startAndEndStation, shortestPath);
    }

    private static List<String> selectShortestTimeRoute(String[] startAndEndStation) {
        DijkstraShortestPath<String, DefaultWeightedEdge> shortestPath = new DijkstraShortestPath<>(time);
        return createDetails(startAndEndStation, shortestPath);
    }

    private static List<String> createDetails(
            String[] startAndEndStation,
            DijkstraShortestPath<String, DefaultWeightedEdge> shortestPath) {
        GraphPath<String, DefaultWeightedEdge> shortestRoute
                = shortestPath.getPath(startAndEndStation[0], startAndEndStation[1]);
        RouteValidator.validateByRoute(shortestRoute);
        List<String> pathDetails = shortestRoute.getVertexList();
        double totalDistance = IntStream.range(0, pathDetails.size() - 1)
                .mapToObj(index -> distance.getEdge(pathDetails.get(index), pathDetails.get(index + 1)))
                .mapToDouble(distance::getEdgeWeight)
                .sum();
        double totalTime = IntStream.range(0, pathDetails.size() - 1)
                .mapToObj(index -> time.getEdge(pathDetails.get(index), pathDetails.get(index + 1)))
                .mapToDouble(time::getEdgeWeight)
                .sum();
        pathDetails.add(String.valueOf((int) totalDistance));
        pathDetails.add(String.valueOf((int) totalTime));
        return pathDetails;
    }
}
