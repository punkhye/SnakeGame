import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    int boardWidth ;
    int boardHeight ;
    int score = 0;
    String colorBackground = "#636940";
    Snake snake;
    Food food;

    Block block;

    Pickaxe pickaxe;

    GameBuild game;
    String mode;
    public GamePanel(String mode){
        this.mode = mode;
        game = new GameBuild();
        boardWidth = game.boardWidth;
        boardHeight = game.boardHeight;
        setPreferredSize(new Dimension(600,600));
        setBackground(Color.decode(colorBackground));
        add(new JLabel(mode));
        addKeyListener(this );
        setFocusable(true);



        //food
        food = new Food();
        food.placeFood(boardWidth, boardHeight);


        //snake
        snake = new Snake();
        snake.snakeHead = new Tile(5,5);
        snake.snakeBody = new ArrayList<Tile>();

        //minecraft mode
        block = new Block();
        block.placeMainBlock(boardWidth,boardHeight);
        block.aroundBlocks = new ArrayList<Block>();
        pickaxe = new Pickaxe();







        //game
        game.detectMode(mode, this);
        game.GameStart();

    }



    public void paintComponent(Graphics g ){
        super.paintComponent(g);
        game.drawFood(g, food);
        game.drawSnake(g, snake);

        if(game.minecraft){
            try {
                game.drawBlock(g, block);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if(game.minecraft && !block.aroundBlocks.isEmpty() ) {
            try {
                game.drawPickaxe(g, pickaxe);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }



    @Override
    public void actionPerformed(ActionEvent e) {
        snake.move(food, boardWidth, boardHeight, game, pickaxe, block, block.aroundBlocks);
        repaint();
        if(game.gameOver){
            game.GameStop();
            score = snake.snakeBody.size();
            Main.frame.SetPanel(new GameOverPanel(score, mode));
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(game.velocityY != 1 && e.getKeyCode() == KeyEvent.VK_UP
                || e.getKeyCode() == KeyEvent.VK_W && game.velocityY != 1 ){
            game.velocityX = 0;
            game.velocityY= -1;
        }
        else if(game.velocityY != -1 && e.getKeyCode() == KeyEvent.VK_DOWN
                || e.getKeyCode() == KeyEvent.VK_S && game.velocityY != -1){
            game.velocityX = 0;
            game.velocityY = 1;
        }
        else if(game.velocityX != 1 && e.getKeyCode() == KeyEvent.VK_LEFT
                || e.getKeyCode() == KeyEvent.VK_A && game.velocityX != 1){
            game.velocityX = -1;
            game.velocityY = 0;
        }
        else if(game.velocityX != -1 && e.getKeyCode() == KeyEvent.VK_RIGHT
                || e.getKeyCode() == KeyEvent.VK_D && game.velocityX != -1){
            game.velocityX = 1;
            game.velocityY = 0;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}
