package seedu.typists.game;

import seedu.typists.exception.InvalidStringInputException;
import seedu.typists.ui.TextUi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static seedu.typists.common.Utils.getWordLineFromStringArray;
import static seedu.typists.common.Utils.getWordLines;
import static seedu.typists.parser.StringParser.splitString;

public class WordLimitGame extends Game {
    private ArrayList<String> eachWord;
    protected ArrayList<String[]> wordLines;
    protected int wordLimit;
    private int gameIndex;
    private final int numberOfWordsDisplayed;
    private final String content1;
    private long beginTime;


    public WordLimitGame(String targetWordSet, int wordsPerLine) {
        super();
        this.eachWord = new ArrayList<>(100);
        this.gameIndex = 0;
        this.numberOfWordsDisplayed = wordsPerLine;
        this.content1 = targetWordSet;
        this.wordLimit = getWordLimit();
    }

    @Override
    public void runGame() {
        game();
        wordLimitGameSummary();
    }

    public int getTotalSentence() {
        return eachWord.size();
    }

    public int getWordLimit() {
        Scanner in = new Scanner(System.in);
        ui.printScreen("Enter how many words you want the game to run: ");

        try {
            return Integer.parseInt(in.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Not a Number!");
            return getWordLimit();
        }
    }

    public void trimContent(int wordLimit) {
        try {
            eachWord = splitString(content1, " ");
        } catch (InvalidStringInputException e) {
            e.printStackTrace();
        }
        eachWord = new ArrayList<>(eachWord.subList(0, wordLimit));
        wordLines = getWordLineFromStringArray(eachWord);
    }

    public void game()  {
        trimContent(wordLimit);
        beginTime = getTimeNow();
        boolean isExit = false;
        int totalError = 0;

        String actualWord = "";
        String inputWord = "";

        while (!isExit) {
            assert gameIndex < getTotalSentence() : "There are still texts to be typed.";
            String temp = "";
            int number = 0;

            while (gameIndex < getTotalSentence()) {
                temp += eachWord.get(gameIndex) + " ";
                gameIndex += 1;
                number += 1;
                if (number >= numberOfWordsDisplayed) {
                    break;
                }
            }

            actualWord += temp;
            temp = temp.trim();
            ui.printLine(temp);
            String fullCommand = ui.readCommand();
            inputWord += fullCommand + " ";

            if (fullCommand.equals("Exit")) {
                try {
                    ui.showAnimatedWordLimitSummary(totalError, gameIndex);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }

            //update for summary
            List<String> inputs = new ArrayList<>();
            inputs.add(fullCommand);
            updateUserLines(inputs);

            WordLimitDataProcessor recordError = new WordLimitDataProcessor(fullCommand, temp);
            try {
                totalError += recordError.getError();
            } catch (InvalidStringInputException e) {
                e.printStackTrace();
                //do something
            }

            try {
                ui.printGameMode1Progress(gameIndex, getTotalSentence());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (gameIndex >= getTotalSentence()) {
                try {
                    ui.showAnimatedError(
                            splitString(actualWord.trim(), " "),
                            splitString(inputWord.trim(), " "),
                            getTotalSentence()
                    );
                } catch (InterruptedException | InvalidStringInputException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public void wordLimitGameSummary() {
        double realGameTime = duration(beginTime, getTimeNow());
        HashMap<String, Object> summary = handleSummary(wordLines, userLines, realGameTime, "Word-limited");
        handleStorage(summary);
    }

    public void updateUserLines(List<String> stringArray) {
        userLines = getWordLineFromStringArray(stringArray);
    }
}
