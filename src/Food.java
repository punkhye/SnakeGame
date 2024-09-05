import java.util.Random;

public class Food extends Tile{
    Random random = new Random();
    Food(){

    }



    public void placeFood(int boardWidth, int boardHeight) {
        x = random.nextInt(boardWidth/tileSize); //600/25
        y = random.nextInt(boardHeight/tileSize); //600/25
    }

    public void removeFood(){

        x = -99;
        y = -99;
    }
}

