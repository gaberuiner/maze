package ADSLab1;

import javax.swing.*;
import java.awt.*;

public class MazeDrawer extends JFrame {


    private Maze.Point start_point;

    private  Maze.Point end_point;
    private int x_max ;
    private int y_max ;
    private String[][] maze;

    private Integer[][] mazeW;
    public MazeDrawer(Maze maze) {
        this.maze = maze.maze;
        this.x_max = maze.maze.length;
        this.y_max = maze.maze[0].length;
        this.mazeW = maze.mazeW;
        this.start_point = maze.start_point;
        this.end_point = maze.end_point;
        setTitle("Maze Drawer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700); // Установите размер окна JFrame
        setLocationRelativeTo(null);

        MazePanel mazePanel = new MazePanel();
        add(mazePanel);

        setVisible(true);
    }

    private class MazePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int cellSize = 60; // Размер каждой ячейки

            for (int i = 0; i < x_max; i++) {
                for (int j = 0; j < y_max; j++) {
                    if (start_point.x_coordinate == i && start_point.y_coordinate == j){
                        g.setFont(new Font("Arial", Font.BOLD, 20));
                        int x = j * cellSize + cellSize / 5;
                        int y = i * cellSize + cellSize / 2;
                        g.drawString("Start", x, y);
                        g.setColor(Color.GREEN);
                        continue;
                    }
                    if (end_point.x_coordinate == i && end_point.y_coordinate == j){
                        g.setFont(new Font("Arial", Font.BOLD, 20));
                        int x = j * cellSize + cellSize / 5;
                        int y = i * cellSize + cellSize / 2;
                        g.drawString("End", x, y);
                        g.setColor(Color.GREEN);
                        continue;
                    }

                    if (maze[i][j].equals("x")) {

                        g.setColor(Color.BLACK);
                    }else if (maze[i][j].equals("W")) {
                        String step = mazeW[i][j].toString();
                        g.setColor(Color.BLUE);

                        g.setFont(new Font("Arial", Font.BOLD, 20));
                        ;


                        int x = j * cellSize + cellSize / 4;
                        int y = i * cellSize + cellSize / 2;


                        g.drawString(step, x, y);


                        g.setFont(new Font("Arial", Font.PLAIN, 20));


                    } else {
                        g.setColor(Color.WHITE);
                    }

                    g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                    g.setColor(Color.BLACK);
                    g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
                }
            }
        }
    }




}
