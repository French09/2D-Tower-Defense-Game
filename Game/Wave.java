package Game;

import java.util.ArrayList;

import Enemy.Enemy;

public class Wave {

    public ArrayList<Enemy> _waveA = new ArrayList<>();
    public ArrayList<Enemy> _waveB = new ArrayList<>();
    public ArrayList<Float> _delaysA = new ArrayList<>();
    public ArrayList<Float> _delaysB = new ArrayList<>();
    
    public Wave() {
    }

    public void spawnEnemies(float currentTime, ArrayList<Enemy> enemies) {
        int i = 0;
        while (i < _waveA.size()) {
            if (currentTime >= _delaysA.get(i)) {
                enemies.add(_waveA.remove(i));
                _delaysA.remove(i);
            } else {
                i++;
            }
        }

        i = 0;
        while (i < _waveB.size()) {
            if (currentTime >= _delaysB.get(i)) {
                enemies.add(_waveB.remove(i));
                _delaysB.remove(i);
            } else {
                i++;
            }
        }
    }

    public void addA(Enemy enemy, Float time) {
        this._waveA.add(enemy);
        this._delaysA.add(time);
    }
    public void addB(Enemy enemy, Float time){
        this._waveB.add(enemy);
        this._delaysB.add(time);
    }

    public void removeA(Enemy enemy){
        this._waveA.remove(enemy);
    }
    public void removeB(Enemy enemy){
        this._waveB.remove(enemy);
    }

    public ArrayList<Enemy> getWaveA(){
        return this._waveA;
    }
    public ArrayList<Enemy> getWaveB(){
        return this._waveB;
    }

}
