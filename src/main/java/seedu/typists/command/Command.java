package seedu.typists.command;

import java.util.ArrayList;

public interface Command {
    void run(ArrayList<String> args);
}
