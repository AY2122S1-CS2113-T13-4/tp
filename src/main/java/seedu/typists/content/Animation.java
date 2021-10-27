package seedu.typists.content;

public class Animation {
    private String lastLine = "";

    public void print(String line) {
        //clear the last line if longer
        if (lastLine.length() > line.length()) {
            String temp = "";
            for (int i = 0; i < lastLine.length(); i++) {
                temp += " ";
            }
            if (temp.length() > 1) {
                System.out.print("\r" + temp);
            }
        }
        System.out.print("\r" + line);
        lastLine = line;
    }

    private byte animWait;

    public void resetAnimWait() {
        animWait = 1;
    }

    public void animateWait(String s) {
        switch (animWait) {
        case 1:
            print("[ \\ ] " + s);
            break;
        case 2:
            print("[ | ] " + s);
            break;
        case 3:
            print("[ / ] " + s);
            break;
        default:
            animWait = 0;
            print("[ - ] " + s);
        }
        animWait++;
    }

    private byte animLeft;
    private byte animRight;

    public void resetAnimLeft() {
        animLeft = 1;
    }

    public void resetAnimRight() {
        animRight = 1;
    }

    public void animateLeft(String s) {
        switch (animLeft) {
        case 1:
            print("     | " + s);
            break;
        case 2:
            print("    | " + s);
            break;
        case 3:
            print("   | " + s);
            break;
        case 4:
            print("  | " + s);
            break;
        case 5:
            print(" | " + s);
            break;
        default:
            animLeft = 0;
            print("| " + s);
        }
        animLeft++;
    }

    public void animateRight(String s) {
        switch (animRight) {
        case 1:
            print("| " + s);
            break;
        case 2:
            print(" | " + s);
            break;
        case 3:
            print("  | " + s);
            break;
        case 4:
            print("   | " + s);
            break;
        case 5:
            print("    | " + s);
            break;
        default:
            animRight = 0;
            print("     | " + s);
        }
        animRight++;
    }
}
