package Tower;
import java.util.ArrayList;
import Enemy.Enemy;

public interface ITower{

    //getters
    Integer getId();
    Integer getLevel();
    int getDamage();
    Integer getCost();
    Integer getUpgradeCost();
    Float getFireRate();
    int getRange();
    ArrayList<Enemy> getInrange();

    //setters
    void setLevel(Integer level);
    void setDamage(int damage);
    void setCost(Integer cost);
    void setUpgradeCost(Integer upgradeCost);
    void setFireRate(Float fireRate);
    void setRange(int range);
    void addInRange(Enemy enemy);
    void removeInRange(Enemy enemy);

    //methods
    Boolean Attack();

}