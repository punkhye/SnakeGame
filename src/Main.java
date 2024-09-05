import java.awt.*;
import java.io.IOException;

public class Main {

    public static MainFrame frame;

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    frame = new MainFrame(new MainPanel());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });



    }
}
