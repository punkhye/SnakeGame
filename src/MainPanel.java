import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class MainPanel extends JPanel {

    ModePanel modePanel = new ModePanel();
    JPanel firstPanel = new JPanel();
    JPanel secondPanel = new JPanel();
    JPanel thirdPanel = new JPanel();

    JButton playButton = new JButton("Play");
    JButton exitButton = new JButton("Exit");
    public MainPanel() throws IOException {
        setPreferredSize(new Dimension(600,600));

        ImageIcon appleImageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("img/snakeButtonHover.gif")));
        ImageIcon scaledAppleImage = new ImageIcon(appleImageIcon.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
        JLabel appleGifLabel = new JLabel(scaledAppleImage);


        ImageIcon titleSnakeIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("img/titleScreen.gif")));
        ImageIcon scaledTitleSnakeIcon  = new ImageIcon(titleSnakeIcon.getImage().getScaledInstance(100,80,Image.SCALE_DEFAULT));
       // Icon titleScreenIcon = new ImageIcon(new ImageIcon("src/img/titleScreen.gif").getImage().getScaledInstance(100, 80, Image.SCALE_DEFAULT));
        JLabel titleGifLabel = new JLabel(scaledTitleSnakeIcon);

        ImageIcon textIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("img/text.gif")));
        ImageIcon scaledTextIcon  = new ImageIcon(textIcon.getImage().getScaledInstance(400,100,Image.SCALE_DEFAULT));
        //Icon textScreenIcon = new ImageIcon(new ImageIcon("src/img/text.gif").getImage().getScaledInstance(400, 100, Image.SCALE_DEFAULT));
        JLabel textScreenGifLabel = new JLabel(scaledTextIcon);



        //bg color
        String colorBackground = "#636940";
        String buttonColor = "#ADBF97";
        this.setBackground(Color.decode(colorBackground));
        firstPanel.setBackground(Color.decode(colorBackground));
        secondPanel.setBackground(Color.decode(colorBackground));
        thirdPanel.setBackground(Color.decode(colorBackground));
        //buttons color
        playButton.setBackground(Color.decode(buttonColor));
        exitButton.setBackground(Color.decode(buttonColor));

        this.setLayout(new GridLayout(4,1));

        //panels
        this.add(firstPanel);
        firstPanel.add(textScreenGifLabel);
        firstPanel.add(titleGifLabel);

        this.add(secondPanel);

        playButton.setPreferredSize(new Dimension(200, 50));
        secondPanel.add(playButton);

        playButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                playButton.add(appleGifLabel);
                playButton.setVisible(false);
                playButton.setVisible(true);
            }
            public void mouseExited(MouseEvent evt) {
                playButton.remove(appleGifLabel);
                playButton.setVisible(false);
                playButton.setVisible(true);
            }
        });


        this.add(thirdPanel);
        exitButton.setPreferredSize(new Dimension(200, 50));
        thirdPanel.add(exitButton);
        exitButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                exitButton.add(appleGifLabel);
                exitButton.setVisible(false);
                exitButton.setVisible(true);
            }
            public void mouseExited(MouseEvent evt) {
                exitButton.remove(appleGifLabel);
                exitButton.setVisible(false);
                exitButton.setVisible(true);
            }
        });

        playButton.addActionListener(new PlayClicked());
        exitButton.addActionListener(new ExitClicked());




        this.setVisible(false);
        this.setVisible(true);

    }


    static class ExitClicked implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    public class PlayClicked implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {

            Main.frame.SetPanel(modePanel);

        }
    }



}