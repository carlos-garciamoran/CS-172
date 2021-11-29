import java.util.ArrayList;

public class Board {
    // grid is a 2D array containing either 'W', 'B' or 'V'
    private char[][] grid;

    // turn is either 'W' or 'B'
    private char turn;

    // x and y keep track of the currently selected square
    private int x;
    private int y;

    // counters for keeping track of the number of checkers in the board
    private int blackCheckers;
    private int whiteCheckers;

    // stores the squares where the selected checker can move
    private ArrayList<int[]> destinationSquares;

    /**
     * Constructor.
     */
    public Board() {
        // Initialise 2D array with W, B, or V values.
        this.grid = new char[8][8];
        for (int x = 0; x < 8; ++x) {
            for (int y = 0; y < 8; ++y) {
                if (x > 0 && x < 7 && (y == 0 || y == 7))
                    this.grid[x][y] = 'B';
                else if (y > 0 && y < 7 && (x == 0 || x == 7))
                    this.grid[x][y] = 'W';
                else this.grid[x][y] = 'V';
            }
        }

        // First turn is black's
        this.turn = 'B';
        this.x = -1;    this.y = -1;
        this.blackCheckers = 0;     this.whiteCheckers = 0;
        this.destinationSquares = new ArrayList<int[]>(64);
    }

    /**
     * Draw the grid with colors according to its values.
     */
    public void draw() {
        StdDraw.clear();
        StdDraw.square(0.5, 0.5, 0.8);

        for (int x = 0; x < 8; ++x) {
            for (int y = 0; y < 8; ++y) {
                // Draw the background square.
                StdDraw.setPenColor(StdDraw.GRAY);
                StdDraw.filledSquare(x, y, 0.5);
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.square(x, y, 0.5);

                // Draw the checker.
                if (this.grid[x][y] == 'W') {
                    StdDraw.setPenColor(StdDraw.WHITE);
                    StdDraw.filledCircle(x, y, 0.4);
                } else if (this.grid[x][y] == 'B') {
                    StdDraw.setPenColor(StdDraw.BLACK);
                    StdDraw.filledCircle(x, y, 0.4);
                }
            }
        }

        if (this.x != -1) {
            if (this.grid[this.x][this.y] != 'V') {
                // Paint surrounding red circle.
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.circle(this.x, this.y, 0.42);
            }

            // Show destination squares.
            this.calculateSquares();
        }

        StdDraw.show();  // Needed due to double buffering.
    }

    /**
     * Rules.
     */
    private void calculateSquares() {
        // For faster reference
        int x = this.x; int y = this.y;
        // Destination squares
        int x2 = -1;    int y2 = -1;
        // Horizontal and vertical counts
        int xc = 0;     int yc = 0;
        // Diagonal counts
        int d1c = 0;    int d2c = 0;

        // Count checkers in the x-axis and y-axis.
        for (int k = 0; k < 8; ++k) {
            if (this.grid[k][y] != 'V') xc++;
            if (this.grid[x][k] != 'V') yc++;
        }
        // StdOut.printf("Counts %d %d\n", xc, yc);

        this.XYMoves(xc, yc);
        this.diagonalMoves(d1c, d2c);       // TODO: a checker may not jump over an enemy checker.

        // Run through the grid and all the legal squares and check the opposite color
        // in front (rule number 2 or whatever)

        this.drawDestinationSquares();
    }

    /**
     * Calculate the vertical and horizontal moves.
     *
     * @param xc horizontal count
     * @param yc vertical count
     */
    public void XYMoves(int xc, int yc) {
        int x = this.x;     int y = this.y;
        boolean rBlock = false;
        boolean lBlock = false;
        boolean upBlock   = false;
        boolean downBlock = false;
        char oT = this.turn == 'W' ? 'B' : 'W';

        // Check if no enemy checker is in between
        for (int i = 0; i < 8; i++) {
            // Horizontal check
            if (this.grid[i][y] == oT && i < x + xc)    rBlock = true;
            if (this.grid[i][y] == oT && i > x - xc)    lBlock = true;

            // Vertical check
            if (this.grid[x][i] == oT && i < y + yc)    upBlock   = true;
            if (this.grid[x][i] == oT && i > y - yc)    downBlock = true;
        }

        // Always check if landing in a vacant or enemy square
        // Check if x is not out of bounds to the right
        if (x + xc <= 7 && this.grid[x + xc][y] != this.turn && !rBlock) {
            this.destinationSquares.add(new int[]{x + xc, y});
        }

        // Check if x is not out of bounds to the left
        if (x - xc >= 0 && this.grid[x - xc][y] != this.turn && !lBlock) {
            this.destinationSquares.add(new int[]{x - xc, y});
        }

        // Check if y is not out of bounds to the top
        if (y + yc <= 7 && this.grid[x][y + yc] != this.turn && !upBlock) {
            this.destinationSquares.add(new int[]{x, y + yc});
        }

        // Check if y is not out of bounds to the bottom
        if (y - yc >= 0 && this.grid[x][y - yc] != this.turn && !downBlock) {
            this.destinationSquares.add(new int[]{x, y - yc});
        }
    }

