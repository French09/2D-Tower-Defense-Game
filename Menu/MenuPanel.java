package Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class MenuPanel extends JPanel {

    private ActionListener actionListener;

    public MenuPanel() {
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        add(new JLabel("<html><h1><strong><i>2D Tower defense</i></strong></h1><hr></html>"), gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel buttons = new JPanel(new GridBagLayout());

        /**
         * Buttons
         */
        JButton playGame = new JButton("Play");
        JButton tutorialGame = new JButton("How to play ?");
        JButton closeGame = new JButton("Quit game");

        /**
         * ! Events listener
         */
        playGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (actionListener != null) {
                    actionListener.actionPerformed(e);
                }
            }
        });
        tutorialGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Rules of the game:\n" +
                        "1. Place towers on the map to stop enemies from reaching your base.\n" +
                        "2. Fire tower shoots bullets and are cheaper, RocketTower shoot rockets and deald an area of effect damage.\n" +
                        "3. You earn money for stopping enemies, but as the game progresses, new enemies attack.\n" +
                        "4. If you stop 500 enemies you win, but if you lose X lives the game is over.");
            }
        });

        closeGame.addActionListener(new ActionListener() {
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
        buttons.add(playGame, gbc);
        buttons.add(tutorialGame, gbc);
        buttons.add(closeGame, gbc);

        add(buttons, gbc);

    }

    public void addActionListener(ActionListener listener) {
        this.actionListener = listener;
    }
}