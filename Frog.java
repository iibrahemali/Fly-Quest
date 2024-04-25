import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;



/**
 * Handles display, movement, and fly eating capabalities for frogs
 */
public class Frog
{
    protected static final String imgFile = "frog.png";

    public static int length;

    protected GridLocation location;

    protected FlyWorld world;

    protected BufferedImage image;

    /**
     * Creates a new Frog object.<br>
     * The image file for a frog is frog.jpg<br>
     *
     * @param loc a GridLocation
     * @param fw the FlyWorld the frog is in
     */
    public Frog(GridLocation loc, FlyWorld fw)
    {
    // FILL IN
        location = loc;
        world = fw;

        try
        {
            image = ImageIO.read(new File(imgFile));
        }
        catch (IOException ioe)
        {
            System.out.println("Unable to read image file: " + imgFile);
            System.exit(0);
        }
        location.setFrog(this);
        

    }

    /**
     * @return BufferedImage the image of the frog
     */
    public BufferedImage getImage()
    {
    return image;
    }

    /**
     * @return GridLocation the location of the frog
     */
    public GridLocation getLocation()
    {
    return location;
    }

    /**
     * @return boolean, always true
     */
    public boolean isPredator()
    {
    return true;
    }

    /**
    * Returns a string representation of this Frog showing
    * the location coordinates and the world.
    *
    * @return the string representation
    */
    public String toString(){
        String s = "Frog in world:  " + this.world + "  at location (" + this.location.getRow() + ", " + this.location.getCol() + ")";
        return s;
    }

    /**
     * Generates a list of <strong>ALL</strong> possible legal moves<br>
     * for a frog.<br>
     * You should select all possible grid locations from<br>
     * the <strong>world</strong> based on the following restrictions<br>
     * Frogs can move one space in any of the four cardinal directions but<br>
     * 1. Can not move off the grid<br>
     * 2. Can not move onto a square that already has frog on it<br>
     * GridLocation has a method to help you determine if there is a frog<br>
     * on a location or not.<br>
     *
     * @return GridLocation[] a collection of legal grid locations from<br>
     * the <strong>world</strong> that the frog can move to
     */
    public GridLocation[] generateLegalMoves()
    {
        // FILL IN
        GridLocation frogLoc = this.getLocation();
        int frogRow = frogLoc.getRow();
        int frogCol = frogLoc.getCol();

        GridLocation[] legalMoves = new GridLocation[4];

        int idx = 0;
    
        if(world.isValidLoc(frogRow-1,frogCol) && !(world.getLocation(frogRow-1,frogCol).hasPredator())){
            
            //legalMoves[idx++] = (world.getLocation(frogRow-1,frogCol));
            legalMoves[idx++] = world.world[frogRow-1][frogCol];
        }

        if(world.isValidLoc(frogRow+1,frogCol) && !(world.getLocation(frogRow+1,frogCol).hasPredator())){
            
            //legalMoves[idx++] = (world.getLocation(frogRow+1,frogCol));
            legalMoves[idx++] = world.world[frogRow+1][frogCol];
        }


        if(world.isValidLoc(frogRow,frogCol-1) && !(world.getLocation(frogRow,frogCol-1).hasPredator())){
            
            //legalMoves[idx++] = (world.getLocation(frogRow,frogCol-1));
            legalMoves[idx++] = world.world[frogRow][frogCol-1];

        }
    
    
        if(world.isValidLoc(frogRow,frogCol+1) && !(world.getLocation(frogRow,frogCol+1).hasPredator())){
            
            //legalMoves[idx++] = (world.getLocation(frogRow,frogCol+1));
            legalMoves[idx++] = world.world[frogRow][frogCol+1];


        }

        GridLocation frogLegalMoves[] = new GridLocation[idx];

        for(int i = 0; i < idx; i++){
            frogLegalMoves[i] = legalMoves[i];
        }
            
        return frogLegalMoves;


        //return null; // THIS LINE IS JUST SO CODE COMPILES
    }

    /**
     * This method updates the frog's position.<br>
     * It should randomly select one of the legal locations(if there any)<br>
     * and set the frog's location to the chosen updated location.
     */
    public void update()
    {
        GridLocation [] filled = generateLegalMoves();
        if (filled.length > 0) {
        location.removeFrog();
        Random rand = new Random();
        int a = rand.nextInt(filled.length);
        location = filled [a];
        location.setFrog(this);
        }

        


        // FILL IN
    }

    /**
     * This method helps determine if a frog is in a location<br>
     * where it can eat a fly or not. A frog can eat the fly if it<br>
     * is on the same square as the fly or 1 spaces away in<br>
     * one of the cardinal directions
     *
     * @return boolean true if the fly can be eaten, false otherwise
     */ 
    public boolean eatsFly()
    {
        // FILL IN
        //the following code determines the exact location of the frog
        //to allow it to be able to eat a fly from either the north,south,west or east direction.

        int row = location.getRow();
        int col = location.getCol();
        GridLocation flylocation = world.getFlyLocation();

        if (flylocation.equals(location)) {
            return true;
        }
        if (flylocation.getRow()+1 == row && flylocation.getCol() == col){ 
            return true;
        }
        if (flylocation.getRow() == row && flylocation.getCol()+1 == col){
            return true;
        }
        if (flylocation.getRow()-1 == row && flylocation.getCol() == col){
            return true;
        }
        if (flylocation.getRow() == row && flylocation.getCol()-1 == col){
            return true;
        }
        return false; // THIS LINE IS JUST SO CODE COMPILES
    }   
}
