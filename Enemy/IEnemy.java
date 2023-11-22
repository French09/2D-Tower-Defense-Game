package Enemy;

public interface IEnemy {
    // Integer _id;


    Integer getId();
    Integer getHealth();
    Integer getLevel();
    Integer getDamage();
    Float getSpeed();
    Integer getRevenue();


    // setters

    void setLevel(Integer Level);
    void setHealth(Integer health);
    void setDamage(Integer damage);
    void setSpeed(Float speed);

}
