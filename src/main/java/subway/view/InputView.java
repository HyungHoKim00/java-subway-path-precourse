package subway.view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readViewOrExit() {
        String viewOrExit = scanner.nextLine();
        InputValidator.validateViewOrExit(viewOrExit);
        return viewOrExit;
    }

    public String readSelectionCriteria() {
        String selectionCriteria = scanner.nextLine();
        InputValidator.validateSelectionCriteria(selectionCriteria);
        return selectionCriteria;
    }

    public String readStation() {
        String station = scanner.nextLine();
        InputValidator.validateStation(station);
        return station;
    }
}
