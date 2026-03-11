import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Class for solving the maze
 */
class SolveMaze {

  /**
   * Method to read the maze
   * @param fname name of maze file
   * @return Maze with all the new things in it
   */
  public static Maze readMaze(String fname){
    ArrayList<String> lines = new ArrayList<String>();
    Scanner file = null;
    try {
      file = new Scanner(new File(fname));
    } catch (FileNotFoundException e) {
      System.err.println("Cannot locate file.");
      System.exit(-1);  
    }

    while (file.hasNextLine()) {
      lines.add(file.nextLine());
    }

    int height = lines.size();
    int width = lines.get(0).length();
    System.out.println(height);
    System.out.println(width);

    Maze maze = new Maze(width, height);

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        char content = lines.get(row).charAt(col);

        if (content == '#') {
            maze.setContents(row, col, MazeContents.WALL);
        }
        else if (content == 'S') {
            maze.setContents(row, col, MazeContents.OPEN);
            MazeLocation start = new MazeLocation(row, col);
            maze.setStart(start);
        }
        else if (content == 'F') {
            maze.setContents(row, col, MazeContents.OPEN);
            MazeLocation finish = new MazeLocation(row, col);
            maze.setFinish(finish);
        }
        else {
            maze.setContents(row, col, MazeContents.OPEN);
        }
      }
    }

    return maze;
  }

  /**
   * Recursive solver for maze
   * @param maze the maze to solve
   * @param row the row to start at
   * @param col the column to start at
   * @return true if it can be solved, false if it cannot be
   */
  public static boolean solve(Maze maze, int row, int col) {

    if (!maze.isExplorable(row, col)) {
      return false;
    }

    if (maze.getFinish().getCol() == col && maze.getFinish().getRow() == row) {
      maze.setContents(row, col, MazeContents.PATH);
      return true;
    }

    //news
    maze.setContents(row, col, MazeContents.VISITED);
    boolean explored = solve(maze, row, col+1) || solve(maze, row + 1, col) || solve(maze, row -1, col) || solve(maze, row, col - 1);

    if (explored) {
      maze.setContents(row, col, MazeContents.PATH);
    } else {
      maze.setContents(row, col, MazeContents.DEAD_END);
    }

    return explored;
  }

  
  /**
   * Testing the maze function
   * @param args arguments to run the code
   */
  public static void main(String[] args) {    
    Maze maze;
    if (args.length <= 0) {
        maze = readMaze("maze1");
    } else {
        maze = readMaze(args[0]);
    }

    MazeViewer viewer = new MazeViewer(maze);
    MazeLocation start = maze.getStart();
    boolean solved = solve(maze, start.getRow(), start.getCol());

    System.out.println("Solved: " + solved);
}
}
