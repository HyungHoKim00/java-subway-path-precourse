package subway;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import subway.controller.PathController;
import subway.domain.LineRepository;
import subway.domain.RouteRepository;
import subway.domain.StationRepository;
import subway.view.OutputView;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        doSetting();
        PathController pathController = new PathController(scanner, new OutputView());
        pathController.run();
    }

    private static void doSetting() {
        addBasicStation();
        addBasicLine();
        addBasicRoute();
    }

    private static void addBasicStation() {
        List<String> stationNames = new ArrayList<>(List.of(
                "교대역", "강남역", "역삼역", "남부터미널역", "양재역", "양재시민의숲역", "매봉역"
        ));
        StationRepository.addAllStationsByName(stationNames);
        RouteRepository.addAllStationsByName(stationNames);
    }

    private static void addBasicLine() {
        List<String> lineNames = new ArrayList<>(List.of(
                "2호선", "3호선", "신분당선"
        ));
        LineRepository.addAllLinesByName(lineNames);
    }

    private static void addBasicRoute() {
        List<String> routeDetails = new ArrayList<>(List.of(
                "교대역,강남역,2,3", "강남역,역삼역,2,3"
                , "교대역,남부터미널역,3,2", "남부터미널역,양재역,6,5", "양재역,매봉역,1,1"
                , "강남역,양재역,2,8", "양재역,양재시민의숲역,10,3"
        ));
        RouteRepository.addAllRoutes(routeDetails);
    }
}
