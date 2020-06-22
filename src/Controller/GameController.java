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

public class GameController implements TickManager {
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

    //add a listener to be notify when a tick\round has finished.
    @Override
    public boolean addListener(TickListener listener) {
        if(listeners.contains(listener))
            return false;
        else{
            listeners.add(listener);
            return true;
        }
    }

    //notify that a round has finished to listeners.
    @Override
    public void notifyListeners() {
        for (TickListener l: this.listeners) {
            l.onGameTick();
        }
        levelTickCounter++;
    }

    //loads the level according to @param levelPath.
    public void loadLevel(String levelPath){
        currentDungeonLevel = levelCreator.decipherLevel(levelPath, currentPlayer);
        listeners.add(currentPlayer);
        for (Enemy e : currentDungeonLevel.getEnemyList()) {
            addListener(e);
        }
        resetTickCounter();
        gameStatusPrint();
    }

    //returns true if the list of enemies of the current level is not empty.
    public boolean isEnemiesAlive(){
        return !currentDungeonLevel.getEnemyList().isEmpty();
    }

    private void gameStatusPrint(){
        currentDungeonLevel.getBoard().printInfo();
        System.out.println("\n" + currentPlayer.describe());
    }

    //return true if player status is ALIVE.
    public boolean playerAlive(){
        return currentPlayer.getPlayerStatus().equals(PlayerStatus.ALIVE);
    }

    //returns the result of the player turn.
    private CombatInfo playerTurn(ActionListInput chosenAction){
        String result = currentPlayer.onPlayerTurn(currentDungeonLevel.getBoard().getLayout(), chosenAction, currentDungeonLevel.getEnemyList());
        CombatInfo playerTurnInfo = new CombatInfo(result);
        return playerTurnInfo;
    }

    //preforming the enemy turns and prints the result.
    private void enemiesTurn(){
        for (Enemy e : currentDungeonLevel.getEnemyList()) {
            String result = e.onEnemyTurn(currentDungeonLevel.getBoard().getLayout(), currentPlayer);
            CombatInfo enemyTurnInfo = new CombatInfo(result);
            enemyTurnInfo.printInfo();
        }
    }

    /* sets the flow of the game.
    * flow:
    * player takes a turn.
    * enemies preform their turns.
    * notifing all listeners that a tick was complete.
    * */
    public void round(ActionListInput chosenAction){
        playerTurn(chosenAction).printInfo();
        enemiesTurn();
        notifyListeners();
        gameStatusPrint();
        levelTickCounter++;
    }

    //reset the tick counter for the level
    private void resetTickCounter(){
        levelTickCounter = 0;
    }
}
