package Controller;

import Controller.LevelCreationPackage.LevelCreator;
import Model.UnitPackage.EnemyPackage.Enemy;
import Model.UnitPackage.PlayerPackage.Player;
import Model.UnitPackage.PlayerPackage.PlayerStatus;
import Model.UnitPackage.TickListener;
import View.CombatInfo;
import View.Level;

import java.util.ArrayList;
import java.util.List;

public class GameController implements TickManager{
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
        return currentPlayer.getPlayerStatus().equals(PlayerStatus.ALIVE);
    }

    private CombatInfo playerTurn(ActionListInput chosenAction){
        String result = currentPlayer.onPlayerTurn(currentDungeonLevel.getBoard().getLayout(), chosenAction, currentDungeonLevel.getEnemyList());
        CombatInfo playerTurnInfo = new CombatInfo(result);
        return playerTurnInfo;
    }

    private void enemiesTurn(){
        for (Enemy e : currentDungeonLevel.getEnemyList()) {
            String result = e.onEnemyTurn(currentDungeonLevel.getBoard().getLayout(), currentPlayer);
            CombatInfo enemyTurnInfo = new CombatInfo(result);
            enemyTurnInfo.printInfo();
        }
    }

    public void round(ActionListInput chosenAction){
        playerTurn(chosenAction).printInfo();
        enemiesTurn();
        notifyListeners();
        gameStatusPrint();
        levelTickCounter++;
    }

    private void resetTickCounter(){
        levelTickCounter = 0;
    }
}
