package Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("2D Tower Defense - Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFullScreen();

        MenuPanel menuPanel = new MenuPanel();
        menuPanel.addActionListener(new PlayButtonListener());

        add(menuPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class PlayButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            excuteGame();
        }
    }

    private void setFullScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(screenSize.width, screenSize.height);
    }

    private void excuteGame() {
        Map map = new Map();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainMenu::new);
    }
}
