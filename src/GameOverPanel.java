import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameOverPanel extends JPanel {

    int score;
    static String mode;
    JButton mainMenuButton = new JButton("Main menu");
    JButton retryButton = new JButton("Try again");
    JLabel gameOverLabel = new JLabel("GAME OVER");
    JLabel scoreLabel = new JLabel("Score: ");
    JPanel overPanel = new JPanel();
    JPanel scorePanel = new JPanel();
    JPanel buttonsPanel = new JPanel();
    JPanel blankPanel = new JPanel();
    public GameOverPanel(int score, String mode){
        this.mode = mode;
        setPreferredSize(new Dimension(600,600));
        setLayout(new GridLayout(4,1));
        String colorBackground = "#636940";
        String buttonColor = "#ADBF97";
        setBackground(Color.decode(colorBackground));
        blankPanel.setBackground(Color.decode(colorBackground));
        overPanel.setBackground(Color.decode(colorBackground));
        scorePanel.setBackground(Color.decode(colorBackground));
        buttonsPanel.setBackground(Color.decode(colorBackground));
        mainMenuButton.setBackground(Color.decode(buttonColor));
        retryButton.setBackground(Color.decode(buttonColor));
        mainMenuButton.setPreferredSize(new Dimension(200, 50));
        retryButton.setPreferredSize(new Dimension(200, 50));
        JLabel pointsLabel =new JLabel(String.valueOf(score));
        gameOverLabel.setFont(new Font("Century Gothic", Font.PLAIN, 64));
        scoreLabel.setFont(new Font("Ariel", Font.PLAIN, 32));
        pointsLabel.setFont(new Font("Ariel", Font.PLAIN, 32));

        add(blankPanel);
        add(overPanel);
        overPanel.add(gameOverLabel);
        add(scorePanel);

        scorePanel.add(scoreLabel);
        scorePanel.add(pointsLabel);
        add(buttonsPanel);
        buttonsPanel.add(mainMenuButton);
        buttonsPanel.add(retryButton);

        retryButton.addActionListener(new RetryClicked());
        mainMenuButton.addActionListener(new MainMenuClicked());

        setVisible(true);

    }

    public static class RetryClicked implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            GamePanel gamePanel = new GamePanel(mode);
            Main.frame.SetPanel(gamePanel);
            gamePanel.requestFocus();
        }
    }

    public static class MainMenuClicked implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Main.frame.SetPanel(new MainPanel());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }
    }
}
