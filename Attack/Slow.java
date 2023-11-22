package Attack;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Enemy.Enemy;

public class Slow extends Attack {
    private BufferedImage image;
    private Float slow=0.5f;
    private Float speed=1.0f;
    private int x, y;
    private boolean active;


    public Slow(int damage, Enemy target, int startX, int startY){

        super(damage, target, startX, startY);
        this.active = true;
        this.x = startX;
        this.y = startY;
        try {
            image = ImageIO.read(new File("assets/towerDefense_tile272.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (image == null) {
            System.out.println("Failed to load projectile image");
        }       
    }


    @Override
    public boolean update(float deltaTime) {
        if (!active) return false;

        float dx = _target.getX() - x;
        float dy = _target.getY() - y;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        float vx = (dx / distance) * speed * deltaTime;
        float vy = (dy / distance) * speed * deltaTime;

        x += vx;
        y += vy;


        if (distance < 10) {
            applySlow(_target);
            active = false;
        }

        return active;
    }

    public void applySlow(Enemy target){
        target.setSpeed(target.getMaxSpeed() * slow);
    }

    public void draw(Graphics2D g2d) {
        if (!active) return;
        g2d.drawImage(image, x, y, null);
    }

}
