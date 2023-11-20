package eazyMaze;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class main {
    public static void main(String[] args) {
        String[][] maze = new String[][]{{"x", "x", "x", "x", "x"}, {"x","S", ".", ".", "x"}, {"x", "x", ".", "x", "x"}, {"x", ".", ".", ".", "Z"}, {"x", "x", "x", "x", "x"}};
        Maze maze1 = new Maze(maze);
        maze1.mazeSolver(maze1.start_point);
        maze1.showMazeA();
        maze1.findRightWay();
        maze1.showMaze();
        MazeDrawer mazeDrawer = new MazeDrawer(maze1);
        mazeDrawer.setVisible(true);
        writeMazeToFile(maze1.maze, "output.txt");

    }

    public static void writeMazeToFile(String[][] maze, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < maze.length; i++) {
                for (int j = 0; j < maze[i].length; j++) {
                    writer.write(maze[i][j] + " ");
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
