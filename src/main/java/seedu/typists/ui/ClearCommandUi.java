package seedu.typists.ui;

import static seedu.typists.common.Messages.CLEAR_RECORDS;

public class ClearCommandUi {

    public static void displayHelp() {
        //update after finish UG
    }

    public static void displaySuccess(String gameMode) {
        System.out.print(CLEAR_RECORDS + "\n");
        System.out.println("Successfully cleared " + gameMode + " game records.\n");
    }
}
