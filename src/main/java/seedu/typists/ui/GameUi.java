package seedu.typists.ui;

import java.util.Scanner;

public class GameUi extends TextUi {

    public void printEnd(String endMessage) {
        printScreen(endMessage);
    }

    public boolean readyToStartTimer() {
        Scanner in = new Scanner(System.in);
        String command = "";
        while (!command.equals("yes")) {
            printScreen("Do you want to start now?");
            command = in.nextLine();
        }
        return true;
    }

    public void printOvershoot(double overshoot) {
        printScreen(
                "While on your last sentence, "
                        + "you exceeds the game by "
                        + String.format("%.2f", overshoot)
                        + " seconds."
        );
    }

    public void printGameMode1Progress(int a, int b) throws InterruptedException {
        viewAnimateLeft("Your progress:" + String.valueOf(a) + "/" + String.valueOf(b));
    }

}
