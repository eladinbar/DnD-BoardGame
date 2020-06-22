package Controller;

import Controller.LevelCreationPackage.PlayerFactory;
import Model.ANSIColors;
import Model.UnitPackage.PlayerPackage.*;
import View.PlayerSelectionMenu;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandLineInterface {

    private GameController controller;
    private PlayerSelectionMenu selectionMenu;
    private List<String> levelsPath;
    private Player chosenPlayer;

    public CommandLineInterface(String folderPath) {
        controller = null;
        selectionMenu = new PlayerSelectionMenu();
        this.levelsPath = getLevelPaths(folderPath);
        chosenPlayer = null;
    }

    //starts the game with the player selection menu and sets the chosen player.
    //creates the gameController and loads the levels to the controller.
    public void start() {
        selectionMenu.printInfo();
        selectPlayer(receiveInput());
        while (chosenPlayer == null) {
            System.out.println("Invalid Selection. Please select again");
            selectPlayer(receiveInput());
        }
        controller = new GameController(chosenPlayer);
        controller.loadLevel(getNextLevelPath());
        
    }

    /* controls the end points of the game.
    * endpoints: player is dead, no enemy is alive and there is more levels, finished all levels.
    */
    public void play() {
        while (controller.playerAlive()) {
            if (!controller.isEnemiesAlive() && hasNextLevel()) {
                //finished the level and there is another level to load
                System.out.println("\nContinue To Next Level. \nBe Prepared!\n");
                controller.loadLevel(getNextLevelPath());
                continue;
            } else if(!controller.isEnemiesAlive() && !hasNextLevel()){
                //finished the game and displays the victory message.
                System.out.println(ANSIColors.BRIGHT_GREEN.value() + "You have cleared the dungeon.\nCongratulations!\nY-O-U  W-I-N!" + ANSIColors.RESET.value());
                break;
            }
            //continues the game flow.
           controller.round(getAction(receiveInput()));
        }
        if(!controller.playerAlive())
            //player died and displays the dead message
            System.out.println(ANSIColors.BRIGHT_MAGENTA.value() + "Y-O-U  A-R-E  D-E-A-D !! " + ANSIColors.RESET.value());
    }

    //returns the wanted action according to the key was pressed.
    private ActionListInput getAction(Character c){
        switch (Character.toLowerCase(c)){
            case 'a':
                return ActionListInput.Left;
            case'd':
                return ActionListInput.Right;
            case 'w':
                return ActionListInput.Up;
            case's':
                return ActionListInput.Down;
            case'e':
                return ActionListInput.CastAbility;
            default:
                return ActionListInput.Stay;
        }
    }

    //a wrapper method for receiving input from Scanner.
    private Character receiveInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next().charAt(0);
    }

    //returns whether the is more levels.
    private boolean hasNextLevel() {
        return levelsPath.size() > 0;
    }

    //returns the next level path if there is left.
    private String getNextLevelPath() {
        String path = levelsPath.get(0);
        levelsPath.remove(0);
        return path;
    }

    //delegates the creation to PlayerFactory.
    private void selectPlayer(Character c) {
        chosenPlayer = (Player) new PlayerFactory().getTile(c,null);
    }

    //translate the folder of the level to a list of individual level paths.
    private ArrayList<String> getLevelPaths(String folderPath){
        File levelFolder = new File(folderPath);
        String[] folderContent = levelFolder.list();
        ArrayList<String> levelsPath = new ArrayList<>();
        for(int i = 0; i< folderContent.length; i++){
            levelsPath.add(folderPath + "\\" +folderContent[i]);
        }
        return levelsPath;
    }
}
