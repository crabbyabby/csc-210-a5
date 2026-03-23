import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class for a Displayable Maze
 * @author Abigail Lei
 */
public class Maze implements DisplayableMaze{

    // Attributes
    private MazeContents[][] mazeGrid;
    private int width;
    private int height;
    private MazeLocation start;
    private MazeLocation finish;

    /**
     * Empty constructor, automatically sets 
     * things to 10 width and 10 height
     */
    public Maze() {
        this.width = 10;
        this.height = 10;
        this.mazeGrid = new MazeContents[this.height][this.width];
    }

    /**
     * Constructor that just takes in height and width
     * Creates maze grid with those heights
     * @param width the width of the maze
     * @param height the height of the maze
     */
    public Maze(int width, int height) {
        this.width = width;
        this.height = height;
        this.mazeGrid = new MazeContents[this.height][this.width];
    }

    /**
     * Constructor that takes in height and weight
     * and start and finish
     * @param width the width of the maze
     * @param height the height of the maze
     * @param start location where it starts
     * @param finish location where it ends
     */
    public Maze(int width, int height, MazeLocation start, MazeLocation finish) {
        this.width = width;
        this.height = height;
        this.start = start;
        this.finish = finish;
        this.mazeGrid = new MazeContents[this.height][this.width];
    }

    /**
     * Constructor for that autograderrrr
     * @param fname the name of the maze file
     */
    public Maze(String fname) {
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

        this.width = width;
        this.height = height;
        this.mazeGrid = new MazeContents[height][width];

        for (int row = 0; row < height; row++) {
        for (int col = 0; col < width; col++) {
            char content = lines.get(row).charAt(col);

            if (content == '#') {
                this.setContents(row, col, MazeContents.WALL);
            }
            else if (content == 'S') {
                this.setContents(row, col, MazeContents.OPEN);
                MazeLocation start = new MazeLocation(row, col);
                this.setStart(start);
            }
            else if (content == 'F') {
                this.setContents(row, col, MazeContents.OPEN);
                MazeLocation finish = new MazeLocation(row, col);
                this.setFinish(finish);
            }
            else {
                this.setContents(row, col, MazeContents.OPEN);
            }
        }
        }
    }

    /** 
     * Getter for height
     * @return height of maze grid */
    public int getHeight() {
        return this.height;
    }

    /** 
     * Getter for width
     * @return width of maze grid */
    public int getWidth(){
        return this.width;
    }

    /** 
     * @param i -> row of content
     * @param j column of content
     * @return contents of maze grid at row i, column j */
    public MazeContents getContents(int i, int j) {
        if (i >= this.height || i < 0 || j >= this.width || j < 0) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        return mazeGrid[i][j];
    }

    /** @return true if the maze grid is explorable at row i, column j 
     * - in bounds,
     * - not a wall, and
     * - not already visited.
     */
    public boolean isExplorable(int i, int j) {
        if (i < 0 || i >= height || j < 0 || j >= width) {
            return false;
        }

        MazeContents contents = mazeGrid[i][j];
        if (contents==MazeContents.WALL || contents ==MazeContents.VISITED || contents == MazeContents.DEAD_END) {
            return false;
        }
        return true;

    }

    /** @return location of maze start point */
    public MazeLocation getStart() {
        return this.start;
    }

    /** @return location of maze finish point */
    public MazeLocation getFinish() {
        return this.finish;
    }

    /**
     * Setter to set a new finish
     * @param finish location of finish
     */
    public void setFinish(MazeLocation finish) {
        this.finish = finish;
    }

    /**
     * Setter / helper to set a new start
     * @param start where maze starts
     */
    public void setStart(MazeLocation start) {
        this.start = start;
    }

    /**
     * Setter which puts new material in maze
     * @param row row to add new stuff
     * @param col column to add new stuff
     * @param content new stuff to add
     * @throws IndexOutOfBoundsException if it is out of bounds
     */
    public void setContents(int row, int col, MazeContents content) {
        if (row >= this.height || row < 0 || col >= this.width || col < 0) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        mazeGrid[row][col] = content;
    }


