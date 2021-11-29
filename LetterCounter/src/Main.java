import java.awt.*;
import java.util.Locale;

// Authors: Katie and Carlos.
public class Main {
    public static void main(String[] args) {
        In file = new In("res/para1.txt");
        // In file = new In("res/moby-dick.txt");

        int[] counter = countLetters(file);
        drawGraph(counter);
    }

    static int[] countLetters(In file) {
        int[] counter = new int[26];   // [0]: count for a's; [1]: count for b's, etc.
        while (!file.isEmpty()) {
            String s = file.readLine().toLowerCase();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                // a - 97 => 0; b - 97; => 1, etc.
                if (c >= 'a' && c <= 'z') {
                    int j = c - 97;
                    counter[j] += + 1;
                }
            }
        }
        return counter;
    }

    static void drawGraph(int[] counter) {
        double x = 0.11;
        double y = 0.1;
        double halfHeight = 0;

        StdDraw.square(0.5, 0.5, 0.4);  // Draw the frame.
        StdDraw.text(0.5, 0.95, "Letter Distribution");

        int max = counter[0];
        for (int i: counter) {
            if (i > max)    max = i;
        }

        for (int i = 97; i < 123; i++){
            int c = counter[i - 97];
            if (c > 0) {
                // Calculate halfHeight based on the counter. Maximum count will have a height of 0.4.
                double percentage = (double) (c * 100) / max;
                halfHeight = percentage * 0.004;

                y = halfHeight + 0.1;

                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.filledRectangle(x, y, 0.01, halfHeight);
            }

            // Print the alphabet on the x-axis.
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.text(x, 0.05, Character.toString((char) i));

            x += 0.03;
        }

        StdDraw.text(0.07, 0.09, "0");
        StdDraw.text(0.07, 0.9, Integer.toString(max));
    }
}
