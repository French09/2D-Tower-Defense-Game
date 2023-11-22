package Attack;

import Enemy.Enemy;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

public class Laser extends Attack {

    private Color color = Color.RED;
    private float thickness = 2.0f;
    private boolean active;
    private float activeTime = 0;

    public Laser(int damage, Enemy target, int startX, int startY) {
        super(damage, target, startX, startY);
        this.active = true;
    }

    @Override
    public boolean update(float deltaTime) {
        if (active) {

            activeTime += deltaTime; 

            if (activeTime >= 200) {
                if (super._target != null) {
                    dealDamage(super._damage, super._target);
                }
                active = false;
            }
        }
        return active;
    }

    public void draw(Graphics2D g2d) {
        if (active && _target != null) {
            int targetCenterX = (int) _target.getX() + 64 / 2;
            int targetCenterY = (int) _target.getY() + 64 / 2;
            g2d.setColor(color);
            g2d.setStroke(new BasicStroke(thickness));
            g2d.drawLine(startX, startY, targetCenterX, targetCenterY);
        }
    }

}