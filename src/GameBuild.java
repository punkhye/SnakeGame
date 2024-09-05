import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class GameBuild {

    int velocityX = 0;
    int velocityY = 0;
    boolean gameOver;

    int speed = 100;

    Timer gameLoop;

    int boardWidth = 600;
    int boardHeight = 600;

    boolean speedy = false;

    boolean minecraft = false;

    GameBuild(){

    }



    public void detectMode(String mode, ActionListener listener){
        String temp = mode.toLowerCase();

        switch (temp) {
            case "classic" -> gameLoop = new Timer(speed, listener);
            case "speedy" -> {
                gameLoop = new Timer(speed, listener);
                speedy = true;
            }
            case "minecraft" -> {
                gameLoop = new Timer(speed, listener);
                minecraft = true;
            }
        }


    }



    public void GameStart(){
        gameOver = false;
        gameLoop.start();
    }

    public void GameStop(){
        gameOver = true;
        gameLoop.stop();
    }


    public void drawSnake(Graphics g, Snake snake){

        /*grid
        for(int i =0; i < boardWidth/snake.tileSize; i++){
            g.drawLine(i*snake.tileSize,0,i*snake.tileSize, boardHeight);
            g.drawLine(0,i*snake.tileSize, boardWidth, i*snake.tileSize);
        }


         */

        //snake head
        g.setColor(Color.decode(snake.snakeColorHead));
        g.fillRect(snake.snakeHead.x *snake.tileSize, snake.snakeHead.y * snake.tileSize, snake.tileSize, snake.tileSize);

        //snake body
        g.setColor(Color.decode(snake.snakeColor));
        for (Tile snakePart : snake.snakeBody) {
            g.fillRect(snakePart.x * snake.tileSize, snakePart.y * snake.tileSize, snake.tileSize, snake.tileSize);
        }

        //score
        g.setColor(Color.lightGray);
        g.drawString("Score: " + snake.snakeBody.size(), snake.tileSize - 10, snake.tileSize);


    }

    public void drawFood(Graphics g, Food food){

        g.setColor(Color.RED);
        g.fillRect(food.x *food.tileSize, food.y *food.tileSize, food.tileSize, food.tileSize);
    }

    public void drawBlock(Graphics g, Block block) throws IOException {
        BufferedImage blockImageBuffered = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("img/block.png")));
        g.drawImage(blockImageBuffered, block.x * block.tileSize, block.y* block.tileSize, block.tileSize, block.tileSize, null);


        for(Block part : block.aroundBlocks){
            g.drawImage(blockImageBuffered, part.x * block.tileSize, part.y* block.tileSize, block.tileSize, block.tileSize, null);
        }



    }

    public void drawPickaxe(Graphics g, Pickaxe pickaxe) throws IOException {
        BufferedImage pickaxeImageBuffered = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("img/pickaxe.png")));
        g.drawImage(pickaxeImageBuffered, pickaxe.x*pickaxe.tileSize, pickaxe.y*pickaxe.tileSize, pickaxe.tileSize, pickaxe.tileSize, null);


    }


}
