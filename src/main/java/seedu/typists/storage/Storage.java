package seedu.typists.storage;


import seedu.typists.game.GameRecord;
import seedu.typists.ui.TextUi;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import java.util.logging.Logger;
import java.util.logging.LogManager;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;
import java.util.logging.FileHandler;
import java.util.logging.ConsoleHandler;

public class Storage {

    private static final Logger LOGGER = Logger.getLogger(TextUi.class.getName());

    public static ArrayList<GameRecord> readGameRecords(String gameMode) {

        ArrayList<String> gameRecordsStringArrayList = readFile(gameMode);
        ArrayList<GameRecord> gameRecords = convertToGameRecords(gameRecordsStringArrayList);
        return gameRecords;

    }

    public static void writeGameRecords(ArrayList<GameRecord> gameRecords, String gameMode) {
        assert gameRecords != null;
        ArrayList<String> gameRecordsStringArrayList = convertToGameRecordsStringArrayList(gameRecords);
        writeToFile(gameRecordsStringArrayList, gameMode);
    }

    private static ArrayList<String> readFile(String gameMode) {
        ArrayList<String> fileLines = new ArrayList<>();
        String filename = getFileName(gameMode);
        try {
            File gameRecordsFile = new File(filename);
            Scanner fc = new Scanner(gameRecordsFile);
            while (fc.hasNextLine()) {
                String fileLine = fc.nextLine();
                fileLines.add(fileLine);
            }
        } catch (NullPointerException e) {
            LOGGER.log(Level.SEVERE, "Caught NullPointerException when creating file", e);
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.INFO, "create new file with filename: " + filename);
        }
        return fileLines;
    }

    private static void writeToFile(ArrayList<String> gameRecordsStringArrayList, String gameMode) {
        String filename = getFileName(gameMode);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
            for (String gameRecordString : gameRecordsStringArrayList) {
                bw.write(gameRecordString);
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Caught FileNotFoundException when creating fileWriter", e);
            System.out.print("Failed to write to file.\n");
            System.out.print("File named '" + filename + "' could not be opened for some reasons.\n"
                    + "Ensure that there are no directories named '"
                    + filename
                    + "' in the directory where the .jar file resides.\n");
        }

    }

    private static ArrayList<GameRecord> convertToGameRecords(ArrayList<String> gameRecordsStringArrayList) {
        ArrayList<GameRecord> gameRecords = new ArrayList<>();
        if (gameRecordsStringArrayList.isEmpty()) {
            return gameRecords;
        } else {
            for (String gameRecordString : gameRecordsStringArrayList) {
                GameRecord gameRecord = FileParser.convertStringToGameRecord(gameRecordString);
                gameRecords.add(gameRecord);
            }
            return gameRecords;
        }
    }

    private static ArrayList<String> convertToGameRecordsStringArrayList(ArrayList<GameRecord> gameRecords) {
        ArrayList<String> gameRecordsStringArrayList = new ArrayList<>();
        if (gameRecords.isEmpty()) {
            return gameRecordsStringArrayList;
        }
        for (GameRecord gameRecord : gameRecords) {
            String gameRecordString = convertGameRecordToString(gameRecord);
            gameRecordsStringArrayList.add(gameRecordString);
        }
        return gameRecordsStringArrayList;
    }

    private static String convertGameRecordToString(GameRecord gameRecord) {
        return gameRecord.getStringFormat();
    }


    private static String getFileName(String gameMode) {
        assert ((gameMode == "Time-limited") || (gameMode == "Word-limited"));
        String filename = gameMode.toLowerCase(Locale.ROOT) + "_records.txt";
        return filename;

    }

    private static void setUpLog() {
        LogManager.getLogManager().reset();
        LOGGER.setLevel(Level.ALL);

        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE);
        LOGGER.addHandler(ch);

        try {
            FileHandler fh = new FileHandler(TextUi.class.getName() + ".log");
            fh.setFormatter(new SimpleFormatter());
            fh.setLevel(Level.FINE);
            LOGGER.addHandler(fh);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "File logger failed to set up\n", e);
        }

        LOGGER.info("Set up log in Storage");
    }


}
