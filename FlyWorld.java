import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class FlyWorld {
    protected int numRows;
    protected int numCols;

    protected GridLocation[][] world;

    protected GridLocation start;
    protected GridLocation goal;

    protected Fly mosca;

    protected Frog[] frogs;
    protected Spider[] spiders;

    /**
     * Reads a file containing information about the grid setup.
     * Initializes the grid and other instance variables for use by
     * FlyWorldGUI and other pieces of code.
     *
     * @param fileName the file containing the world grid information
     */
    public FlyWorld(String fileName) {
        File inputFile = new File(fileName);
        Scanner input = null;
        try {
            input = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }

        String firstRow = input.nextLine();
        String[] firstLine = firstRow.split(" ");

        numRows = Integer.parseInt(firstLine[0]);
        numCols = Integer.parseInt(firstLine[1]);

        world = new GridLocation[numRows][numCols];

        ArrayList<Frog> frogList = new ArrayList<>();
        ArrayList<Spider> spiderList = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            String line = input.nextLine();
            for (int j = 0; j < numCols; j++) {
                world[i][j] = new GridLocation(i, j);
                if (line.charAt(j) == 's') {
                    world[i][j].setBackground(Color.GREEN);
                    start = world[i][j];
                    mosca = new Fly(world[i][j], this);
                } else if (line.charAt(j) == 'h') {
                    world[i][j].setBackground(Color.RED);
                    goal = world[i][j];
                } else if (line.charAt(j) == 'f') {
                    Frog f = new Frog(world[i][j], this);
                    frogList.add(f);
                } else if (line.charAt(j) == 'a') {
                    Spider p = new Spider(world[i][j], this);
                    spiderList.add(p);
                }
            }
        }

        frogs = frogList.toArray(new Frog[0]);
        spiders = spiderList.toArray(new Spider[0]);

        System.out.println("numRows: " + this.numRows + "   numCols: " + this.numCols);
        System.out.println("start: " + this.start + "   goal: " + this.goal);
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public boolean isValidLoc(int r, int c) {
        if (r >= 0 && c >= 0 && r < numRows && c < numCols) {
            return true;
        }
        return false;
    }

    public GridLocation getLocation(int r, int c) {
        return world[r][c];
    }

    public GridLocation getFlyLocation() {
        return mosca.getLocation();
    }

    public int moveFly(int direction) {
        mosca.update(direction);
        if (goal.equals(mosca.location)) {
            return FlyWorldGUI.ATHOME;
        } else if (movePredators()) {
            return FlyWorldGUI.EATEN;
        }
        return FlyWorldGUI.NOACTION;
    }

    public boolean movePredators() {
        for (Frog f : frogs) {
            f.update();
            if (f.eatsFly()) {
                return true;
            }
        }

        for (Spider a : spiders) {
            a.update();
            if (a.eatsFly()) {
                return true;
            }
        }
        return false;
    }
}
