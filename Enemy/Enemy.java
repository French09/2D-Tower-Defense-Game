package Enemy;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Enemy {
    protected static Integer id = 0;
    protected float _x; // Change the type to float
    protected float _y; // Change the type to float
    protected Integer _id;
    protected Integer _level;
    protected Integer _health;
    protected Integer _damage;
    protected Integer _maxHealth;
    protected Float _maxSpeed;
    protected Float _speed;
    protected Integer _revenue;
    protected int[][] path;
    protected int pathIndex;
    protected double rotationAngle = 0;

    public Enemy(Integer level,
            Integer health,
            Integer damage,
            Float speed,
            Integer revenue,
            int[][] path,
            int pathIndex
            ) {

        this._id = id;
        id += 1;
        this._level = level;
        this._health = health;
        this._speed = speed;
        this.path=path;
        this.pathIndex=pathIndex;
        this._maxHealth = health;
        this._maxSpeed = speed;

        if (path != null && path.length > 0) {
            this._x = path[0][0] * 64.0f; // Change to float
            this._y = path[0][1] * 64.0f; // Change to float
        }

    }

    public void update() {
        if (pathIndex < path.length - 1) {
            float targetX = path[pathIndex + 1][0] * 64.0f; // Change to float
            float targetY = path[pathIndex + 1][1] * 64.0f; // Change to float

            calculateRotationAngle(targetX, targetY);

            float dx = targetX - _x;
            float dy = targetY - _y;
            float distance = (float) Math.sqrt(dx * dx + dy * dy);

            if (distance > 0) {
                float ratio = _speed / distance;
                float moveX = ratio * dx;
                float moveY = ratio * dy;

                // Update positions
                _x += moveX;
                _y += moveY;
            }

            // Check if we are close enough to snap to the target
            if (Math.abs(_x - targetX) <= 1.0f && Math.abs(_y - targetY) <= 1.0f) {
                _x = targetX;
                _y = targetY;
                pathIndex++;
            }
        }
    }

    public boolean hasReachedEnd() {
        if (pathIndex >= path.length - 1) {
            return true;
        }
        return false;
    }

    protected void calculateRotationAngle(float targetX, float targetY) {
        float dx = targetX - _x;
        float dy = targetY - _y;
        rotationAngle = Math.atan2(dy, dx);
    }


    public void drawHealthBar(Graphics2D g2d) {
        // Constants for the health bar size
        int barWidth = 40;
        int barHeight = 5;
        int barX = (int) (this.getX()+15); // Center the bar above the enemy
        int barY = (int) (this.getY()); // Position the bar above the enemy

        // Calculate health percentage
        float healthPercent = (float) this.getHealth() / this.getMaxHealth();

        // Draw background of the health bar (e.g., red for empty part)
        g2d.setColor(Color.RED);
        g2d.fillRect(barX, barY, barWidth, barHeight);

        // Draw the foreground of the health bar (e.g., green for current health)
        g2d.setColor(Color.GREEN);
        g2d.fillRect(barX, barY, (int) (barWidth * healthPercent), barHeight);
    }

    public abstract void draw(Graphics2D g2d);

    public Integer getId() {
        return this._id;

    };

    public float getX() { // Change the return type to float
        return this._x;
    }

    public float getY() { // Change the return type to float
        return this._y;
    }

    public Integer getHealth() {
        return this._health;
    };

    public Integer getMaxHealth() {
        return this._maxHealth;
    };


    public Integer getLevel() {
        return this._level;
    };

    public Integer getDamage() {
        return this._damage;
    };

    public Float getSpeed() {
        return this._speed;
    };
    public Float getMaxSpeed() {
        return this._maxSpeed;
    };


    public Integer getRevenue(){
        return this._revenue;
    }

    public void setHealth(Integer health) {
        if (health <= 0) {
            this._health = 0;

        } else {
            this._health = health;
        }
    };

    public void setLevel(Integer level) {
        this._level = level;
    };

    public void setSpeed(Float speed) {
        this._speed = speed;
    };
}
