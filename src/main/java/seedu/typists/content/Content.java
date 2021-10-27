package seedu.typists.content;

import seedu.typists.exception.InvalidArticleException;
import seedu.typists.ui.TextUi;

import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;
import static seedu.typists.common.Messages.SAMPLE_TEXT;

public class Content {
    private String content = SAMPLE_TEXT;
    private final TextUi ui;

    public Content() {
        this.ui = new TextUi();
    }

    public String getContent() {
        return content;
    }

    public void setContent() {
        ui.printLetter();
        ui.printLine("Content selection (input 0 to go back):");
        ui.printLine("1. Opening of famous books");
        ui.printLine("2. Wikipedia article");
        ui.printLine("3. Random sentence of custom length");

        Scanner in = new Scanner(System.in);
        int command = -1;
        do {
            ui.printLine("Enter your selection: ");
            try {
                command = in.nextInt();
            } catch (InputMismatchException e) {
                ui.printLine("Invalid selection.");
                in.nextLine();
            }
        } while (command < 0 || command > 3);
        switch (command) {
        case 1:
            setBooks();
            break;
        case 2:
            setWikipedia();
            break;
        case 3:
            setRandomContent();
            break;
        default:
            break;
        }
    }

    private void setBooks() {
        Scanner in = new Scanner(System.in);
        ui.printBookSelection();
        int selection = -1;
        do {
            ui.printLine("Enter your selection:");
            try {
                selection = in.nextInt();
            } catch (InputMismatchException e) {
                ui.printLine("Not an integer.");
                in.nextLine();
            }
        } while (selection < 0 || selection > 15);
        if (selection == 0) {
            setContent();
        } else {
            this.content = Books.getBook(selection - 1);
            assert this.content.length() > 0;
            ui.printLine("Content set");
        }
    }

    private void setWikipedia() {
        Scanner in = new Scanner(System.in);
        WikiImport wi = new WikiImport();
        String title;
        boolean articleNotFound = true;
        do {
            ui.printLine("Enter the article name (input 0 to go back):");
            title = in.nextLine();
            if (title.equals("0")) {
                articleNotFound = false;
                setContent();
            } else {
                try {
                    String temp = wi.getArticle(title);
                    articleNotFound = false;
                    this.content = temp;
                    assert this.content.length() > 0;
                    ui.printLine("Content set");
                } catch (InvalidArticleException e) {
                    ui.printLine(e.getMessage());
                }
            }
        } while (articleNotFound);
    }

    private void setRandomContent() {
        Scanner in = new Scanner(System.in);
        RandomGenerator rg = new RandomGenerator();
        int length = -1;
        do {
            ui.printLine("Enter the word count (between 5 - 100, input 0 to go back):");
            try {
                length = in.nextInt();
            } catch (InputMismatchException e) {
                ui.printLine("Invalid selection.");
                in.nextLine();
            }
        } while (length < 0 || length > 100);
        if (length < 5) {
            setContent();
        } else {
            String s = "";
            Random r = new Random();
            for (int i = 0; i < length; i++) {
                s += (rg.randomString(r.nextInt(10) + 5) + " ");
            }
            this.content = s;
            assert this.content.length() > 0;
            ui.printLine("Content set");
        }
    }
}
