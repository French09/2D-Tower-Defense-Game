package Attack;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Enemy.Enemy;


public class Explosion extends Attack {
    private BufferedImage image;
    private Float speed=1.0f;
    private Integer range=100;
    private int x, y;
    private boolean active;


    public Explosion(int damage, Enemy target, int startX, int startY) {
        super(damage, target, startX, startY);
        this.active = true;
        this.x = startX;
        this.y = startY;
        try {
            image = ImageIO.read(new File("assets/towerDefense_tile251.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading explosion image: " + e.getMessage());
        }
        if (image == null) {
            System.out.println("Failed to load projectile image");
        }
    }


    @Override
        public boolean update(float deltaTime, ArrayList<Enemy> enemies) {
        if (!active){
            System.out.println("Explosion not active");
            return false;
        } 

        float dx = _target.getX() - x;
        float dy = _target.getY() - y;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        float vx = (dx / distance) * speed * deltaTime;
        float vy = (dy / distance) * speed * deltaTime;

        x += vx;
        y += vy;
        if (distance < 10) {
            for (Enemy enemy : enemies) {
                float dxtarget = enemy.getX() - x;  
                float dytarget = enemy.getY() - y;
                float distanceTarget = (float) Math.sqrt(dxtarget * dxtarget + dytarget * dytarget);

                if (distanceTarget <= range) {
                    dealDamage(super._damage, enemy);
                }
            }
            active = false;
        }

        return active;
    }

    public void draw(Graphics2D g2d) {
        if (!active) return;
        g2d.drawImage(image, x, y, null);

    }

}
