package Enemy;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Plane extends Enemy {
    private BufferedImage tilePlane;
    public Plane(Integer level,
    Integer health,
    Integer damage,
    Float speed,
    Integer revenue,
    int[][] path,
    int pathIndex
    ){
        super(level, health, damage, speed, revenue, path, pathIndex);
        this._revenue = revenue;
        this._damage = damage;
        if (path != null && path.length > 0) {
            this._x = path[0][0] * 64.0f; // Change to float
            this._y = path[0][1] * 64.0f; // Change to float
        }
        try {
            tilePlane = ImageIO.read(new File("assets/towerDefense_tile270.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void draw(Graphics2D g2d) {
        if (tilePlane != null) {
            AffineTransform rotation = AffineTransform.getTranslateInstance(_x, _y);
            rotation.rotate(rotationAngle, tilePlane.getWidth() / 2.0, tilePlane.getHeight() / 2.0);
            g2d.drawImage(tilePlane, rotation, null);
        }
    }

}
