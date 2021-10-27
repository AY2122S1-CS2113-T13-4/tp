package seedu.typists.content;

import java.util.Random;

/**
 * Generate random string of custom length.
 */
public class RandomGenerator {
    public String randomString(int length) {
        Random isCaps = new Random();
        Random rand = new Random();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (isCaps.nextBoolean()) {
                s.append((char) (rand.nextInt(26) + 'A'));
            } else {
                s.append((char) (rand.nextInt(26) + 'a'));
            }
        }
        return s.toString();
    }
}
