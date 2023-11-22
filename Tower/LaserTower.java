package Tower;

import Enemy.Enemy;
import Attack.Laser;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class LaserTower extends Tower {
    private ArrayList<Laser> activeLasers;
    private BufferedImage towerImage1;
    private BufferedImage towerImage2;

    public LaserTower(int x, int y) throws IOException {
        super(x, y, 10, 50, 50, 800.0F, 256);
        this.activeLasers = new ArrayList<>();
        towerImage1 = ImageIO.read(new File("assets/towerDefense_tile183.png"));
        towerImage2 = ImageIO.read(new File("assets/towerDefense_tile226.png"));
    }

    @Override
    public Boolean Attack() {
        if (!_inRange.isEmpty() && timeSinceLastShot >= this._fireRate) {
            Enemy enemy = _inRange.get(0);
            int centerX = this._x + 64 / 2;
            int centerY = this._y + 64/ 2;
            Laser laser = new Laser(_damage, enemy, centerX, centerY);
            this.activeLasers.add(laser);
        
            
            timeSinceLastShot = 0f;
            return true;
        }
        return false;
    }


    @Override
    public void draw(Graphics2D g2d) {
        // Draw the tower and all active lasers
        g2d.drawImage(towerImage1, this._x, this._y, null);
        g2d.drawImage(towerImage2, this._x, this._y, null);
        for (Laser laser : activeLasers) {
            laser.draw(g2d);
        }
    }

    public ArrayList<Laser> getActiveLasers() {
        return activeLasers;
    }
}

