package subway.view;

import java.util.List;
import java.util.stream.IntStream;

public class OutputView {
    private static final String LINE = "---";

    public void printRoute(List<String> routeDetails) {
        System.out.println();
        printTitle("조회 결과");
        printInfo(LINE);
        printInfo("총 거리: " + routeDetails.get(routeDetails.size() - 2) + "km");
        printInfo("총 소요 시간: " + routeDetails.get(routeDetails.size() - 1) + "분");
        printInfo(LINE);
        IntStream.range(0, routeDetails.size() - 2)
                .forEach(index -> printInfo(routeDetails.get(index)));
        System.out.println();
    }

    public void printViewOrExitRequestMessage() {
        printTitle("메인 화면");
        System.out.println("1. 경로 조회");
        System.out.println("Q. 종료");
        System.out.println();
        printTitle("원하는 기능을 선택하세요.");
    }

    public void printSelectionCriteriaRequestMessage() {
        System.out.println();
        printTitle("경로 기준");
        System.out.println("1. 최단 거리");
        System.out.println("2. 최소 시간");
        System.out.println("B. 돌아가기");
        System.out.println();
        printTitle("원하는 기능을 선택하세요.");
    }

    public void printStartStationRequestMessage() {
        System.out.println();
        printTitle("출발역을 입력하세요.");
    }

    public void printEndStationRequestMessage() {
        System.out.println();
        printTitle("도착역을 입력하세요.");
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
