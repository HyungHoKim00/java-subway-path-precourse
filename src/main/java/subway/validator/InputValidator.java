package subway.validator;

import subway.domain.StationRepository;

public class InputValidator {
    private static final String VIEW_OR_EXIT_REGEX = "^([1Q])$";
    private static final String SELECTION_CRITERIA_REGEX = "^([12Q])$";
    private static final String INVALID_INPUT = "잘못된 입력입니다.";

    public static void validateViewOrExit(String viewOrExit) {
        if (!viewOrExit.matches(VIEW_OR_EXIT_REGEX)) {
            throw new IllegalArgumentException(INVALID_INPUT);
        }
    }

    public static void validateSelectionCriteria(String selectionCriteria) {
        if (!selectionCriteria.matches(SELECTION_CRITERIA_REGEX)) {
            throw new IllegalArgumentException(INVALID_INPUT);
        }
    }

    public static void validateStation(String station) {
        if (!StationRepository.contains(station)) {
            throw new IllegalArgumentException(INVALID_INPUT);
        }
    }
}
