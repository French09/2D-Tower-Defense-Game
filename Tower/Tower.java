package Tower;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Enemy.Enemy;

public abstract class Tower implements ITower {

    // id
    protected static Integer id = 0;
    protected Integer _id;

    // position

    protected int _x;
    protected int _y;
    protected Integer _level;
    protected Enemy _target;
    protected int _damage;
    protected Integer _cost;
    protected Integer _upgradeCost;
    protected Float _fireRate;
    protected int _range;
    protected ArrayList<Enemy> _inRange = new ArrayList<>();
    protected Float timeSinceLastShot = 0f;

    protected BufferedImage _projectileImage;

    protected Tower(
            int x,
            int y,
            int damage,
            Integer cost,
            Integer upgradeCost,
            Float fireRate,
            int range
            ) {
                this._x = x;
                this._y = y;
                this._damage = damage;
                this._cost = cost;
                this._upgradeCost = upgradeCost;
                this._fireRate = fireRate;
                this._range = range;
                this._id = id;
                id += 1;
            }


        public void update(ArrayList<Enemy> allEnemies, float deltaTime) {
            _inRange.clear(); // Clear the list for fresh calculation
            timeSinceLastShot += deltaTime;

            for (Enemy enemy : allEnemies) {
                if (isInRange(enemy)) {
                    _inRange.add(enemy);
                }
            }
        }

        

        public boolean isInRange(Enemy enemy) {
            float dx = this._x - enemy.getX();
            float dy = this._y - enemy.getY();
            return Math.sqrt(dx * dx + dy * dy) <= this._range;
        }

   




    // getters
    public Integer getId() {
        return this._id;
    };

    public int getX() {
        return this._x;
    }
    public int getY() {
        return this._y;
    }

    public Integer getLevel() {
        return this._level;
    };

    public int getDamage() {
        return this._damage;
    };

    public Integer getCost() {
        return this._cost;
    };

    public Integer getUpgradeCost() {
        return this._upgradeCost;
    }

    public Float getFireRate() {
        return this._fireRate;
    };

    public int getRange() {
        return this._range;
    };

    public ArrayList<Enemy> getInrange() {
        return this._inRange;
    };

    // setters

    public void setLevel(Integer level) {
        this._level = level;
    };

    public void setDamage(int damage) {
        this._damage = damage;
    };

    public void setCost(Integer cost) {
        this._cost = cost;
    };

    public void setUpgradeCost(Integer upgradeCost) {
        this._upgradeCost = upgradeCost;
    }

    public void setFireRate(Float fireRate) {
        this._fireRate = fireRate;
    };

    public void setRange(int range) {
        this._range = range;
    };

    public void addInRange(Enemy enemy) {
        this._inRange.add(enemy);
    };

    public void removeInRange(Enemy enemy) {
        this._inRange.remove(enemy);
    }

   

    public Boolean Attack() {
        return false;
    }


    public void draw(Graphics2D g2d) {
    }

}
