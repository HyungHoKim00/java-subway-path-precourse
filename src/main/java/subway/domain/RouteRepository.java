package subway.domain;

import java.util.List;
import java.util.stream.IntStream;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
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
        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstraShortestPath = new DijkstraShortestPath<>(distance);
        return createDetails(startAndEndStation, dijkstraShortestPath);
    }

    private static List<String> selectShortestTimeRoute(String[] startAndEndStation) {
        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstraShortestPath = new DijkstraShortestPath<>(time);
        return createDetails(startAndEndStation, dijkstraShortestPath);
    }

    private static List<String> createDetails(
            String[] startAndEndStation,
            DijkstraShortestPath<String, DefaultWeightedEdge> dijkstraShortestPath) {
        List<String> shortestPath =
                dijkstraShortestPath.getPath(startAndEndStation[0], startAndEndStation[1]).getVertexList();
        double totalDistance = IntStream.range(0, shortestPath.size() - 1)
                .mapToObj(i -> distance.getEdge(shortestPath.get(i), shortestPath.get(i + 1)))
                .mapToDouble(distance::getEdgeWeight)
                .sum();
        double totalTime = IntStream.range(0, shortestPath.size() - 1)
                .mapToObj(i -> time.getEdge(shortestPath.get(i), shortestPath.get(i + 1)))
                .mapToDouble(time::getEdgeWeight)
                .sum();
        shortestPath.add(String.valueOf(totalDistance));
        shortestPath.add(String.valueOf(totalTime));
        return shortestPath;
    }
}
