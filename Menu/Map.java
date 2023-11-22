package Menu;

import javax.imageio.ImageIO;
import javax.swing.*;

import Attack.Explosion;
import Attack.Laser;
import Game.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Enemy.*;
import Tower.*;

public class Map extends JPanel {

    private ArrayList<Tower> towers;
    private ArrayList<Enemy> enemies;
    private boolean rotateDirection = true;
    private static final int DELAY = 10, TILE_SIZE = 64, TILE_COUNT = 100;
    private int currentTileX = -1, currentTileY = -1;
    private int[][] path1, path2;
    private int pathIndex1 = 0, pathIndex2 = 0;
    private BufferedImage[] tileImages;
    private int[][][] tiles;
    private JFrame frame;
    protected Timer gameTimer;
    private ArrayList<Wave> waves;
    private int currentWaveIndex = 0;
    private float elapsedTime = 0;
    private boolean isGameOver = false;
    private Player player = new Player(50, 200);

    private EscapePanel escapePanel;
    private boolean paused = false;

    public Map() {
        towers = new ArrayList<>();
        enemies = new ArrayList<>();
        path1 = new int[][]{{0,1},{1,1},{2,1},{3,1},{3,2},{3,3},{3,4},{4,4},{5,4},{6,4},{6,5},{6,6},{7,6},{8,6},{9,6}};
        path2 = new int[][]{{0,2},{1,2},{2,2},{2,3},{2,4},{2,5},{3,5},{4,5},{5,5},{5,6},{5,7},{6,7},{7,7},{8,7},{9,7}};
        tiles = new int[][][] {{{0, 0}, {1, 2}, {1, 1}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}},
                               {{0, 3}, {1, 2}, {1, 1}, {0, 2}, {0, 0}, {0, 0}, {0, 1}, {0, 0}, {0, 0}, {0, 0}},
                               {{0, 0}, {1, 2}, {1, 5}, {1, 3}, {1, 3}, {1, 7}, {0, 0}, {0, 0}, {0, 0}, {0, 0}},
                               {{0, 0}, {1, 6}, {1, 4}, {1, 4}, {1, 8}, {1, 1}, {0, 0}, {0, 1}, {0, 3}, {0, 0}},
                               {{0, 1}, {0, 2}, {0, 0}, {0, 0}, {1, 2}, {1, 1}, {0, 0}, {0, 0}, {0, 0}, {0, 0}},
                               {{0, 0}, {0, 0}, {0, 0}, {0, 0}, {1, 2}, {1, 5}, {1, 3}, {1, 7}, {0, 0}, {0, 0}},
                               {{0, 0}, {0, 0}, {0, 0}, {0, 2}, {1, 6}, {1, 4}, {1, 8}, {1, 1}, {0, 0}, {0, 5}},
                               {{0, 0}, {0, 1}, {0, 0}, {0, 0}, {0, 0}, {0, 2}, {1, 2}, {1, 1}, {0, 0}, {0, 0}},
                               {{0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 4}, {0, 5}, {1, 2}, {1, 1}, {0, 2}, {0, 1}},
                               {{0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {1, 2}, {1, 1}, {0, 0}, {0, 0}},};
        tileImages = new BufferedImage[14];
        loadTileImages();
        initMouseListener();
        setupFrame();
        waves = new ArrayList<>();
        initializeWaves();
        gameTimer = new Timer(DELAY, e -> updateGame());
        gameTimer.start();
        addKeyListener(new KeyAdapter());
        setFocusable(true);
        requestFocus();
    }

