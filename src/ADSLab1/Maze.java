package ADSLab1;

import com.sun.source.tree.IfTree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Maze {

    public String[][] maze;

    public Integer[][] mazeW;
    public Point start_point;

    public Point end_point;

    public Integer x_max;

    public Integer y_max;

    public Boolean isPossible = true;

    public class Point{

        public Point(int x, int y){
            x_coordinate = x;
            y_coordinate = y;
        }
        public Integer x_coordinate;
        public Integer y_coordinate;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return Objects.equals(x_coordinate, point.x_coordinate) && Objects.equals(y_coordinate, point.y_coordinate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(x_coordinate, y_coordinate);
        }
    }



    public Point findChar(String let){
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j].equals(let)){

                    Point point = new Point(i, j);
                    return point;
                }
            }

        }
        return null;
    }

    public void ExploreMaze(Point point){
        if (!canBeExplored(point) || !isNotExplored(point)){
            return;
        }
        mazeW[point.x_coordinate][point.y_coordinate] = findMinNeihboor(point);
        Point point1 = new Point(point.x_coordinate, point.y_coordinate+1);
        Point point2 = new Point(point.x_coordinate+1, point.y_coordinate);
        Point point3 = new Point(point.x_coordinate, point.y_coordinate-1);
        Point point4 = new Point(point.x_coordinate-1, point.y_coordinate);
        Point[] points = new Point[4];
        points[0] = point1;
        points[1] = point2;
        points[2] = point3;
        points[3] = point4;

        for (Point poin : points) {
            if (!checkCoordinates(poin) || !canBeExplored(poin)) {
                continue;
            }
            ExploreMaze(poin);
        }


    }

    public void drawTheWay(){
        if (getMinWayOut() == -1){
            isPossible = false;
            return;
        }

        ArrayList<Point> points = new ArrayList<>();

        Point newPoint = end_point;

        while (!newPoint.equals(start_point)){
            newPoint = findNeihgborWithValue(newPoint);
            Point newPoint2 = newPoint;

            points.add(newPoint2);
        }
        points.remove(points.size()-1);


        for (int i = 0; i < mazeW.length; i++) {
            for (int j = 0; j < mazeW[i].length; j++) {
                Point point = new Point(i, j);
                if (checkForPoint(points, point)){
                    maze[i][j] = "W";
                }

            }


        }



    }

    public boolean checkForPoint(ArrayList<Point> points, Point point){
        for (Point point1 : points){
            if (point.equals(point1)){
                return true;
            }
        }
        return false;
    }

    public Point findNeihgborWithValue(Point point){
        Point point1 = new Point(point.x_coordinate, point.y_coordinate+1);
        Point point2 = new Point(point.x_coordinate+1, point.y_coordinate);
        Point point3 = new Point(point.x_coordinate, point.y_coordinate-1);
        Point point4 = new Point(point.x_coordinate-1, point.y_coordinate);
        Point[] points = new Point[4];
        points[0] = point1;
        points[1] = point2;
        points[2] = point3;
        points[3] = point4;

        for (Point poin : points) {

            if (!checkCoordinates(poin) || !canBeExplored(poin)) {
                continue;
            }
            if (mazeW[poin.x_coordinate][poin.y_coordinate] == null){
                continue;
            }

            if (mazeW[poin.x_coordinate][poin.y_coordinate].equals(mazeW[point.x_coordinate][point.y_coordinate]-1)){

                return poin;
            }

        }
        return null;
    }
    public boolean canBeExplored(Point point){
        if (getChar(point).equals("x")){
            return false;
        }
        return true;
    }

    public Maze(String[][] maze){
        this.maze = maze;
        this.start_point = findChar("S");
        this.end_point = findChar("Z");
        this.mazeW = new Integer[maze.length][maze[0].length];
        this.x_max = maze.length;
        this.y_max = maze[0].length;

        if (start_point == null || end_point == null){
            System.out.println("Input is wrong");
        }
    }

    public boolean isNotExplored(Point point){
        if (mazeW[point.x_coordinate][point.y_coordinate] == null){
            return true;
        }
        return false;
    }

    public Integer getMinWayOut(){
        if (mazeW[end_point.x_coordinate][end_point.y_coordinate] == null){
            System.out.println("Maze can not be solved");
            return -1;
        }
        return mazeW[end_point.x_coordinate][end_point.y_coordinate];
    }

    public void ShowMazeM(){
        for (int i = 0; i < mazeW.length; i++) {
            for (int j = 0; j < mazeW[i].length; j++) {
                if (mazeW[i][j] == null){
                    System.out.print("x" + " ");
                    continue;
                }
                System.out.print(mazeW[i][j] + " ");

            }
            System.out.println();

        }
    }

    public void ShowMaze(){

        drawTheWay();
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {

                System.out.print(maze[i][j] + " ");

            }
            System.out.println();

        }
    }

    public boolean checkCoordinates(Point point){
        if (point.x_coordinate < 0 || point.y_coordinate < 0 || point.x_coordinate >= x_max || point.y_coordinate >= y_max){
            return false;
        }
        return true;
    }

    public String getChar(Point point){
        return maze[point.x_coordinate][point.y_coordinate];
    }

    public Integer findMinNeihboor(Point point){

        if (point.equals(start_point)){
            return 0;
        }
        ArrayList<Integer> vals = new ArrayList<>();
        Point point1 = new Point(point.x_coordinate, point.y_coordinate+1);
        Point point2 = new Point(point.x_coordinate+1, point.y_coordinate);
        Point point3 = new Point(point.x_coordinate, point.y_coordinate-1);
        Point point4 = new Point(point.x_coordinate-1, point.y_coordinate);
        Point[] points = new Point[4];
        points[0] = point1;
        points[1] = point2;
        points[2] = point3;
        points[3] = point4;

        for (Point poin : points){
            if (!checkCoordinates(poin) || !canBeExplored(point) ){
                continue;
            }
            try {
                if (mazeW[poin.x_coordinate][poin.y_coordinate] == null){
                    continue;
                }
            } catch (NullPointerException e){

            }
            vals.add(mazeW[poin.x_coordinate][poin.y_coordinate]);


        }
        if (vals.isEmpty()){
            return -1;
        }
        Integer minV = vals.get(0);

        for (int val : vals){
            if (minV > val){
                minV = val;
            }
        }
        return minV+1;



    }



}
