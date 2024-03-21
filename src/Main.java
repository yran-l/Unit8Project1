import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
public class Main {

    public static void main(String[] args) {
        MazeSolverMethods MSM = new MazeSolverMethods("data/mazes");
        MSM.solveMaze();
        MSM.printMaze();
        MSM.printCoords();
    }
}