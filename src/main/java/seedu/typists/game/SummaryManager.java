package seedu.typists.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class SummaryManager {

    public static HashMap<String, Object> generateSummary(
            ArrayList<String[]> expectedInput, ArrayList<String[]> actualInput, double timeElapsed, String gameMode) {

        assert timeElapsed >= 0;
        assert expectedInput.size() > 0;
        assert actualInput != null;
        assert ((gameMode.equals("Word-limited")) || (gameMode.equals("Time-limited")));

        HashMap<String, Object> summary = new HashMap<>();
        summary.put("timeElapsed", timeElapsed);
        summary.put("gameMode", gameMode);
        summary.put("errorWordCount", calculateErrorWordCount(actualInput, expectedInput));
        summary.put("correctWordCount", calculateCorrectWordCount(actualInput, expectedInput));
        summary.put("totalWordCount", calculateTotalWordCount(expectedInput));
        summary.put("errorWordPercentage", calculateErrorWordsPercentage(actualInput, expectedInput));
        summary.put("correctWordPercentage", calculateCorrectWordsPercentage(actualInput, expectedInput));
        summary.put("wordsPerMinute", calculateWpm(timeElapsed, actualInput, expectedInput));
        summary.put("errorWords", getErrorWords(actualInput, expectedInput));
        return summary;
    }

    private static ArrayList<String> getErrorWords(
            ArrayList<String[]> actualInput, ArrayList<String[]> expectedInput) {

        ArrayList<String> errorWords = new ArrayList<>();

        if (actualInput.isEmpty()) {
            ArrayList<String> mergedExpectedInput = mergeArrays(expectedInput);
            return mergedExpectedInput;
        }

        int totalExpectedLines = expectedInput.size();
        int outerLoopCount;
        for (outerLoopCount = 0; outerLoopCount < totalExpectedLines; outerLoopCount++) {

            if (actualInput.size() == outerLoopCount) {
                ArrayList<String[]> remainingLines = new ArrayList<>(
                        expectedInput.subList(outerLoopCount, totalExpectedLines)
                );
                ArrayList<String> mergedRemainingLines = mergeArrays(remainingLines);
                errorWords.addAll(mergedRemainingLines);
                break;
            }

            ArrayList<String> expectedLine = new ArrayList<>(Arrays.asList(expectedInput.get(outerLoopCount)));
            assert expectedLine.size() > 0;
            ArrayList<String> actualLine = new ArrayList<>(Arrays.asList(actualInput.get(outerLoopCount)));

            int innerLoopCount = 0;
            for (String expectedWord : expectedLine) {

                if (actualLine.size() == 0) {
                    ArrayList<String> remainingWords = new ArrayList<>(
                            expectedLine.subList(innerLoopCount, expectedLine.size())
                    );
                    errorWords.addAll(remainingWords);
                    break;
                }

                int result = actualLine.indexOf(expectedWord);

                if (result == -1) {
                    String errorWord = expectedWord;
                    errorWords.add(errorWord);
                } else {
                    actualLine.remove(result);
                }

                innerLoopCount++;
            }

        }
        return errorWords;
    }

    private static int calculateCorrectWordCount(ArrayList<String[]> actualInput, ArrayList<String[]> expectedInput) {
        int errorWordCount = calculateErrorWordCount(actualInput, expectedInput);
        assert errorWordCount >= 0;
        int totalWordCount = calculateTotalWordCount(expectedInput);
        assert totalWordCount >= errorWordCount;
        int correctWordCount = totalWordCount - errorWordCount;
        return correctWordCount;

    }

    private static Double calculateCorrectWordsPercentage(
            ArrayList<String[]> actualInput, ArrayList<String[]> expectedInput) {
        int correctWordCount = calculateCorrectWordCount(actualInput, expectedInput);
        int totalWordCount = calculateTotalWordCount(expectedInput);
        double correctWordsPercentage = ((double) correctWordCount / (double) totalWordCount) * 100;
        return correctWordsPercentage;
    }

    private static Double calculateWpm(
            Double timeElapsed, ArrayList<String[]> actualInput, ArrayList<String[]> expectedInput) {
        int correctWordCount = calculateCorrectWordCount(actualInput, expectedInput);
        double wordPerMinute = ((double) correctWordCount / timeElapsed) * 60;
        return wordPerMinute;
    }


    private static int calculateErrorWordCount(ArrayList<String[]> actualInput, ArrayList<String[]> expectedInput) {
        int errorWordCount = getErrorWords(actualInput, expectedInput).size();
        return errorWordCount;
    }

    private static Double calculateErrorWordsPercentage(
            ArrayList<String[]> actualInput, ArrayList<String[]> expectedInput) {
        int errorWordCount = calculateErrorWordCount(actualInput, expectedInput);
        int totalWordCount = calculateTotalWordCount(expectedInput);
        double errorWordsPercentage = ((double) errorWordCount / (double) totalWordCount) * 100;
        return errorWordsPercentage;
    }

    private static int calculateTotalWordCount(ArrayList<String[]> expectedInput) {
        ArrayList<String> mergedExpectedInput = mergeArrays(expectedInput);
        int totalWordCount = mergedExpectedInput.size();
        return totalWordCount;
    }

    private static ArrayList<String> mergeArrays(ArrayList<String[]> arrayToMerge) {
        ArrayList<String> mergedArr = new ArrayList<>();

        for (String[] array : arrayToMerge) {
            ArrayList<String> tempArr = new ArrayList<>(Arrays.asList(array));
            mergedArr.addAll(tempArr);
        }

        return mergedArr;
    }

}
