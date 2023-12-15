package subway.validator;

public class RouteValidator {
    private static final String SAME_STATION = "출발역과 도착역이 동일합니다.";

    public static void validate(String startStation, String endStation) {
        if (startStation.equals(endStation)) {
            throw new IllegalArgumentException(SAME_STATION);
        }
    }
}
