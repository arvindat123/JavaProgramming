package org.Leetcode.matrix;

public class IslandPerimeter {
    public int islandPerimeter(int[][] grid) {
        int perimeter = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    perimeter += 4;
                    if (r > 0 && grid[r-1][c] == 1) {
                        perimeter -= 2;
                    }
                    if (c > 0 && grid[r][c-1] == 1) {
                        perimeter -= 2;
                    }
                }
            }
        }

        return perimeter;
    }

    public static void main(String[] args) {
        IslandPerimeter obj = new IslandPerimeter();
        int[][] grid = {{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}};
        System.out.println(obj.islandPerimeter(grid));
    }
}
