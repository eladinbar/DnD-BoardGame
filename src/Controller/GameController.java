package Controller;
import Model.UnitPackage.EnemyPackage.*;
import Model.UnitPackage.PlayerPackage.*;
import Model.TickListener;
import View.*;

import java.util.ArrayList;
import java.util.List;

public class GameController implements TickManager, DeathObserver{
    private GameBoard currentDungeonLevel;
    private Player currentPlayer;
    private List<Enemy> enemiesInPlay;

    private int levelTickCounter = 0;
    private List<TickListener> listeners;

    public GameController(GameBoard level, Player chosenPlayer){
        this.currentDungeonLevel = level;
        this.currentPlayer = chosenPlayer;
        this.enemiesInPlay = new ArrayList<>();
        this.listeners = new ArrayList<>();
    }


    @Override
    public void update(Enemy enemy) {

    }

    @Override
    public void update(Player player) {

    }

    @Override
    public boolean addListener(TickListener listener) {
        if(listeners.contains(listener))
            return false;
        else{
            listeners.add(listener);
            return true;
        }
    }

    @Override
    public void notifyListeners() {
        for (TickListener l: this.listeners) {
            l.onGameTick();
        }
        levelTickCounter++;
    }

    protected void addEnemy(Enemy e){
        this.enemiesInPlay.add(e);
    }
}