    /** This DemoMaze method will allow you to generate a simple maze
     * to test your code on as you develop it. Ultimately, you need
     * to accept maze files as command line inputs or standard input.
     * You will need to implement the DisplayableMaze interface before you
     * can run the initDemoMaze method.
     * * @author Tianah Gooden
     * * @version October 17th 2023
     */
    public void initDemoMaze() { //String fileName, 
        this.height = 10;
        this.width = 8;
        this.mazeGrid = new MazeContents[height][width];
        this.start = new MazeLocation(1,1);
        this.finish = new MazeLocation(8,6);

        this.mazeGrid[0][0] = MazeContents.WALL; this.mazeGrid[0][1] = MazeContents.WALL; this.mazeGrid[0][2] = MazeContents.WALL; this.mazeGrid[0][3] = MazeContents.WALL; this.mazeGrid[0][4] = MazeContents.WALL; this.mazeGrid[0][5] = MazeContents.WALL; this.mazeGrid[0][6] = MazeContents.WALL; this.mazeGrid[0][7] = MazeContents.WALL;
        this.mazeGrid[1][0] = MazeContents.WALL; this.mazeGrid[1][1] = MazeContents.OPEN; this.mazeGrid[1][2] = MazeContents.OPEN; this.mazeGrid[1][3] = MazeContents.OPEN; this.mazeGrid[1][4] = MazeContents.OPEN; this.mazeGrid[1][5] = MazeContents.OPEN; this.mazeGrid[1][6] = MazeContents.WALL; this.mazeGrid[1][7] = MazeContents.WALL;
        this.mazeGrid[2][0] = MazeContents.WALL; this.mazeGrid[2][1] = MazeContents.WALL; this.mazeGrid[2][2] = MazeContents.OPEN; this.mazeGrid[2][3] = MazeContents.WALL; this.mazeGrid[2][4] = MazeContents.WALL; this.mazeGrid[2][5] = MazeContents.OPEN; this.mazeGrid[2][6] = MazeContents.WALL; this.mazeGrid[2][7] = MazeContents.WALL;
        this.mazeGrid[3][0] = MazeContents.WALL; this.mazeGrid[3][1] = MazeContents.OPEN; this.mazeGrid[3][2] = MazeContents.WALL; this.mazeGrid[3][3] = MazeContents.OPEN; this.mazeGrid[3][4] = MazeContents.OPEN; this.mazeGrid[3][5] = MazeContents.OPEN; this.mazeGrid[3][6] = MazeContents.WALL; this.mazeGrid[3][7] = MazeContents.WALL;
        this.mazeGrid[4][0] = MazeContents.WALL; this.mazeGrid[4][1] = MazeContents.OPEN; this.mazeGrid[4][2] = MazeContents.OPEN; this.mazeGrid[4][3] = MazeContents.OPEN; this.mazeGrid[4][4] = MazeContents.WALL; this.mazeGrid[4][5] = MazeContents.WALL; this.mazeGrid[4][6] = MazeContents.OPEN; this.mazeGrid[4][7] = MazeContents.WALL;
        this.mazeGrid[5][0] = MazeContents.WALL; this.mazeGrid[5][1] = MazeContents.OPEN; this.mazeGrid[5][2] = MazeContents.WALL; this.mazeGrid[5][3] = MazeContents.OPEN; this.mazeGrid[5][4] = MazeContents.OPEN; this.mazeGrid[5][5] = MazeContents.WALL; this.mazeGrid[5][6] = MazeContents.WALL; this.mazeGrid[5][7] = MazeContents.WALL;
        this.mazeGrid[6][0] = MazeContents.WALL; this.mazeGrid[6][1] = MazeContents.OPEN; this.mazeGrid[6][2] = MazeContents.WALL; this.mazeGrid[6][3] = MazeContents.WALL; this.mazeGrid[6][4] = MazeContents.OPEN; this.mazeGrid[6][5] = MazeContents.OPEN; this.mazeGrid[6][6] = MazeContents.OPEN; this.mazeGrid[6][7] = MazeContents.WALL;
        this.mazeGrid[7][0] = MazeContents.WALL; this.mazeGrid[7][1] = MazeContents.OPEN; this.mazeGrid[7][2] = MazeContents.WALL; this.mazeGrid[7][3] = MazeContents.OPEN; this.mazeGrid[7][4] = MazeContents.OPEN; this.mazeGrid[7][5] = MazeContents.WALL; this.mazeGrid[7][6] = MazeContents.OPEN; this.mazeGrid[7][7] = MazeContents.WALL;
        this.mazeGrid[8][0] = MazeContents.WALL; this.mazeGrid[8][1] = MazeContents.OPEN; this.mazeGrid[8][2] = MazeContents.OPEN; this.mazeGrid[8][3] = MazeContents.WALL; this.mazeGrid[8][4] = MazeContents.OPEN; this.mazeGrid[8][5] = MazeContents.WALL; this.mazeGrid[8][6] = MazeContents.OPEN; this.mazeGrid[8][7] = MazeContents.WALL;
        this.mazeGrid[9][0] = MazeContents.WALL; this.mazeGrid[9][1] = MazeContents.WALL; this.mazeGrid[9][2] = MazeContents.WALL; this.mazeGrid[9][3] = MazeContents.WALL; this.mazeGrid[9][4] = MazeContents.WALL; this.mazeGrid[9][5] = MazeContents.WALL; this.mazeGrid[9][6] = MazeContents.WALL; this.mazeGrid[9][7] = MazeContents.WALL;
  }

}
