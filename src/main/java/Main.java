import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Window ex = new Window();
                ex.pack();
                ex.setVisible(true);
            }
        });
    }
}
