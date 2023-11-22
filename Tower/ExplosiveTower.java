package Tower;

import Enemy.Enemy;
import Attack.Explosion;
import Attack.Slow;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ExplosiveTower extends Tower {
    private ArrayList<Explosion> explosions = new ArrayList<>();
    private BufferedImage towerImage1;
    private BufferedImage towerImage2;

    public ExplosiveTower(
        int x,
        int y

) throws IOException {

    super(
            x,
            y,
            10,
            200,
            250,
            1000.0F,
            256
    );

    towerImage1 = ImageIO.read(new File("assets/towerDefense_tile182.png"));
    towerImage2 = ImageIO.read(new File("assets/towerDefense_tile204.png"));

}


    public Boolean Attack() {
        if (!_inRange.isEmpty() && timeSinceLastShot >= this._fireRate) {
            Enemy target = _inRange.get(0); // First enemy in range
            int centerX = this._x + 64 / 2;
            int centerY = this._y + 64 / 2;
            Explosion explosion = new Explosion(10, target, centerX, centerY);
            explosions.add(explosion);

            timeSinceLastShot = 0f;
            return true;
        }
        return false;
    }


public void updateProjectiles(float deltaTime, ArrayList<Enemy> enemies) {
    Iterator<Explosion> iterator = explosions.iterator();
    while (iterator.hasNext()) {
        Explosion explosion = iterator.next();
        if (!explosion.update(deltaTime, enemies)) {
            iterator.remove();
        }
    }
}


    @Override
    public void draw(Graphics2D g2d) {
        // Draw the tower and all active lasers
        g2d.drawImage(towerImage1, this._x, this._y, null);
        g2d.drawImage(towerImage2, this._x, this._y, null);
        for (Explosion explosion : explosions) {
            explosion.draw(g2d);
        }
    }


    public ArrayList<Explosion> getExplosions() {
        return explosions;
    }
 
}
