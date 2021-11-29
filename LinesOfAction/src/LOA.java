// Katie and Carlos.

public class LOA {
    public static void main(String[] args) {
        Board board = new Board();
        StdDraw.setScale(-0.5, 8 - 0.5);
        StdDraw.enableDoubleBuffering();  // Avoid flickering

        while (true) {
            if (board.getTurn() == 'B') StdOut.printf("Black");
            else                        StdOut.printf("White");
            StdOut.println(": select a checker");

            board.draw();
            board.handleMouseClick();

            if (board.whiteHasWon()) {
                StdOut.println("WHITE won!");
                break;
            } else if (board.blackHasWon()) {
                StdOut.println("BLACK won!");
                break;
            }
        }

        StdOut.println("FINISH");
    }
}
