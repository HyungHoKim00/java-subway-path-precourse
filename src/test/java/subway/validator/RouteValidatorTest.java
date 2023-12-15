package subway.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RouteValidatorTest {
    @DisplayName("출발역과 도착역이 같을 시 예외 반환")
    @Test
    void sameStartAndEndStationThrowsException() {
        String station = "선릉역";

        assertThatThrownBy(() -> RouteValidator.validateByStation(station, station))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
