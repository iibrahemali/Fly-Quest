import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class Spider extends Frog{
    protected static final String imgFile = "spider.png";
    
    public Spider(GridLocation loc, FlyWorld fw)
    { super(loc, fw);
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
    
    @Override
    public GridLocation[] generateLegalMoves(){

        GridLocation spiderLoc = this.getLocation();
        int spiderRow = spiderLoc.getRow();
        int spiderCol = spiderLoc.getCol();

        GridLocation[] legalMoves = new GridLocation[4];

        int idx = 0;
        if(world.getFlyLocation().getRow() == location.getRow()){
            if(world.isValidLoc(spiderRow,spiderCol-1) && !(world.getLocation(spiderRow,spiderCol-1).hasPredator()) && location.getCol() > world.getFlyLocation().getCol()){
                //adding [locationrow][locationcol+1]
                legalMoves[idx++] = world.world[spiderRow][spiderCol-1];

            }else{
            // adding [locationrow][locationcol-1]
            legalMoves[idx++] = world.world[spiderRow][spiderCol+1];
            }

            }
        else if (world.getFlyLocation().getCol() == location.getCol()){
            if(world.isValidLoc(spiderRow-1,spiderCol) && !(world.getLocation(spiderRow-1,spiderCol).hasPredator()) && location.getRow() > world.getFlyLocation().getRow()){
                legalMoves[idx++] = world.world[spiderRow-1][spiderCol];
            }else{
            legalMoves[idx++] = world.world[spiderRow+1][spiderCol];
            }
        }
        else{
            if (world.isValidLoc(spiderRow,spiderCol+1) && !(world.getLocation(spiderRow,spiderCol+1).hasPredator()) && location.getCol() < world.getFlyLocation().getCol()) {
                legalMoves[idx++] = world.world[spiderRow][spiderCol+1];

                
            }else{
                legalMoves[idx++] = world.world[spiderRow][spiderCol-1];
            }

            if (world.isValidLoc(spiderRow+1,spiderCol) && !(world.getLocation(spiderRow+1,spiderCol).hasPredator()) && location.getRow() < world.getFlyLocation().getRow()){
                legalMoves[idx++] = world.world[spiderRow+1][spiderCol];


            }
            else{
                legalMoves[idx++] = world.world[spiderRow-1][spiderCol];

            }
        }
        GridLocation frogLegalMoves[] = new GridLocation[idx];

        for(int i = 0; i < idx; i++){
            frogLegalMoves[i] = legalMoves[i];
        }
            
        return frogLegalMoves;
    }

    @Override
    public boolean eatsFly()
    {
        // FILL IN
        //the following code determines the exact location of the frog
        //to allow it to be able to eat a fly from either the north,south,west or east direction.

        GridLocation flylocation = world.getFlyLocation();

        if (flylocation.equals(location)) {
            return true;
        }
        return false;

        




}
}
