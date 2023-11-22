package Tower;

import Enemy.Enemy;
import Attack.Slow;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class SlowTower extends Tower {
    private ArrayList<Slow> activeSlows = new ArrayList<>();
    private BufferedImage towerImage1;
    private BufferedImage towerImage2;

    public SlowTower(
        int x,
        int y

) throws IOException {

    super(
            x,
            y,
            0,
            200,
            250,
            1000.0F,
            256
    );

    towerImage1 = ImageIO.read(new File("assets/towerDefense_tile180.png"));
    towerImage2 = ImageIO.read(new File("assets/towerDefense_tile249.png"));

}


    @Override
    public Boolean Attack() {
        if (!_inRange.isEmpty() && timeSinceLastShot >= this._fireRate) {
            Enemy enemy = _inRange.get(0);
            int centerX = this._x + 64 / 2;
            int centerY = this._y + 64 / 2;
            Slow slow = new Slow(0, enemy, centerX, centerY);
            activeSlows.add(slow);

            timeSinceLastShot = 0f;
            return true;
        }
        return false;
    }

    public void updateProjectiles(float deltaTime) {
        Iterator<Slow> iterator = activeSlows.iterator();
        while (iterator.hasNext()) {
            Slow slow = iterator.next();
            if (!slow.update(deltaTime)) {
                iterator.remove();
            }
        }
    }


    @Override
    public void draw(Graphics2D g2d) {
        // Draw the tower and all active lasers
        g2d.drawImage(towerImage1, this._x, this._y, null);
        g2d.drawImage(towerImage2, this._x, this._y, null);
        for (Slow slow : activeSlows) {
            slow.draw(g2d);
        }
    }
 
}
