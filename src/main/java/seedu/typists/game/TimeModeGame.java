package seedu.typists.game;


import static seedu.typists.common.Messages.MESSAGE_TIME_GAME_END;
import static seedu.typists.common.Utils.getWordLineFromStringArray;
import static seedu.typists.common.Utils.splitStringIntoWordList;
import static seedu.typists.common.Utils.getDisplayLines;

public class TimeModeGame extends Game {
    protected final int timeInSeconds;
    protected final ArrayList<String> wordLists;
    protected int wordsPerLine;
    protected double gameTime;
    protected final boolean isReady;

    public TimeModeGame(int timeInSeconds, String targetWordSet, int wordsPerLine, boolean isReady) {
        super();
        assert targetWordSet != null : "text passed into Time Game should not be null.";
        this.wordLists = splitStringIntoWordList(targetWordSet);
        this.timeInSeconds = timeInSeconds;
        this.wordsPerLine = wordsPerLine;
        this.isReady = isReady;
    }

    public boolean getReady(boolean isReady) {
        if (!isReady) {
            return ui.readyToStartTimer();
        }
        return true;
    }

    public void runGame() {
        Scanner in = new Scanner(System.in);
        List<String> inputs = new ArrayList<>();

        if (getReady(isReady)) {
            int currentRow = 1;
            long beginTime = getTimeNow();
            boolean timeOver = false;

            while (!timeOver) {
                long currTime = getTimeNow() - beginTime;
                if (currTime >= timeInSeconds * 1000L) {
                    gameTime = (double) currTime / 1000;
                    timeOver = true;
                } else {
                    String[] display = getDisplayLines(wordLists, wordsPerLine, currentRow);
                    ui.printLine(display);
                    displayedLines.add(display);
                    inputs.add(in.nextLine());
                    currentRow++;
                }
            }

            updateUserLines(inputs);
            endGame();
        }
    }

    public void endGame() {
        ui.printEnd(MESSAGE_TIME_GAME_END);
        double overshoot = gameTime - timeInSeconds;
        assert overshoot >= 0;
        if (overshoot > 0) {
            ui.printOvershoot(overshoot);
        }
    }

    public void gameSummary() {
        HashMap<String, Object> summary = handleSummary(displayedLines, userLines, gameTime, "Time-limited");
        handleStorage(summary);
    }


    public void updateUserLines(List<String> stringArray) {
        userLines = getWordLineFromStringArray(stringArray);
    }
}

