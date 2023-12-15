package subway.domainTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import subway.domain.RouteRepository;

public class RouteRepositoryTest {
    @BeforeEach
    void before() {
        List<String> stationNames = new ArrayList<>(List.of(
                "교대역", "강남역", "역삼역"
        ));
        RouteRepository.addAllStationsByName(stationNames);
        List<String> routeDetails = new ArrayList<>(List.of(
                "교대역,강남역,2,3", "강남역,역삼역,2,3", "교대역,역삼역,5,5"
        ));
        RouteRepository.addAllRoutes(routeDetails);
    }

    @DisplayName("최단 거리 정보 생성")
    @Test
    void selectRouteBy1ReturnsShortestDistanceRouteDetails() {
        List<String> result = RouteRepository.selectRouteBySelectionCriteria("1", new String[]{"교대역", "역삼역"});
        List<String> expected = new ArrayList<>(List.of("교대역", "강남역", "역삼역", "4", "6"));

        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("최단 거리 정보 생성")
    @Test
    void selectRouteBy2ReturnsShortestTimeRouteDetails() {
        List<String> result = RouteRepository.selectRouteBySelectionCriteria("2", new String[]{"교대역", "역삼역"});
        List<String> expected = new ArrayList<>(List.of("교대역", "역삼역", "5", "5"));

        assertThat(result).isEqualTo(expected);
    }
}
