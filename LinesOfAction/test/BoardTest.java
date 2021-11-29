import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @org.junit.jupiter.api.Test
    void there_are_12_white_pieces_at_the_start() {
        Board board = new Board();
        int whiteCheckers = 0;

        for (int x = 0; x < 8; ++x)
            for (int y = 0; y < 8; ++y)
                if (board.getGrid()[x][y] == 'W') whiteCheckers++;

        assertEquals(true, whiteCheckers == 12);
    }

    @org.junit.jupiter.api.Test
    void there_are_12_black_pieces_at_the_start() {
        Board board = new Board();
        int blackCheckers = 0;

        for (int x = 0; x < 8; ++x)
            for (int y = 0; y < 8; ++y)
                if (board.getGrid()[x][y] == 'B') blackCheckers++;

        assertEquals(true, blackCheckers == 12);
    }

    @org.junit.jupiter.api.Test
    void there_are_40_vacant_pieces_at_the_start() {
        Board board = new Board();
        int vacantSquares = 0;

        for (int x = 0; x < 8; ++x)
            for (int y = 0; y < 8; ++y)
                if (board.getGrid()[x][y] == 'V') vacantSquares++;

        assertEquals(true, vacantSquares == 40);
    }

    @org.junit.jupiter.api.Test
    void white_wins_if_it_has_a_single_checker() {
        Board board = new Board();
        char[][] grid = {
            {'V', 'V', 'V', 'V', 'V', 'V', 'V', 'V'},
            {'V', 'V', 'V', 'V', 'V', 'V', 'V', 'V'},
            {'V', 'V', 'V', 'V', 'V', 'V', 'V', 'V'},
            {'V', 'V', 'V', 'V', 'V', 'V', 'V', 'V'},
            {'V', 'V', 'V', 'W', 'V', 'V', 'V', 'V'},
            {'V', 'V', 'V', 'V', 'V', 'V', 'V', 'V'},
            {'V', 'V', 'V', 'V', 'V', 'V', 'V', 'V'},
            {'V', 'V', 'V', 'V', 'V', 'V', 'V', 'V'},
        };

        board.setGrid(grid);
        assertEquals(true, board.whiteHasWon());
    }

    @org.junit.jupiter.api.Test
    void white_does_not_win_if_it_its_checkers_are_not_connected() {
        Board board = new Board();
        char[][] grid = {
                {'V', 'V', 'V', 'V', 'V', 'V', 'V', 'V'},
                {'V', 'W', 'V', 'V', 'V', 'V', 'V', 'V'},
                {'V', 'V', 'V', 'V', 'V', 'V', 'V', 'V'},
                {'V', 'V', 'V', 'V', 'V', 'V', 'V', 'V'},
                {'V', 'V', 'V', 'W', 'V', 'V', 'V', 'V'},
                {'V', 'V', 'V', 'V', 'V', 'V', 'V', 'V'},
                {'V', 'V', 'V', 'V', 'V', 'V', 'V', 'V'},
                {'V', 'V', 'V', 'V', 'V', 'V', 'V', 'V'},
        };

        board.setGrid(grid);
        assertEquals(false, board.whiteHasWon());
    }

    @org.junit.jupiter.api.Test
    void black_wins_if_it_has_a_single_checker() {
        Board board = new Board();
        char[][] grid = {
                {'V', 'V', 'V', 'V', 'V', 'V', 'V', 'V'},
                {'V', 'V', 'V', 'V', 'V', 'V', 'V', 'V'},
                {'V', 'V', 'V', 'V', 'V', 'V', 'V', 'V'},
                {'V', 'V', 'V', 'V', 'V', 'V', 'V', 'V'},
                {'V', 'V', 'V', 'V', 'V', 'V', 'V', 'V'},
                {'V', 'V', 'V', 'V', 'V', 'V', 'V', 'V'},
                {'V', 'B', 'V', 'V', 'V', 'V', 'V', 'V'},
                {'V', 'V', 'V', 'V', 'V', 'V', 'V', 'V'},
        };

        board.setGrid(grid);
        assertEquals(true, board.blackHasWon());
    }

    @org.junit.jupiter.api.Test
    void black_does_not_win_if_it_its_checkers_are_not_connected() {
        Board board = new Board();
        char[][] grid = {
                {'V', 'V', 'V', 'V', 'V', 'V', 'V', 'V'},
                {'V', 'V', 'V', 'V', 'V', 'V', 'V', 'V'},
                {'V', 'V', 'V', 'V', 'V', 'V', 'V', 'V'},
                {'V', 'B', 'V', 'V', 'V', 'V', 'V', 'V'},
                {'V', 'V', 'V', 'V', 'B', 'V', 'V', 'V'},
                {'V', 'V', 'V', 'V', 'V', 'V', 'V', 'V'},
                {'V', 'V', 'V', 'V', 'V', 'V', 'V', 'V'},
                {'V', 'V', 'V', 'V', 'V', 'V', 'V', 'V'},
        };

        board.setGrid(grid);
        assertEquals(false, board.whiteHasWon());
    }
}