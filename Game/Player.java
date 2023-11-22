package Game;

public class Player {

    private Integer _health;
    private Integer _money;
    private Integer _maxHealth;

    public Player(Integer health, Integer money){
        this._money = money;
        this._health = health;
        this._maxHealth = health;

    }

    public void setHealth(Integer health){
        this._health = health;
    }
    public Integer getHealth(){
        return this._health;
    }
    public Integer getMaxHealth(){
        return this._maxHealth;
    }

    public void setMoney(Integer money){
        this._money = money;
    }
    public Integer getMoney(){
        return this._money;
    }

    
}