    private void initializeWaves() {
        Wave wave1 = new Wave();
        Wave wave2 = new Wave();
        Wave wave3 = new Wave();
        Wave wave4 = new Wave();
        Wave wave5 = new Wave();

        wave1.addA(new Soldier(1, 50, 5, 1.0f, 20, path1, 0),1F);
        wave1.addA(new Soldier(1, 50, 5, 1.0f, 20, path1, 0),2000F);
        wave1.addA(new Tank(1, 100, 10, 0.5f, 50, path1, 0),5000F);
        wave1.addB(new Tank(1, 100, 10, 0.5f, 50, path2, 0),1F);
        wave1.addB(new Soldier(1, 50, 5, 1.0f, 20, path1, 0),4000F);

        //wave 2
        wave2.addA(new Tank(1, 100, 10, 0.5f, 50, path1, 0),10000F);
        wave2.addB(new Tank(1, 100, 10, 0.5f, 50, path2, 0),10000F);
        wave2.addA(new Soldier(1, 50, 5, 1.0f, 20, path1, 0),12000F);
        wave2.addB(new Soldier(1, 50, 5, 1.0f, 20, path1, 0),12000F);
        wave2.addA(new Plane(1, 30, 3, 1.5f, 30, path1, 0),13000F);

        //wave3
        wave3.addA(new Plane(1, 30, 3, 1.5f, 30, path1, 0),18000F);
        wave3.addB(new Plane(1, 30, 3, 1.5f, 30, path1, 0),18000F);
        wave3.addA(new Soldier(1, 50, 5, 1.0f, 20, path1, 0),19000F);
        wave3.addA(new Soldier(1, 50, 5, 1.0f, 20, path1, 0),21000F);
        wave3.addB(new Soldier(1, 50, 5, 1.0f, 20, path1, 0),21000F);

        //wave4
        wave4.addA(new Tank(1, 100, 10, 0.5f, 50, path1, 0),26000F);
        wave4.addB(new Tank(1, 100, 10, 0.5f, 50, path2, 0),28000F);
        wave4.addA(new Soldier(1, 50, 5, 1.0f, 20, path1, 0),28000F);
        wave4.addB(new Soldier(1, 50, 5, 1.0f, 20, path1, 0),30000F);
        wave4.addA(new Plane(1, 30, 3, 1.5f, 30, path1, 0),31000F);

        //wave5
        wave5.addA(new Tank(1, 1000, 50, 0.4f, 5000, path1, 0),40000F);

        waves.add(wave1);
        waves.add(wave2);
        waves.add(wave3);
        waves.add(wave4);
        waves.add(wave5);
    }

    private void updateGame() {
        if (isGameOver) {

            System.out.println("test");
        } 
        float deltaTime = DELAY;
        elapsedTime += DELAY;
        if (player.getHealth() <= 0) {
            isGameOver = true; 
            gameTimer.stop();
            backToMenu();

        }
        Iterator<Enemy> enemyIterator = enemies.iterator();
        while (enemyIterator.hasNext()) {
            Enemy enemy = enemyIterator.next();
            enemy.update();
            if (enemy.getHealth() <= 0) {
                player.setMoney(player.getMoney() + enemy.getRevenue());
                enemyIterator.remove();
            }
            else if (enemy.hasReachedEnd()) {
                player.setHealth(player.getHealth() - enemy.getDamage());
                enemyIterator.remove();
            }
        }
        for (Tower tower : towers) {
            tower.update(enemies, deltaTime);
            tower.Attack();
            if (tower instanceof SlowTower) {
                ((SlowTower) tower).updateProjectiles(deltaTime);
            }
            if (tower instanceof ExplosiveTower) {
                ((ExplosiveTower) tower).updateProjectiles(deltaTime, enemies);
            }
            if (tower instanceof LaserTower) {
                LaserTower laserTower = (LaserTower) tower;
                Iterator<Laser> laserIterator = laserTower.getActiveLasers().iterator();

                while (laserIterator.hasNext()) {
                    Laser laser = laserIterator.next();
                    if (!laser.update(deltaTime)) {  
                    }
                }
            }
        }
        if (currentWaveIndex < waves.size()) {
            Wave currentWave = waves.get(currentWaveIndex);
            currentWave.spawnEnemies(elapsedTime, enemies);

            if (currentWave.getWaveA().isEmpty() && currentWave.getWaveB().isEmpty()) {
                currentWaveIndex++; 
            }
        }
        repaint();
    }

