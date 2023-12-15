package subway.controller;

import java.util.Scanner;
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

    }
}
