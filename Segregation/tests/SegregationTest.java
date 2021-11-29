import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SegregationTest {
    @Test
    void initGrid() {
        int count = 0;
        int[][] grid = new int[5][5];
        Segregation.initGrid(grid, 0.9, 0.1);

        for (int x = 0; x < grid.length; ++x) {
            for (int y = 0; y < grid.length; ++y) {
                if (grid[x][y] >= 0 && grid[x][y]<= 2) ++count;
            }
        }

        assertEquals(25, count);
    }

    @Test
    void organiseGrid() {
        int[][] grid = {
                {1, 2, 0, 0}, {2, 2, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}
        };

        Segregation.organiseGrid(grid, 0.3);
        assertEquals(0, grid[0][0]);
    }
}