package Attack;

import java.util.ArrayList;

import Enemy.Enemy;

public abstract class Attack implements IAttack {

    private Float _speed;
    protected int _damage;
    protected Enemy _target;
    private Float _hitbox;
    protected int startX;
    protected int startY;

    protected Attack(
            int damage, Enemy target, int startX, int startY
    ) {
        this._damage = damage;
        this._target = target;
        this.startX = startX;
        this.startY = startY;
    }

    public void dealDamage(Integer damage, Enemy target) {
        target.setHealth(target.getHealth() - damage);
    }

    public boolean update(float deltaTime) {
        return true;
    }

   public boolean update(float deltaTime, ArrayList<Enemy> enemies) {
        return true;
    }

    public Float getSpeed() {
        return this._speed;
    }

    public int getDamage() {
        return this._damage;
    }

    public Enemy getTarget() {
        return this._target;
    }

    public void getSpeed(Float speed) {
        this._speed = speed;
    }

    public void getDamage(int damage) {
        this._damage = damage;
    }

    public void getTarget(Enemy target) {
        this._target = target;
    }

}
