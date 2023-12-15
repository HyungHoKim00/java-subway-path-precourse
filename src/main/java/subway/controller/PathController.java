package subway.controller;

import java.util.Scanner;
import subway.domain.RouteRepository;
import subway.validator.RouteValidator;
import subway.view.InputView;
import subway.view.OutputView;

public class PathController {
    private final InputView inputView;
    private final OutputView outputView;

    public PathController(Scanner scanner, OutputView outputView) {
        this.inputView = new InputView(scanner);
        this.outputView = outputView;
    }

    public void run() {
        if (createValidatedViewOrExit().equals("Q")) {
            return;
        }
        selectRoute(createValidatedSelectionCriteria());
    }

    private void selectRoute(String selectionCriteria) {
        if (selectionCriteria.equals("B")) {
            run();
        }
        String[] startAndEndStation = createValidatedStartAndEndStation();
        outputView.printRoute(RouteRepository.selectRouteBySelectionCriteria(selectionCriteria, startAndEndStation));
        run();
    }


    private String createValidatedViewOrExit() {
        outputView.printViewOrExitRequestMessage();
        String viewOrExit;
        while (true) {
            try {
                viewOrExit = inputView.readViewOrExit();
                return viewOrExit;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private String createValidatedSelectionCriteria() {
        outputView.printSelectionCriteriaRequestMessage();
        String selectionCriteria;
        while (true) {
            try {
                selectionCriteria = inputView.readSelectionCriteria();
                return selectionCriteria;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private String[] createValidatedStartAndEndStation() {
        String startStation;
        String endStation;
        try {
            outputView.printStartStationRequestMessage();
            startStation = inputView.readStation();
            outputView.printEndStationRequestMessage();
            endStation = inputView.readStation();
            RouteValidator.validate(startStation, endStation);
            return new String[]{startStation, endStation};
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            selectRoute(createValidatedSelectionCriteria());
        }
        return null;
    }
}
