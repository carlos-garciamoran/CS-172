/** Sudoku solver. */

// Carlos and Katie
public class Sudoku {
    /** Prints the solution to the puzzle in the specified directory. */
    public static void main(String[] args) {
        String puzzle = new In("sudoku1.txt").readAll();
        Square[][] grid = createSquares(puzzle);
        solve(grid);
        StdOut.println(toString(grid));
    }

    /** Returns a new Location object with the specified row and column. */
    static Location createLocation(int r, int c) {
        Location location = new Location();
        location.row = r;
        location.column = c;

        return location;
    }

    /** Returns an array of the eight Locations in the same row as here. */
    static Location[] findRow(Location here) {
        int column = 0;
        Location[] locations = new Location[8];

        for (int i = 0; i < locations.length; i++) {
            if (here.column == i) column++;
            locations[i] = Sudoku.createLocation(here.row, column);
            column++;
        }

        return locations;
    }

    /** Returns an array of the eight Locations in the same column as here. */
    static Location[] findColumn(Location here) {
        int row = 0;
        Location[] locations = new Location[8];

        for (int i = 0; i < locations.length; i++) {
            if (here.row == i) row++;
            locations[i] = Sudoku.createLocation(row, here.column);
            row++;
        }

        return locations;
    }

    /** Returns an array of the eight Locations in the same 3x3 block as here. */
    static Location[] findBlock(Location here) {
        Location[] locations = new Location[8];
        int column = here.column/3*3, row = here.row/3*3;
        for (int i = 0; i < locations.length; i++) {
            if (column==here.column && row==here.row) i--;
            else {
                assert 0 <= row && row <= 8;
                assert 0 <= column && column <= 8;
                locations[i] = Sudoku.createLocation(row, column);
            }

            column++;
            if (column >= here.column/3*3 +3) {
                column= here.column/3*3;
                row++;
            }
        }

        return locations;
    }

    /**
     * Returns a 9x9 array of Squares, each of which has value 0 and knows about the other squares in its row,
     * column, and block.
     */
    static Square[][] createSquares() {
        Square[][] grid = new Square[9][9];

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                grid[r][c] = new Square();
                grid[r][c].value = 0;
                grid[r][c].row = new Square[8];
                grid[r][c].column = new Square[8];
                grid[r][c].block = new Square[8];
            }
        }

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                Location[] row = findRow(createLocation(r, c));
                Location[] column = findColumn(createLocation(r, c));
                Location[] block = findBlock(createLocation(r, c));
                for (int i = 0; i < 8; i++) {
                    grid[r][c].row[i] = grid[row[i].row][row[i].column];
                    grid[r][c].column[i] = grid[column[i].row][column[i].column];
                    grid[r][c].block[i] = grid[block[i].row][block[i].column];
                }
            }
        }

        return grid;
    }

    /** Returns a String representing grid, showing the numbers (or . for squares with value 0). */
    static String toString(Square[][] grid) {
        String s = "";
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (grid[x][y].value == 0)
                    s += ".";
                else
                    s += grid[x][y].value;

                if (y == 8)     s += "\n";
            }
        }

        return s;
    }

    /**
     * Returns a 9x9 array of Squares, each of which has value 0 and knows about the other squares in its row,
     * column, and block. Any numbers in diagram are filled in to the grid; empty squares should be given as
     * periods.
     */
    static Square[][] createSquares(String diagram) {
        Square[][] grid = createSquares();

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                int value = diagram.substring(r * 10, r * 10 + 9).charAt(c);
                if (value == '.') grid[r][c].value = 0;
                else    grid[r][c].value = value - 48;
            }
        }

        return grid;
    }

    /**
     * Returns a boolean array of length 10. For each digit, the corresponding entry in the array is true
     * if that number does not appear elsewhere in s's row, column, or block.
     */
    static boolean[] findValidNumbers(Square s) {
        boolean[] array = new boolean[10];

        for (int i = 0; i < 10; i++) {
            array[i] = true;
            for (int j = 0; j < 8; j++) {
                if (s.row[j].value == i || s.column[j].value == i || s.block[j].value == i) {
                    array[i] = false;
                    break;
                }
            }
        }

        return array;
    }

    /**
     * Returns true if grid can be solved. If so, grid is modified to fill in that solution.
     */
    static boolean solve(Square[][] grid) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                Square square = grid[r][c];
                if (square.value == 0) {
                    boolean[] numbers = findValidNumbers(square);
                    for (int k = 1; k < 10; k++) {
                        if (numbers[k]) {
                            grid[r][c].value = k;
                            if (solve(grid)) return true;

                            grid[r][c].value = 0;
                        }
                    }

                    return false;
                }
            }
        }

        return true;
    }

}
