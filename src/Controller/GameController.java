package Controller;
import Controller.LevelCreationPackage.LevelCreator;
import Model.UnitPackage.EnemyPackage.*;
import Model.UnitPackage.PlayerPackage.*;
import Model.TickListener;
import View.*;
import View.Level;

import java.util.ArrayList;
import java.util.List;

public class GameController implements TickManager, DeathObserver{
    private Level currentDungeonLevel;
    private Player currentPlayer;
    private LevelCreator levelCreator;

    private int levelTickCounter;
    private List<TickListener> listeners;

    public GameController(Player chosenPlayer){
        this.levelCreator = new LevelCreator();
        this.currentDungeonLevel = null;
        this.currentPlayer = chosenPlayer;
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

    public void loadLevel(String levelPath){
        currentDungeonLevel = levelCreator.decipherLevel(levelPath, currentPlayer);
        listeners.add(currentPlayer);
        for (Enemy e : currentDungeonLevel.getEnemyList()) {
            addListener(e);
        }
        resetTickCounter();
        gameStatusPrint();
    }

    public boolean isEnemiesAlive(){
        return !currentDungeonLevel.getEnemyList().isEmpty();
    }

    private void gameStatusPrint(){
        currentDungeonLevel.getBoard().printInfo();
        System.out.println(currentPlayer.describe());
    }

    public boolean playerAlive(){
        return true;
    }

    private void playerTurn(ActionListInput chosenAction){
        currentPlayer.onPlayerTurn(currentDungeonLevel.getBoard().getLayout(), chosenAction);
    }

    private void enemiesTurn(){
        for (Enemy e : currentDungeonLevel.getEnemyList()) {
            e.onEnemyTurn(currentDungeonLevel.getBoard().getLayout(), currentPlayer);
        }
    }

    public void round(ActionListInput chosenAction){
        playerTurn(chosenAction);
        enemiesTurn();
        notifyListeners();
        gameStatusPrint();
        levelTickCounter++;
    }

    private void resetTickCounter(){
        levelTickCounter = 0;
    }


}
