package ADSLab1;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        System.out.println("Starting");
        Scanner in = new Scanner(System.in);
        System.out.println("Write x coordinate max size:");
        Integer x_len = in.nextInt();
        System.out.println("Write y coordinate max size:");
        Integer y_len = in.nextInt();
        in.nextLine();
        String[][] maze2 = new String[x_len][y_len];
        for (int i = 0; i < x_len; i++) {
            String row = in.nextLine();
            String[] rowX = row.split("");
            for (int j = 0; j < rowX.length; j++) {
                if (rowX[j].equals(" ")){
                    rowX[j] = ".";
                }
            }
            if (rowX.length != y_len){
                System.out.println("Incorrect Input");
                return;
            }
            maze2[i] = rowX;


        }
        //String[][] maze1 = new String[][]{{"x", "x", "x", "x", "x"}, {"x","S", ".", ".", "x"}, {"x", "x", ".", "x", "x"}, {"x", ".", ".", ".", "Z"}, {"x", "x", "x", "x", "x"}};
        Maze maze = new Maze(maze2);
        maze.ExploreMaze(maze.start_point);

        maze.ShowMaze();
        System.out.println("Minimal steps: " + maze.getMinWayOut());
        SwingUtilities.invokeLater(() -> new MazeDrawer(maze));
        String filePath = "output.txt";
        String[][] array = maze.maze;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    writer.write(array[i][j]);
                    if (j < array[i].length - 1) {
                        writer.write(" ");
                    }
                }
                writer.newLine();

            }

            writer.write("Minimal steps: " + maze.getMinWayOut());
            writer.newLine();
            writer.write("x - Стены");
            writer.newLine();
            writer.write("S - точка старта");
            writer.newLine();
            writer.write("W - путь до выхода");
            writer.newLine();
            writer.write("Z - выход");
            writer.newLine();
            writer.write("Если минимальное количество шагов -1 тогда лабиринт не имееет пути выхода");
            System.out.println("Данные успешно записаны в файл: " + filePath);
            return;
        } catch (IOException e) {
            System.err.println("Ошибка при записи данных в файл: " + e.getMessage());
        }


    }
}
