package subway.view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readViewOrExit() {
        String viewOrExit = scanner.nextLine();
        InputValidator.validateViewOrExit(viewOrExit);
    }
}
