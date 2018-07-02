import Drip.DripPanel;
import input.DisplayState;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Window.java
 * Generic Swing window class to play around in
 * 
 * @author Benjamin Steenhoek
 * @version 1
 */
class Window extends JFrame {
    private DripPanel dripPanel;

    Window()
    {
        initUI();
        initRedrawTimer();
    }

    private void initUI() {
        setTitle("Mouse drip demo");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        dripPanel = new DripPanel();
        add(dripPanel);
        dripPanel.initDripInfoManager();
    }

    private void initRedrawTimer() {
        new Timer(DisplayState.getMSInterval(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dripPanel.repaint();
            }
        }).start();
    }
}