    /**
     * Calculate the moves for each diagonal.
     *
     * @param d1c northwest and southeast diagonal count
     * @param d2c northeast and southwest diagonal count
     */
    public void diagonalMoves(int d1c, int d2c) {
        int x = this.x;     int y = this.y;

        // Top-left diagonal
        int _x = x;     int _y = y;
        for (int k = 0; k <= x; k++) {
            if (_x < 0 || _y > 7)           break;
            if (this.grid[_x][_y] != 'V')   d1c++;
            //System.out.printf("\t%d %d\n", _x, _y);
            _x--;   _y++;
        }

        // Bottom-right diagonal (initialize at {x,y}-1 so checker is not counted twice)
        _x = x + 1;     _y = y - 1;
        for (int k = 0; k <= 7-x; k++) {
            if (_y < 0 || _x > 7)           break;
            if (this.grid[_x][_y] != 'V')   d1c++;
            _x++;   _y--;
        }

        // Top-right diagonal
        _x = x;     _y = y;
        for (int k = 0; k <= 7-x; k++) {
            if (_y > 7 || _x > 7)           break;
            if (this.grid[_x][_y] != 'V')   d2c++;
            _x++;   _y++;
        }

        // Bottom-left diagonal (initialize at {x,y}-1 so checker is not counted twice)
        _x = x - 1;     _y = y - 1;
        for (int k = 0; k <= x; k++) {
            if (_x < 0 || _y < 0)           break;
            if (this.grid[_x][_y] != 'V')   d2c++;
            _x--;   _y--;
        }

        // Rule: a checker may not jump over an enemy checker.
        boolean nwBlock = false;
        boolean seBlock = false;
        boolean neBlock = false;
        boolean swBlock = false;
        char oT = this.turn == 'W' ? 'B' : 'W';

        // Top-left diagonal
        _x = x;     _y = y;
        for (int k = 0; k < d1c; k++) {
            if (_x < 0 || _y > 7)           break;
            if (this.grid[_x][_y] == oT)    nwBlock = true;
            _x--;   _y++;
        }

        // Bottom-right diagonal
        _x = x;     _y = y;
        for (int k = 0; k < d1c; k++) {
            if (_y < 0 || _x > 7)           break;
            if (this.grid[_x][_y] == oT )   seBlock = true;
            _x++;   _y--;
        }

        // Top-right diagonal
        _x = x;     _y = y;
        for (int k = 0; k < d2c; k++) {
            if (_y > 7 || _x > 7)           break;
            if (this.grid[_x][_y] == oT)    neBlock = true;
            _x++;   _y++;
        }

        // Bottom-left diagonal
        _x = x;     _y = y;
        for (int k = 0; k < d2c; k++) {
            if (_x < 0 || _y < 0)           break;
            if (this.grid[_x][_y] == oT)    swBlock = true;
            _x--;   _y--;
        }

        if (x - d1c >= 0 && y + d1c <= 7 && !nwBlock && this.grid[x - d1c][y + d1c] != this.turn) {
            this.destinationSquares.add(new int[] {x - d1c, y + d1c});
        }

        if (x + d1c <= 7 && y - d1c >= 0 && !seBlock && this.grid[x + d1c][y - d1c] != this.turn) {
            this.destinationSquares.add(new int[] {x + d1c, y - d1c});
        }

        if (x + d2c <= 7 && y + d2c <= 7 && !neBlock && this.grid[x + d2c][y + d2c] != this.turn) {
            this.destinationSquares.add(new int[] {x + d2c, y + d2c});
        }

        if (x - d2c >= 0 && y - d2c >= 0 && !swBlock && this.grid[x - d2c][y - d2c] != this.turn) {
            this.destinationSquares.add(new int[] {x - d2c, y - d2c});
        }
    }

    /**
     * Draw the legal destination squares on the board.
     */
    public void drawDestinationSquares() {
        for (int[] destinationSquare : this.destinationSquares) {
            int x = destinationSquare[0];     int y = destinationSquare[1];

            StdDraw.setPenColor(StdDraw.DARK_GRAY);
            if (this.grid[x][y] == 'V') StdDraw.filledSquare(x, y, 0.5);
            else {
                // Draw a dark gray square.
                StdDraw.filledSquare(x, y, 0.5);

                // Re-draw the enemy circle (checker) over gray square.
                if      (this.grid[x][y] == 'B') StdDraw.setPenColor(StdDraw.BLACK);
                else if (this.grid[x][y] == 'W') StdDraw.setPenColor(StdDraw.WHITE);
                StdDraw.filledCircle(x, y, 0.4);
            }
        }
    }

