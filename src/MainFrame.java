import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MainFrame extends JFrame {

    public MainFrame(JPanel panel){

        //basics
        setTitle("Snake Game");
        setSize(600,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setContentPane(panel);
        pack();
        setVisible(true);
    }

    public void SetPanel(JPanel panel){

        Component temp = this.getContentPane();
        if(temp != null){
            this.remove(this.getContentPane());
        }
        this.setContentPane(panel);
        pack();
        setVisible(true);

    }



}

