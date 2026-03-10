import java.io.*;
import java.util.Scanner;

class SolveMaze {

  public static Scanner readMaze(String fname){
    Scanner file = null;
    try {
      file = new Scanner(new File(fname));
    } catch (FileNotFoundException e) {
      System.err.println("Cannot locate file.");
      System.exit(-1);  
    }
    return file;
  }


/**
1. Success case:
   - If current location is finish, mark it `MazeContents.PATH` and return `true`.
2. Failure case:
   - If current location is not explorable, return `false`.

When neither base case applies:

1. Mark current location as `MazeContents.VISITED`.
2. Recursively explore neighbors.
3. Combine results with logical OR (`||`).
4. Before returning:
   - mark current location `PATH` if any recursive call succeeds,
   - otherwise mark it `DEAD_END`.
 */


  public static boolean solve(Maze maze, int row, int col) {

    if (!maze.isExplorable(row, col)) {
      return false;
    }

    if (maze.getContents(row, col).equals(maze.getFinish())) {
      maze.setContents(row, col, MazeContents.PATH);
      return true;
    }

    maze.setContents(row, col, MazeContents.VISITED);
    boolean explored = solve(maze, row -1, col) || solve(maze, row + 1, col) || solve(maze, row, col - 1) || solve(maze, row, col + 1);

    return false;
  }

  
  public static void main(String[] args) {
    Scanner file = null;
    if(args.length <= 0){
      file = readMaze("maze1");
    }
    else{
      file = readMaze(args[0]);
    }
    

    System.out.println(file.nextLine());
    
    // Maze maze = new Maze();
    // MazeViewer viewer = new MazeViewer(maze);
    // maze.initDemoMaze();
  }
}