   private void loadTileImages() {
        String[] imageFiles = {
            "assets/towerDefense_tile024.png",
            "assets/towerDefense_tile001.png",
            "assets/towerDefense_tile047.png",
            "assets/towerDefense_tile025.png",
            "assets/towerDefense_tile023.png",
            "assets/towerDefense_tile002.png",
            "assets/towerDefense_tile004.png",
            "assets/towerDefense_tile026.png",
            "assets/towerDefense_tile046.png",
            "assets/towerDefense_tile131.png",
            "assets/towerDefense_tile045.png",
            "assets/towerDefense_tile135.png",
            "assets/towerDefense_tile136.png",
            "assets/towerDefense_tile137.png"
        };

        try {
            for (int i = 0; i < tileImages.length; i++) {
                tileImages[i] = ImageIO.read(new File(imageFiles[i]));
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void initMouseListener() {
        JPopupMenu tileMenu = new JPopupMenu();
        JMenuItem changeToType6 = new JMenuItem("Laser Tower : 50$");
        if (player.getMoney()>=50){
            changeToType6.addActionListener(e -> changeTileType(6));
            tileMenu.add(changeToType6);
        }

        JMenuItem changeToType7 = new JMenuItem("Slow Tower : 100$");
        if(player.getMoney()>=100){
            changeToType7.addActionListener(e -> changeTileType(7));
            tileMenu.add(changeToType7);
        }
        JMenuItem changeToType8 = new JMenuItem("Explosive Tower : 200$");
        if(player.getMoney()>=200){
            changeToType8.addActionListener(e -> changeTileType(8));
            tileMenu.add(changeToType8);
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                int tileX = x / TILE_SIZE;
                int tileY = y / TILE_SIZE;

                if (tileX >= 0 && tileX < tiles.length && tileY >= 0 && tileY < tiles[tileX].length) {
                    if (isTileClickable(tiles[tileX][tileY][1]) && tiles[tileX][tileY][0] == 0) {
                        currentTileX = tileX;
                        currentTileY = tileY;
                        tileMenu.show(Map.this, x, y);
                    }
                }
            }
        });
    }

    private void changeTileType(int newType) {
        if (currentTileX >= 0 && currentTileY >= 0) {
            int cost = 0;
            if (newType == 6) {
                cost = 50;
            } else if (newType == 7) {
                cost = 100;
            } else if (newType == 8) {
                cost = 200; 
            }

            if (player.getMoney() >= cost) {

                player.setMoney(player.getMoney() - cost);
                tiles[currentTileX][currentTileY][1] = newType;
                repaint();  

                try {
                    if (newType == 6) {
                        addLaserTower(currentTileX * TILE_SIZE, currentTileY * TILE_SIZE);
                    } else if (newType == 7) {
                        addSlowTower(currentTileX * TILE_SIZE, currentTileY * TILE_SIZE);
                    } else if (newType == 8) {
                        addExplosiveTower(currentTileX * TILE_SIZE, currentTileY * TILE_SIZE);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private boolean isTileClickable(int tileType) {
        int[] clickableTypes = {2};
        for (int type : clickableTypes) {
            if (tileType == type) {
                return true;
            }
        }
        return false;
    }

    public void addLaserTower(int x, int y) throws IOException {
        LaserTower newTower = new LaserTower(x, y);
        towers.add(newTower);
    }

    public void addSlowTower(int x, int y) throws IOException {
        SlowTower newTower = new SlowTower(x, y);
        towers.add(newTower);
    }

    public void addExplosiveTower(int x, int y) throws IOException {
        ExplosiveTower newTower = new ExplosiveTower(x, y);
        towers.add(newTower);
    }

    private void setupFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(TILE_SIZE * TILE_COUNT, TILE_SIZE * TILE_COUNT);
        frame.add(this);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j][0] == 1) {
                    switch (tiles[i][j][1]) {
                        case 1:
                            g.drawImage(tileImages[1], i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
                            break;
                        case 2:
                            g.drawImage(tileImages[2], i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
                            break;
                        case 3:
                            g.drawImage(tileImages[3], i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
                            break;
                        case 4:
                            g.drawImage(tileImages[4], i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
                            break;
                        case 5:
                            g.drawImage(tileImages[5], i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
                            break;
                        case 6:
                            g.drawImage(tileImages[6], i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
                            break;
                        case 7:
                            g.drawImage(tileImages[7], i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
                            break;
                        case 8:
                            g.drawImage(tileImages[8], i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
                            break;
                        case 9:
                            g.drawImage(tileImages[9], i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
                            break;
                    }
                    } else {
                        g.drawImage(tileImages[0], i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
                        switch (tiles[i][j][1]) {
                            case 1:
                                g.drawImage(tileImages[9], i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
                                break;
                            case 2:
                                g.drawImage(tileImages[10], i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
                                break;
                            case 3:
                                g.drawImage(tileImages[11], i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
                                break;
                            case 4:
                                g.drawImage(tileImages[12], i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
                                break;
                            case 5:
                                g.drawImage(tileImages[13], i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
                                break;
                        }
                    }
                }
            }
            for (Tower tower : towers) {
                tower.draw(g2d);
                if (tower instanceof ExplosiveTower) {
                    for (Explosion explosion : ((ExplosiveTower) tower).getExplosions()) {
                        explosion.draw(g2d);
                    }
                }
            }
            for (Enemy enemy : enemies) {
                enemy.draw(g2d);
                enemy.drawHealthBar(g2d);
            }
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Consolas", Font.BOLD, 20));
            String moneyText = "Money: $" + player.getMoney();
            g2d.drawString(moneyText, 410, 20);
            int healthBarWidth = 200;
            int healthBarHeight = 20;
            int healthBarX = 410;
            int healthBarY = 40;


            int currentHealthWidth = (int) ((player.getHealth() / (float) player.getMaxHealth()) * healthBarWidth);


            g2d.setColor(Color.GRAY);
            g2d.fillRect(healthBarX, healthBarY, healthBarWidth, healthBarHeight);

            g2d.setColor(Color.RED);
            g2d.fillRect(healthBarX, healthBarY, currentHealthWidth, healthBarHeight);

            g2d.setColor(Color.BLACK);
            g2d.drawRect(healthBarX, healthBarY, healthBarWidth, healthBarHeight);
            
            if (isGameOver) {
                String gameOverText = "GAME OVER";
                g2d.setFont(new Font("Consolas", Font.BOLD, 50));
                

                FontMetrics fm = g2d.getFontMetrics();
                int textWidth = fm.stringWidth(gameOverText);
                int textHeight = fm.getHeight();

                int textX = (getWidth() - textWidth) / 2 -600;
                int textY = (getHeight() + textHeight) / 2;

                g2d.setColor(Color.RED);
                g2d.drawString(gameOverText, textX, textY);

            }
    }

    private class KeyAdapter implements KeyListener {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                togglePause();
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
            // Unused method
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // Unused method
        }
    }

    public void togglePause() {
        paused = !paused;
        if (paused) {
            setEscapePanel();
            gameTimer.stop();
        } else {
            removeEscapePanel();
            gameTimer.start();
        }
    }

    private void setEscapePanel() {
        escapePanel = new EscapePanel(gameTimer);
        add(escapePanel);
        revalidate(); 
        repaint();    
    }

    private void removeEscapePanel() {
        if (escapePanel != null) {
            remove(escapePanel);
            escapePanel = null;
            revalidate(); 
            repaint();
        }
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void backToMenu() {
        escapePanel = new EscapePanel("test");
        add(escapePanel);
        revalidate(); 
        repaint();    
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Map());
    }
}