    /**
     * Handle the first mouse click.
     */
    public void handleMouseClick() {
        while (!StdDraw.isMousePressed()) { }
        int x = (int) Math.round(StdDraw.mouseX());
        int y = (int) Math.round(StdDraw.mouseY());
        while (StdDraw.isMousePressed()) { }

        String color = this.turn == 'W' ? "White" : "Black";
        if (this.grid[x][y] == this.turn) {
            this.x = x;     this.y = y;

            StdOut.printf("%s: select a destination\n", color);

            this.draw();
            this.handleSecondClick();
        } else {
            StdOut.printf("%s: Illegal selection\n", color);
        }
    }

    /**
     * Handle the second mouse click.
     */
    public void handleSecondClick() {
        while (!StdDraw.isMousePressed()) { }
        int x = (int) Math.round(StdDraw.mouseX());
        int y = (int) Math.round(StdDraw.mouseY());
        while (StdDraw.isMousePressed()) { }

        // Move checker only if it's a valid move.
        for (int[] destinationSquare : this.destinationSquares) {
            if (x == destinationSquare[0] && y == destinationSquare[1]) {
                // Remove all the other destination squares
                this.destinationSquares.clear();

                // Make the destination the color of the current checker
                this.grid[x][y] = this.turn;

                // Make the source vacant
                this.grid[this.x][this.y] = 'V';

                // Reset turn to the opposite color
                this.turn = this.turn == 'W' ? 'B' : 'W';

                // Reset x and y
                this.x = -1;        this.y = -1;

                // Redraw the board before returning.
                this.draw();

                return;
            }
        }

        StdOut.println("Illegal move");
    }

    /**
     * Getter for turn.
     * @return char turn
     */
    public char getTurn() {
        return this.turn;
    }

    /**
     * Getter for grid.
     * @return char[][] grid
     */
    public char[][] getGrid() {
        return this.grid;
    }

    /**
     * Set this.grid to grid.
     * @param grid state of the board
     */
    public void setGrid(char[][] grid) {
        this.grid = grid;
    }

    /**
     * Return true if white has won, else return false.
     */
    public boolean whiteHasWon() {
        boolean connected = true;
        char[][] checkers = copyBoard();

        for (int x = 0; x < 8; ++x) {
            for (int y = 0; y < 8; ++y) {
                if (x > 0 && y < 7 && checkers[x - 1][y + 1] == 'W') {
                    checkers[x][y] = 'V';
                } else if (y < 7 && checkers[x][y + 1] == 'W') {
                    checkers[x][y] = 'V';
                } else if (x < 7 && y < 7 && checkers[x + 1][y + 1] == 'W') {
                    checkers[x][y] = 'V';
                } else if (x < 7 && y > 0 && checkers[x + 1][y - 1] == 'W') {
                    checkers[x][y] = 'V';
                } else if (y > 0 && checkers[x][y - 1] == 'W') {
                    checkers[x][y] = 'V';
                } else if (x > 0 && y > 0 && checkers[x - 1][y - 1] == 'W') {
                    checkers[x][y] = 'V';
                } else if (x < 7 && checkers[x + 1][y] == 'W') {
                    checkers[x][y] = 'V';
                } else if (x > 0 && checkers[x - 1][y] == 'W') {
                    checkers[x][y] = 'V';
                } else {
                    connected = false;
                }
            }
        }

        return this.whiteCheckers == 1 || connected;
    }

    /**
     * Return true if black has won, else return false.
     */
    public boolean blackHasWon() {
        boolean connected = true;
        char[][] checkers = copyBoard();

        for (int x = 0; x < 8; ++x) {
            for (int y = 0; y < 8; ++y) {
                if (x > 0 && y < 7 && checkers[x - 1][y + 1] == 'B') {
                    checkers[x][y] = 'V';
                } else if (y < 7 && checkers[x][y + 1] == 'B') {
                    checkers[x][y] = 'V';
                } else if (x < 7 && y < 7 && checkers[x + 1][y + 1] == 'B') {
                    checkers[x][y] = 'V';
                } else if (x < 7 && y > 0 && checkers[x + 1][y - 1] == 'B') {
                    checkers[x][y] = 'V';
                } else if (y > 0 && checkers[x][y - 1] == 'B') {
                    checkers[x][y] = 'V';
                } else if (x > 0 && y > 0 && checkers[x - 1][y - 1] == 'B') {
                    checkers[x][y] = 'V';
                } else if (x < 7 && checkers[x + 1][y] == 'B') {
                    checkers[x][y] = 'V';
                } else if (x > 0 && checkers[x - 1][y] == 'B') {
                    checkers[x][y] = 'V';
                } else {
                    connected = false;
                }
            }
        }

        return this.blackCheckers == 1 || connected;
    }

    private char[][] copyBoard() {
        char[][] checkers = new char[8][8];

        for (int x = 0; x < 8; ++x)
            for (int y = 0; y < 8; ++y) {
                if (this.grid[x][y] == 'W') {
                    checkers[x][y] = 'W';
                    this.whiteCheckers++;
                } else if (this.grid[x][y] == 'B') {
                    checkers[x][y] = 'B';
                    this.blackCheckers++;
                } else
                    checkers[x][y] = 'V';
            }

        return checkers;
    }
}