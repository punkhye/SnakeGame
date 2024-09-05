import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ModePanel extends JPanel {

    String[] gameModes = {"Classic", "Speedy", "Minecraft"};
    JButton changeModeButton = new JButton("change");
    JButton backButton = new JButton("back");
    JButton startButton = new JButton("start");
    public String mode;
    public JLabel modeLabel = new JLabel("Game mode:");
    public JLabel modeNameLabel = new JLabel("");
    public JPanel blank = new JPanel();
    public JPanel modePanel = new JPanel(new FlowLayout());
    public JPanel changePanel = new JPanel();
    public JPanel downButtonsPanel = new JPanel();
    public int counterMode = 0;

   public ModePanel(){
       //colors sizes
       setPreferredSize(new Dimension(600,600));
       String colorBackground = "#636940";
       String buttonColor = "#ADBF97";
       String textColor = "#474A2C";
       this.setBackground(Color.decode(colorBackground));
       blank.setBackground(Color.decode(colorBackground));
       modePanel.setBackground(Color.decode(colorBackground));
       changePanel.setBackground(Color.decode(colorBackground));
       downButtonsPanel.setBackground(Color.decode(colorBackground));
       backButton.setBackground(Color.decode(buttonColor));
       backButton.setPreferredSize(new Dimension(200, 50));
       startButton.setBackground(Color.decode(buttonColor));
       startButton.setPreferredSize(new Dimension(200, 50));
       changeModeButton.setBackground(Color.decode(buttonColor));
       changeModeButton.setPreferredSize(new Dimension(200, 50));
       modeLabel.setFont(new Font("Century Gothic", Font.PLAIN, 32));
       modeNameLabel.setFont(new Font("Ariel", Font.BOLD, 32));
       modeNameLabel.setBorder(BorderFactory.createLineBorder((Color.decode(textColor)), 2));
       modeNameLabel.setText(gameModes[counterMode]);
       mode = gameModes[counterMode];
       this.setLayout(new GridLayout(4,1,10,10));

       //panels
       this.add(blank);
       this.add(modePanel);
       modePanel.add(modeLabel);
       modePanel.add(modeNameLabel);
       this.add(changePanel);
       changePanel.add(changeModeButton);
       this.add(downButtonsPanel);
       downButtonsPanel.add(backButton);
       downButtonsPanel.add(startButton);

       backButton.addActionListener(new BackClicked());
       changeModeButton.addActionListener(new ChangeClicked());
       startButton.addActionListener(new StartClicked());

        this.setVisible(true);
    }

    public class ChangeClicked implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(counterMode == gameModes.length-1){
                counterMode = 0;
            }else{
                ++counterMode;
            }
            modeNameLabel.setText(gameModes[counterMode]);
            mode = gameModes[counterMode];
            setVisible(false);
            setVisible(true);
        }
    }

    public static class BackClicked implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Main.frame.SetPanel(new MainPanel());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public class StartClicked implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            GamePanel gamePanel = new GamePanel(mode);
            Main.frame.SetPanel(gamePanel);
            gamePanel.requestFocus();

        }
    }


}
