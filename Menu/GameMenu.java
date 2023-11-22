package Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter; 



public class GameMenu extends JFrame {

    private JPanel mainMenuPanel;
    private JPanel playPanel;
    private JPanel gameOverPanel;
    private JPanel escapePanel;
    private boolean isPaused = false;

    private String currentState;

    public GameMenu() {
        setTitle("Game Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(screenSize.width, screenSize.height);

        mainMenuPanel = createMainMenuPanel();
        playPanel = createPlayPanel();
        escapePanel = createEscapePanel();
        gameOverPanel = createGameOverPanel();

        playPanel.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    if (currentState.equals("Play") && !isPaused) {
                        switchToPauseState();
                    } else if (currentState.equals("Pause")) {
                        switchToPlayState();
                    }
                }
            }
            
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        add(mainMenuPanel);

        pack();
        setLocationRelativeTo(null); 
        setVisible(true);
        
    }

    private GridBagConstraints getConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0);
        return gbc;
    }

    private JPanel createMainMenuPanel() {
        JPanel panel = new JPanel(new GridBagLayout());

        JButton playButton = new JButton("Play");
        JButton tutoButton = new JButton("How to play ?");
        JButton closeButton = new JButton("Quit game");

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToPlayState();
            }
        });

        tutoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Rules of the game:\n" +
                        "1. Place towers on the map to stop enemies from reaching your base.\n" +
                        "2. Fire tower shoots bullets and are cheaper, RocketTower shoot rockets and deald an area of effect damage.\n" +
                        "3. You earn money for stopping enemies, but as the game progresses, new enemies attack.\n" +
                        "4. If you stop 500 enemies you win, but if you lose X lives the game is over.");
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit the game?", "Quit Game",
                        JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        panel.add(new JLabel("Main Menu"), getConstraints());
        panel.add(playButton, getConstraints());
        panel.add(tutoButton, getConstraints());
        panel.add(closeButton, getConstraints());

        return panel;
    }

    // ! Game logic in there
    private JPanel createPlayPanel() {
        JPanel panel = new JPanel(new GridBagLayout());

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    if (currentState.equals("Play") && !isPaused) {
                        switchToPauseState();
                    } else if (currentState.equals("Pause")) {
                        switchToPlayState();
                    }
                }
            }
        });

        panel.setFocusable(true);
        panel.requestFocusInWindow();

        return panel;
    }

    private JPanel createGameOverPanel() {
        JPanel panel = new JPanel(new GridBagLayout());

        JButton mainMenuButton = new JButton("Main Menu");
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToMainMenuState();
            }
        });

        panel.add(new JLabel("Game Over"), getConstraints());
        panel.add(mainMenuButton, getConstraints());

        return panel;
    }

    private JPanel createPausePanel() {
        JPanel panel = new JPanel(new GridBagLayout());

        JButton resumeButton = new JButton("Resume");
        JButton menuButton = new JButton("Back to menu");
        JButton quitButton = new JButton("Quit game");

        // Add KeyAdapter to the escapePanel
    
        // Set the escapePanel focusable to enable KeyListener
        escapePanel.setFocusable(true);
        escapePanel.requestFocusInWindow();

        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToPlayState();
            }
        });

        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToMainMenuState();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit the game?", "Quit Game",
                        JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        panel.add(new JLabel("Play"), getConstraints());
        panel.add(resumeButton, getConstraints());
        panel.add(menuButton, getConstraints());
        panel.add(quitButton, getConstraints());

        // Set the panel focusable to enable KeyListener
        panel.setFocusable(true);
        panel.requestFocusInWindow();

        return panel;
    }

    private JPanel createEscapePanel() {
        JPanel panel = new JPanel(new GridBagLayout());

        JButton resumeButton = new JButton("Resume");
        JButton menuButton = new JButton("Back to menu");
        JButton quitButton = new JButton("Quit game");

        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToPlayState();
            }
        });

        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToMainMenuState();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit the game?", "Quit Game",
                        JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        panel.add(new JLabel("Play"), getConstraints());
        panel.add(resumeButton, getConstraints());
        panel.add(menuButton, getConstraints());
        panel.add(quitButton, getConstraints());

        return panel;
    }
    private void switchToPlayState() {
        remove(mainMenuPanel);
        add(playPanel);
        currentState = "Play";
        setFocusable(true);
        requestFocus();
        revalidate();
        repaint();
    }

    private void switchToPauseState() {
        remove(playPanel);
        add(escapePanel);
        currentState = "Pause";
        isPaused = true;
        revalidate();
        repaint();
    }
    

    private void switchToGameOverState() {
        remove(playPanel);
        add(gameOverPanel);
        currentState = "GameOver";
        revalidate();
        repaint();
    }

    private void switchToMainMenuState() {
        System.out.println("trying to get to the menu");
        remove(escapePanel);
        add(createMainMenuPanel());
        currentState = "MainMenu";
        getContentPane().revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameMenu();
            }
        });
    }

}