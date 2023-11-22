package Enemy;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tank extends Enemy {
    private BufferedImage tileTank1;
    private BufferedImage tileTank2;

    public Tank(Integer level, Integer health, Integer damage, Float speed, Integer revenue, int[][] path, int pathIndex) {
        super(level, health, damage, speed, revenue, path, pathIndex);
        this._revenue = revenue;
        this._damage = damage;
        if (path != null && path.length > 0) {
            this._x = path[0][0] * 64.0f; // Change to float
            this._y = path[0][1] * 64.0f; // Change to float
        }
        try {
            tileTank1 = ImageIO.read(new File("assets/towerDefense_tile268.png"));
            tileTank2 = ImageIO.read(new File("assets/towerDefense_tile291.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        super.update(); // Call the update method from the Enemy class
        // Add any additional update logic specific to Buggy here
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (tileTank1 != null) {
            AffineTransform rotation = AffineTransform.getTranslateInstance(_x, _y);
            rotation.rotate(rotationAngle, tileTank1.getWidth() / 2.0, tileTank1.getHeight() / 2.0);
            g2d.drawImage(tileTank1, rotation, null);
        }
        if (tileTank2 != null) {
            AffineTransform rotation = AffineTransform.getTranslateInstance(_x, _y);
            rotation.rotate(rotationAngle, tileTank2.getWidth() / 2.0, tileTank2.getHeight() / 2.0);
            g2d.drawImage(tileTank2, rotation, null);
        }
    }
}

