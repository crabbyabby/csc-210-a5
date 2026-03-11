import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

class SolveMaze {

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

    if (maze.getFinish().getCol() == col && maze.getFinish().getRow() == row) {
      maze.setContents(row, col, MazeContents.PATH);
      return true;
    }

    maze.setContents(row, col, MazeContents.VISITED);
    boolean explored = solve(maze, row -1, col) || solve(maze, row + 1, col) || solve(maze, row, col - 1) || solve(maze, row, col + 1);

    if (explored) {
      maze.setContents(row, col, MazeContents.PATH);
    } else {
      maze.setContents(row, col, MazeContents.DEAD_END);
    }

    return explored;
  }

  
  public static void main(String[] args) {    
    Maze maze;
    if(args.length <= 0){
        maze = readMaze("maze1");
    } else {
        maze = readMaze(args[0]);

    
    maze = new Maze();
    MazeViewer viewer = new MazeViewer(maze);
    maze.initDemoMaze();
  }
}
}
