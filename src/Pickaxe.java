import java.util.Random;

public class Pickaxe extends Tile{

    Random random = new Random();

    boolean isPlaced = false;

    Pickaxe(){

    }

    Pickaxe(int x, int y){
        super(x,y);
        this.x = x;
        this.y = y;
    }

    public void placePickaxe(int boardWidth, int boardHeight){

        if(!isPlaced) {
            x = random.nextInt(boardWidth / tileSize); //600/25
            y = random.nextInt(boardHeight / tileSize); //600/25
            isPlaced = true;
        }
    }


}
