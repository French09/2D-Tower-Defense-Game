package Menu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * EscapePanel
 */
// 

// public class EscapePanel extends JPanel {
public class EscapePanel extends JPanel {

    private Timer gameTimer;
    private JLabel gamePausedLabel;

    public EscapePanel (String paramString) {
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        gamePausedLabel = new JLabel("<html><h1><strong><i>Game over</i></strong></h1><hr></html>");
        add(gamePausedLabel, gbc);


        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel buttons = new JPanel(new GridBagLayout());

        JButton startMenuJButton = new JButton("Start menu");
        startMenuJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu mainMenu = new MainMenu();
            }
            
        });

        /**
         * List buttons
         */
        buttons.add(startMenuJButton, gbc);

        add(buttons, gbc);
    }
    
    public EscapePanel(Timer gametimer) {
        this.gameTimer = gametimer;

        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        gamePausedLabel = new JLabel("<html><h1><strong><i>Game paused</i></strong></h1><hr></html>");
        add(gamePausedLabel, gbc);


        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel buttons = new JPanel(new GridBagLayout());

        JButton resumeJButton = new JButton("Resume");
        JButton startMenuJButton = new JButton("Start menu");
        JButton closeJButton = new JButton("Leave game");

        resumeJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameTimer.start();
                buttons.setVisible(false);
                gamePausedLabel.setVisible(false);
            }
        });
        startMenuJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu mainMenu = new MainMenu();
            }
            
        });
        closeJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit the game?", "Quit Game",
                    JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        /**
         * List buttons
         */
        buttons.add(resumeJButton, gbc);
        buttons.add(startMenuJButton, gbc);
        buttons.add(closeJButton, gbc);

        add(buttons, gbc);
    }
}