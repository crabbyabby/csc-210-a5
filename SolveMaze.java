import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Class for solving the maze
 */
class SolveMaze {



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
