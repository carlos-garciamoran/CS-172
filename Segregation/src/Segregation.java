public class Segregation {
    public static void main(String[] args) {
        int SIZE = 6;
        double T = 0.5;
        double COLOR = 0.5;
        double EMPTY = 0.2;
        int[][] grid = new int[SIZE][SIZE];

        initGrid(grid, COLOR, EMPTY);
        StdDraw.enableDoubleBuffering();  // avoid flickering
        drawGrid(grid);
        organiseGrid(grid, T);
    }

    public static void initGrid(int [][] grid, double COLOR, double EMPTY){
        for (int x = 0; x < grid.length; ++x) {
            for (int y = 0; y < grid.length; ++y) {
                int color;
                double r = StdRandom.uniform();

                if (r <= EMPTY)       color = 0;  // white
                else if (r <= COLOR)  color = 1;  // blue
                else                  color = 2;  // red

                grid[x][y] = color;
            }
        }
    }

    // Draw the grid with colors according to its value.
    public static void drawGrid(int[][] grid) {
        StdDraw.clear();
        StdDraw.setScale(-0.5, grid.length - 0.5);
        StdDraw.square(0.5, 0.5, 0.8);

        for (int x = 0; x < grid.length; ++x) {
            for (int y = 0; y < grid.length; ++y) {
                if (grid[x][y] == 0)
                    StdDraw.setPenColor(StdDraw.WHITE);
                else if (grid[x][y] == 1)
                    StdDraw.setPenColor(StdDraw.BLUE);
                else
                    StdDraw.setPenColor(StdDraw.RED);

                StdDraw.filledSquare(x, y, 0.5);
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.square(x, y, 0.5);
            }
        }

        StdDraw.show();  // needed due to using double buffering
    }


    public static void organiseGrid(int[][] grid, double T) {
        for (int x = 0; x < grid.length; ++x) {
            for (int y = 0; y < grid.length; ++y) {
                int same = 0, count = 0;
                int agent = grid[x][y];

                if (agent == 0)     continue;

                int[] neighbors = getNeighbors(grid, x, y);

                for (int n: neighbors) {
                    if (n != 0)         count++;
                    if (n == agent)     same++;
                }

                if (count != 0 && (double) (same / count) < T) {
                    // Agent is dissatisfied, change location.
                    moveToVacantLocation(grid, x, y, agent);
                    drawGrid(grid);
                }
            }
        }
    }

    // Iterate through the grid to find the neighbors of the given set of coordinates.
    public static int[] getNeighbors(int[][] grid, int x, int y) {
        int i = 0;
        int[] neighbors = new int[8];

        for (int x1 = x - 1; x1 <= x + 1; ++x1) {
            for (int y1 = y - 1; y1 <= y + 1; ++y1) {
                if (x1 >= 0 && x1 < grid.length &&
                        y1 >= 0 && y1 < grid.length &&
                        (x1 != x || y1 != y)) {
                    neighbors[i] = grid[x1][y1];
                    i++;
                }
            }
        }

        return neighbors;
    }

    // Search for a vacant space in the grid and move the given agent there.
    public static void moveToVacantLocation(int[][] grid, int x, int y, int agent) {
        boolean vacant = false;
        while (! vacant) {
            int x2 = StdRandom.uniform(grid.length);
            int y2 = StdRandom.uniform(grid.length);
            if (grid[x2][y2] == 0) {
                grid[x][y] = grid[x2][y2];
                grid[x2][y2] = agent;
                vacant = true;
            }
        }
    }
}
