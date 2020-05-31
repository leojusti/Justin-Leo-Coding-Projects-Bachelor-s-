import javafx.geometry.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Maze {

    private static final byte OPEN = 0;
    private static final byte WALL = 1;
    private static final byte VISITED = 2;

    private int rows, columns;
    private byte[][] grid;

    // A constructor that makes a maze of the given size
    public Maze(int r, int c) {
        rows = r;
        columns = c;
        grid = new byte[r][c];
        for (r = 0; r < rows; r++) {
            for (c = 0; c < columns; c++) {
                grid[r][c] = WALL;
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    // Return true if a wall is at the given location, otherwise false
    public boolean wallAt(int r, int c) {
        return grid[r][c] == WALL;
    }

    // Return true if this location has been visited, otherwise false
    public boolean visitedAt(int r, int c) {
        return grid[r][c] == VISITED;
    }

    // Put a visit marker at the given location
    public void placeVisitAt(int r, int c) {
        grid[r][c] = VISITED;
    }

    // Remove a visit marker from the given location
    public void removeVisitAt(int r, int c) {
        grid[r][c] = OPEN;
    }

    // Put a wall at the given location
    public void placeWallAt(int r, int c) {
        grid[r][c] = WALL;
    }

    // Remove a wall from the given location
    public void removeWallAt(int r, int c) {
        grid[r][c] = 0;
    }

    // Carve out a maze
    public void carve() {
        int startRow = (int) (Math.random() * (rows - 2)) + 1;
        int startCol = (int) (Math.random() * (columns - 2)) + 1;
        carve(startRow, startCol);
    }

    // Directly recursive method to carve out the maze
    public void carve(int r, int c) {
        // Write your code here
//        System.out.println("coming here");
//        System.out.println("r: " + r);
//        System.out.println("c: " + c);
//        System.out.println("Rows: " + rows);
//        System.out.println("Columns: " + columns);
        if (r == 0 || r == rows - 1 || c == 0 || c == columns - 1) {
//            System.out.println("along the border");
        } else if (grid[r][c] == OPEN) {
            //we already removed the wall here, so there is nothing more to do
        } else {
//            System.out.println("coming to else block");

            int wallCount = 0;
            if (wallAt(r - 1, c + 0)) {
                wallCount++;
            }
            if (wallAt(r + 1, c + 0)) {
                wallCount++;
            }
            if (wallAt(r + 0, c - 1)) {
                wallCount++;
            }
            if (wallAt(r + 0, c + 1)) {
                wallCount++;
            }

//            System.out.println("Walcount: " + wallCount);
            ArrayList<Integer> rowOffsets = new ArrayList<>(Arrays.asList(-1, 1, 0, 0));
            ArrayList<Integer> colOffsets = new ArrayList<>(Arrays.asList(0, 0, -1, 1));

            ArrayList<Integer> indices = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
            Collections.shuffle(indices);

            if (wallCount >= 3) {

                removeWallAt(r, c);

//                System.out.println("has wall on atleast three sides");
                for (Integer index : indices) {

                    int rowIndexValue = rowOffsets.get(index);
                    int colIndexValue = colOffsets.get(index);

//                    System.out.println("row Index: " + rowIndexValue);
//                    System.out.println(" col index val: " + colIndexValue);
                    carve(r + rowIndexValue, c + colIndexValue);
                }
            }

        }
    }

    // Determine the longest path in the maze from the given start location
    public ArrayList<Point2D> longestPath() {

        ArrayList<Point2D> longestPath = new ArrayList<>();
        ArrayList<Point2D> tempPath = new ArrayList<>();

        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < columns - 1; j++) {
                tempPath = longestPathFrom(i, j);

                if (tempPath != null && !tempPath.isEmpty() && tempPath.size() > longestPath.size()) {
                    longestPath = tempPath;
                }
            }
        }

        return longestPath;

        //        return longestPathFrom(1, 1); // Replace this with your code
    }

    // Determine the longest path in the maze from the given start location
    public ArrayList<Point2D> longestPathFrom(int r, int c) {
        ArrayList<Point2D> path = new ArrayList<Point2D>();
        ArrayList<Point2D> tempPath = new ArrayList<Point2D>();

        //if there is no wall at r, c
        if (!wallAt(r, c) && grid[r][c] != VISITED) {

            path.add(new Point2D(r, c));
            grid[r][c] = VISITED;

            tempPath = longestPathFrom(r + 1, c);
            path = tempPath;

            tempPath = longestPathFrom(r, c + 1);
            if(tempPath.size() > path.size())
                path = tempPath;

            tempPath = longestPathFrom(r - 1, c);
            if(tempPath.size() > path.size())
                path = tempPath;

            tempPath = longestPathFrom(r, c - 1);
            if(tempPath.size() > path.size())
                path = tempPath;

        }

        //no red path is shown if there is a wall at 1,1
        return path;
    }
}