package subway.view;

import java.util.List;
import java.util.stream.IntStream;

public class OutputView {
    private static final String LINE = "---";

    public void printRoute(List<String> routeDetails) {
        printInfo(LINE);
        printInfo("총 거리: " + routeDetails.get(routeDetails.size() - 2) + "km");
        printInfo("총 소요 시간: " + routeDetails.get(routeDetails.size() - 1) + "분");
        printInfo(LINE);
        IntStream.range(0, routeDetails.size() - 2)
                .forEach(index -> printInfo(routeDetails.get(index)));
        System.out.println();
    }

    public void printSelectionCriteriaRequestMessage() {
    }

    public void printStartStationRequestMessage() {
    }

    public void printEndStationRequestMessage() {
    }


    private void printTitle(String title) {
        System.out.println("## " + title);
    }

    private void printInfo(String info) {
        System.out.println("[INFO] " + info);
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println("[ERROR] " + errorMessage);
    }
}
