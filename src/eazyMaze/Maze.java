package eazyMaze;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Objects;
import java.util.OptionalInt;

public class Maze {

    Integer x_coordinate;
    Integer y_coordinate;

    Point start_point;
    Point end_point;
    String[][] maze;

    Integer[][] mazeA;
    public class Point{
        public Integer x_coord;
        public Integer y_coord;
        public Point(){};
        public Point(int x_coord, int y_coord){
            this.x_coord =x_coord;
            this.y_coord = y_coord;
        };

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return Objects.equals(x_coord, point.x_coord) && Objects.equals(y_coord, point.y_coord);
        }

        @Override
        public int hashCode() {
            return Objects.hash(x_coord, y_coord);
        }
    }

    public Point findChar(String letter){
        for (int i = 0; i < x_coordinate; i++) {
            for (int j = 0; j < y_coordinate; j++) {
                if (maze[i][j] == letter){
                    Point point = new Point(i, j);
                    return point;
                }
            }
        }
        return null;
    }

    public Integer findMin(Point point){
        Point point1 = new Point(point.x_coord, point.y_coord+1);
        Point point2 = new Point(point.x_coord+1, point.y_coord);
        Point point3 = new Point(point.x_coord, point.y_coord-1);
        Point point4 = new Point(point.x_coord-1, point.y_coord);
        Point[] points = new Point[]{point1, point2, point3, point4};

        ArrayList<Integer> mins = new ArrayList<>();
        for (Point point5 : points){
            if (checkPoint(point5) == null){
                continue;
            }else {
                mins.add(checkPoint(point5));
            }

        }
        if (mins.isEmpty()){
            return null;
        }
        Integer min = mins.get(0);
        for (int minq : mins){
            if (minq < min){
                min = minq;
            }

        }
        return min;

    }

    public Integer checkPoint(Point point){

        if (point.x_coord < 0 || point.y_coord < 0 || point.x_coord >= x_coordinate || point.y_coord >= y_coordinate){
            return null;
        }
        if (mazeA[point.x_coord][point.y_coord] == null){
            return null;
        }
        if (maze[point.x_coord][point.y_coord].equals("x") ){
            return null;
        }

        return mazeA[point.x_coord][point.y_coord];

    }

    public void mazeSolver(Point point){
        if (point == start_point){
            setPoint(point, 0);
        }
        Integer min = findMin(point);
        if (min != null){
            setPoint(point, min+1);
        }
        Point point1 = new Point(point.x_coord, point.y_coord+1);
        Point point2 = new Point(point.x_coord+1, point.y_coord);
        Point point3 = new Point(point.x_coord, point.y_coord-1);
        Point point4 = new Point(point.x_coord-1, point.y_coord);
        Point[] points = new Point[]{point1, point2, point3, point4};
        for (Point point5 : points){
            if (checkPointIsAvailable(point5)){
                mazeSolver(point5);
            }
        }



    }

    public boolean checkPointIsAvailable(Point p){

        if (p.x_coord < 0 || p.y_coord < 0 || p.x_coord >= x_coordinate || p.y_coord >= y_coordinate){
            return false;
        }
        if (mazeA[p.x_coord][p.y_coord] != null){
            return false;
        }
        if (maze[p.x_coord][p.y_coord].equals("x")){
            return false;
        }

        return true;
    }
    public void setPoint(Point point, int num){
        mazeA[point.x_coord][point.y_coord] = num;
    }




    public Maze(){};
    public Maze(String[][] maze){
        this.x_coordinate = maze.length;
        this.y_coordinate = maze[0].length;
        this.maze = maze;
        this.start_point = findChar("S");
        this.end_point = findChar("Z");

        this.mazeA = new Integer[x_coordinate][y_coordinate];
    };
    public void showMaze(){

        for (int i = 0; i < x_coordinate; i++) {
            for (int j = 0; j < y_coordinate; j++) {

                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void showMazeA(){

        for (int i = 0; i < x_coordinate; i++) {
            for (int j = 0; j < y_coordinate; j++) {
                if (mazeA[i][j] == null){
                    System.out.print("#" + " ");
                    continue;
                }
                System.out.print(mazeA[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void findRightWay(){
        Point point = findPrev(end_point);
        while (mazeA[point.x_coord][point.y_coord] != 1){
            point = findPrev(point);
        }


    }

    public Point findPrev(Point point){
        Point point1 = new Point(point.x_coord, point.y_coord+1);
        Point point2 = new Point(point.x_coord+1, point.y_coord);
        Point point3 = new Point(point.x_coord, point.y_coord-1);
        Point point4 = new Point(point.x_coord-1, point.y_coord);
        Point[] points = new Point[]{point1, point2, point3, point4};
        Integer num = mazeA[point.x_coord][point.y_coord] - 1;
        for (Point point5 : points){
            if (point5.x_coord < 0 || point5.y_coord < 0 || point5.x_coord >= x_coordinate || point5.y_coord >= y_coordinate){
                continue;
            }
            if (mazeA[point5.x_coord][point5.y_coord] == null){
                continue;
            }
            if (mazeA[point5.x_coord][point5.y_coord].equals(num)){
                maze[point5.x_coord][point5.y_coord] = "W";
                return point5;
            }
        }
        return null;


    }

}
