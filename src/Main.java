import java.io.FileNotFoundException;
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
        }
        catch (FileNotFoundException e) {
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
        maze[0][0] = "*";
        int counter = 0;
        boolean foundEnd = false;
        while (!foundEnd){
            for (int row = 0; row < maze.length && !foundEnd;){
                for (int col = 0;col < maze[0].length && !foundEnd;){
                    counter = 0;
                    // checks if there's an intersection / way down
                    if (row + 1 < maze.length && maze[row+1][col].equals(".")){
                        counter++;
                    }
                    // checks if there's an intersection / way up
                    if (row - 1 >= 0 && maze[row-1][col].equals(".")){
                        counter++;
                    }
                    // checks if there's an intersection / way right
                    if (col + 1 < maze[0].length && maze[row][col+1].equals(".")){
                        counter++;
                    }
                    // checks if there's an intersection / way left
                    if (col - 1 >= 0 && maze[row][col-1].equals(".")){
                        counter++;
                    }

                    // checks if reached the end
                    if (row == maze.length - 1 && col == maze[0].length - 1){
                        foundEnd = true;
                    }

                    // down
                    else if (row + 1 < maze.length && maze[row+1][col].equals(".") && counter == 1){
                        maze[row+1][col] = "*";
                        row = row+1;
                        coords.add("(" + row + ", " + col + ")");
                    }
                    // up
                    else if (row - 1 >= 0 && maze[row-1][col].equals(".") && counter == 1){
                        maze[row-1][col] = "*";
                        row = row-1;
                        coords.add("(" + row + ", " + col + ")");
                    }
                    // right
                    else if (col + 1 < maze[0].length && maze[row][col+1].equals(".") && counter == 1){
                        maze[row][col+1] = "*";
                        col = col+1;
                        coords.add("(" + row + ", " + col + ")");
                    }
                    // left
                    else if (col - 1 >= 0 && maze[row][col-1].equals(".") && counter == 1){
                        maze[row][col-1] = "*";
                        col = col-1;
                        coords.add("(" + row + ", " + col + ")");
                    }
                }
            }
        }
        System.out.println(coords);
    }
}