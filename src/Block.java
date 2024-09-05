import java.util.ArrayList;
import java.util.Random;

public class Block extends Tile{
    Random random = new Random();

     ArrayList<Block> aroundBlocks = new ArrayList<>();


    Block(){

    }





   public void placeMainBlock(int boardWidth, int boardHeight){
       x = random.nextInt(boardWidth/tileSize); //600/25
       y = random.nextInt(boardHeight/tileSize); //600/25
   }

   public void placeSurroundingBlocks(Block mainBlock) {

       int mainBlockX = mainBlock.x;
       int mainBlockY = mainBlock.y;

       Block newBlock = new Block();
       newBlock.x += mainBlockX;
       newBlock.y += mainBlockY;

       newXY(newBlock,mainBlock);




           for (Block block : mainBlock.aroundBlocks) {
               if (newBlock.x == block.x && newBlock.y == block.y) {
                    while (true){
                        newXY(newBlock,mainBlock);
                        if (newBlock.x != block.x && newBlock.y != block.y){
                            break;
                        }
                    }

               }
               else if(newBlock.x == mainBlockX && newBlock.y == block.y){
                  while(true){
                      newXY(newBlock,mainBlock);
                      if(newBlock.x != mainBlockX && newBlock.y != block.y){
                          break;
                      }
                  }
               }

           }


       mainBlock.aroundBlocks.add(newBlock);
   }

   public void newXY(Block newBlock, Block mainBlock){
       newBlock.x += random.nextInt(-2, 2);
       if(newBlock.x > 24 || newBlock.x < 0){
           newBlock.x = mainBlock.x;
       }
       newBlock.y += random.nextInt(-2, 2);
       if(newBlock.y > 24 || newBlock.y < 0){
           newBlock.y = mainBlock.y;
       }
   }


   public void removeAllBlocks(Block block){

        block.aroundBlocks.clear();
        block.placeMainBlock(600,600);

   }

}
