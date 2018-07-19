import Drip.DripMenu;
import Drip.DripPanel;
import input.InputManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Window extends JFrame {
    private InputManager inputManager;
    private DripPanel dripPanel;

    Window()
    {
        inputManager = new InputManager();
        initUI();
        initRedrawTimer();
        initDripPanel();
        initDripMenu();
    }

    private void initUI() {
        setBackground(Color.WHITE);
        setTitle("Mouse drip demo");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new FlowLayout());
        setResizable(false);
    }

    private void initDripPanel()
    {
        dripPanel = new DripPanel(inputManager);
        add(dripPanel);
        dripPanel.initDripInfoManager();
    }

    private void initDripMenu()
    {
        add(new DripMenu(dripPanel));
    }

    private void initRedrawTimer() {
        new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dripPanel.repaint();
            }
        }).start();
    }
}
