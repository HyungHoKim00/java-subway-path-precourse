package subway.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import subway.domain.Station;
import subway.domain.StationRepository;

public class InputValidatorTest {

    @DisplayName("viewOrExit 입력테스트")
    @Test
    void invalidViewOrExitThrowsException() {
        assertThatThrownBy(() -> InputValidator.validateViewOrExit("2"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("selectionCriteria 입력테스트")
    @Test
    void invalidSelectionCriteriaThrowsException() {
        assertThatThrownBy(() -> InputValidator.validateSelectionCriteria("3"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Station 입력테스트")
    @Test
    void invalidStationThrowsException() {
        String stationName = "선릉역";

        StationRepository.addStation(new Station(stationName));

        assertThatThrownBy(() -> InputValidator.validateStation("강남역"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
