import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
public class Main {
    public static String[][] getMaze(String fileName) {
        File f = new File(fileName);
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        }

        ArrayList<String> fileData = new ArrayList<String>();
        while (s.hasNextLine())
            fileData.add(s.nextLine());

        int rows = fileData.size();
        int cols = fileData.get(0).length();

        String[][] maze = new String[rows][cols];

        for (int i = 0; i < fileData.size(); i++) {
            String d = fileData.get(i);
            for (int j = 0; j < d.length(); j++) {
                maze[i][j] = d.charAt(j) + "";
            }
        }
        return maze;
    }

    public static void main(String[] args) {
        String[][] maze = getMaze("data/mazes");
        ArrayList<String> coords = new ArrayList<String>();
        coords.add("(" + 0 + ", " + 0 + ")");
        maze[0][0] = "\u001B[34mV\u001B[0m";

        boolean foundEnd = false;
        while (!foundEnd) {
            for (int row = 0; row < maze.length && !foundEnd; ) {

                for (int col = 0; col < maze[0].length && !foundEnd; ) {
                    // checks if reached the end
                    if (row == maze.length - 1 && col == maze[0].length - 1) {
                        foundEnd = true;
                    }

                    // down
                    else if (row + 1 < maze.length && maze[row + 1][col].equals(".")) {
                        maze[row + 1][col] = "\u001B[34mV\u001B[0m";
                        row = row + 1;
                        coords.add("(" + row + ", " + col + ")");
                    }
                    // up
                    else if (row - 1 >= 0 && maze[row - 1][col].equals(".")) {
                        maze[row - 1][col] = "\u001B[34mV\u001B[0m";
                        row = row - 1;
                        coords.add("(" + row + ", " + col + ")");
                    }
                    // right
                    else if (col + 1 < maze[0].length && maze[row][col + 1].equals(".")) {
                        maze[row][col + 1] = "\u001B[34mV\u001B[0m";
                        col = col + 1;
                        coords.add("(" + row + ", " + col + ")");
                    }
                    // left
                    else if (col - 1 >= 0 && maze[row][col - 1].equals(".")) {
                        maze[row][col - 1] = "\u001B[34mV\u001B[0m";
                        col = col - 1;
                        coords.add("(" + row + ", " + col + ")");
                    } else {
                        maze[row][col] = "\u001B[31mX\u001B[0m";
                        row = 0;
                        col = 0;
                        for (int r = 0; r < maze.length; r++) {
                            for (int c = 0; c < maze[0].length; c++) {
                                if (maze[r][c].equals("\u001B[34mV\u001B[0m")) {
                                    maze[r][c] = ".";
                                }
                            }
                        }
                        maze[0][0] = "\u001B[34mV\u001B[0m";
                    }
                }
            }
        }
        // prints maze
        System.out.println("\n");
        for (String[] temp : maze) {
            System.out.println(Arrays.toString(temp));
        }
        // prints coordinates
        ArrayList<String> newCoords = new ArrayList<String>();
        for (String coord : coords){
            if (maze[Integer.parseInt(coord.substring(coord.indexOf("(") + 1, coord.indexOf(",")))][Integer.parseInt(coord.substring(coord.indexOf(",") + 2, coord.indexOf(")")))].equals("\u001B[34mV\u001B[0m") && !newCoords.contains(coord)){
                newCoords.add(coord);
            }
        }
        for (int i = 0; i < newCoords.size(); i++){
            System.out.print(newCoords.get(i));
            if (i != newCoords.size() - 1){
                System.out.print(" ---> ");

            }
        }
    }
}