import java.util.ArrayList;

public class Snake extends Tile{

    int speed = 100;
    String snakeColor = "#9BDEAC";

    String snakeColorHead = "#75D18C";

    Tile snakeHead = new Tile(5,5) ;

    ArrayList<Tile> snakeBody;


    Snake(){

    }
    Snake(Tile snakeHead, ArrayList<Tile> snakeBody){

        this.snakeHead = snakeHead;
        snakeBody = new ArrayList<Tile>();
    }

    public boolean collision(Tile tile1, Tile tile2){

        return tile1.x == tile2.x && tile1.y ==  tile2.y;
    }

    public void move(Food food, int boardWidth, int boardHeight, GameBuild game, Pickaxe pickaxe, Block block, ArrayList<Block> blocks){
        //eating food
        if(collision(snakeHead, food)){
            if(game.speedy){
                speed -= 2;
                game.gameLoop.setDelay(speed);
            }

            if(game.minecraft){
                block.placeSurroundingBlocks(block);
            }

            if(!blocks.isEmpty()){
                pickaxe.placePickaxe(boardWidth,boardHeight);
                pickaxe.isPlaced = true;
            }

            snakeBody.add(new Tile(food.x, food.y));
            food.placeFood(boardWidth,boardHeight);
            for(Block part : block.aroundBlocks){
                if(food.x == part.x && food.y == part.y){
                    food.removeFood();
                    food.placeFood(boardWidth,boardHeight);
                }
            }
        }

        //if it takes the pickaxe
        if(collision(snakeHead, pickaxe)){
            block.removeAllBlocks(block);

        }

        //if it hits a block
        if(collision(block, snakeHead)){
            game.gameOver = true;
        }

        for(Block oneblock : block.aroundBlocks){
            if(collision(oneblock, snakeHead)){
                game.gameOver = true;
            }
        }


        //snake body
        for(int i = snakeBody.size()-1; i >= 0; i--){
            Tile snakePart = snakeBody.get(i);
            if(i == 0){
                snakePart.x = snakeHead.x;
                snakePart.y = snakeHead.y;

            }
            else{
                Tile prevSnakePart = snakeBody.get(i-1);
                snakePart.x = prevSnakePart.x;
                snakePart.y = prevSnakePart.y;
            }
        }

        //snake head
        snakeHead.x += game.velocityX;
        snakeHead.y += game.velocityY;


        //if it eats itself
        for (Tile snakePart : snakeBody) {
            if (collision(snakeHead, snakePart)) {
                game.gameOver = true;
            }
        }

        //if it hits a wall
        if(snakeHead.x*tileSize < 0 || snakeHead.x*tileSize > boardWidth
                || snakeHead.y*tileSize <0 || snakeHead.y*tileSize >boardHeight){
            game.gameOver = true ;
        }

        //if food spawns in snake's body
        for(Tile snakePart : snakeBody) {

            if(collision(food, snakePart)){
                food.removeFood();
                food.placeFood(boardWidth, boardHeight);
            }

        }


    }



}
