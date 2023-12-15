package subway.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import subway.domain.RouteRepository;

public class RouteValidatorTest {
    @DisplayName("출발역과 도착역이 같을 시 예외 반환")
    @Test
    void sameStartAndEndStationThrowsException() {
        String station = "선릉역";

        assertThatThrownBy(() -> RouteValidator.validateByStation(station, station))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("경로가 없을 시 예외 반환")
    @Test
    void InvalidRouteThrowsException() {
        List<String> stationNames = new ArrayList<>(List.of(
                "교대역", "강남역", "역삼역", "선릉역"
        ));
        RouteRepository.addAllStationsByName(stationNames);
        List<String> routeDetails = new ArrayList<>(List.of(
                "교대역,강남역,2,3", "강남역,역삼역,2,3", "교대역,역삼역,5,5"
        ));
        RouteRepository.addAllRoutes(routeDetails);

        assertThatThrownBy(() -> RouteRepository
                .selectRouteBySelectionCriteria("1", new String[]{"교대역", "선릉역"}))
                .isInstanceOf(IllegalArgumentException.class);
    }
}