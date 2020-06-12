package Controller;

import Model.ANSIColors;
import Model.UnitPackage.PlayerPackage.*;
import View.PlayerSelectionMenu;
import java.util.List;
import java.util.Scanner;

public class CommandLineInterface {

    private GameController controller;
    private PlayerSelectionMenu selectionMenu;
    private List<String> levelsPath;
    private Player chosenPlayer;

    public CommandLineInterface(List<String> levelsPath) {
        controller = null;
        selectionMenu = new PlayerSelectionMenu();
        this.levelsPath = levelsPath;
        chosenPlayer = null;
    }

    public void start() {
        selectionMenu.printInfo();
        selectPlayer(receiveInput());
        while (chosenPlayer == null) {
            System.out.println("Invalid Selection. Please select again");
            selectPlayer(receiveInput());
        }
        controller = new GameController(chosenPlayer);
        controller.loadLevel(getNextLevelPath());
        play();
    }

    public void play() {
        while (controller.playerAlive()) {
            if (!controller.isEnemiesAlive() && hasNextLevel()) {
                controller.loadLevel(getNextLevelPath());
                continue;
            } else if(!controller.isEnemiesAlive() && !hasNextLevel()){
                System.out.println(ANSIColors.BRIGHT_GREEN + "You have cleared the dungeon.\ncongratulations!\nY-O-U  W-I-N!" + ANSIColors.RESET);
                break;
            }
           controller.round(getAction(receiveInput()));
        }
    }

    public ActionListInput getAction(Character c){
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

    public Character receiveInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next().charAt(0);
    }

    public boolean hasNextLevel() {
        return levelsPath.size() > 0;
    }

    public String getNextLevelPath() {
        String path = levelsPath.get(0);
        levelsPath.remove(0);
        return path;
    }

    public void selectPlayer(Character c) {
        for (Warriors w : Warriors.values()) {
            if (w.getMenuPosition() == (c.charValue() - 48)) {
                chosenPlayer = new Warrior(null, w);
                return;
            }
        }

        for (Mages m : Mages.values()) {
            if (m.getMenuPosition() == (c.charValue() - 48)) {
                chosenPlayer = new Mage(null, m);
                return;
            }
        }

        for (Rogues r : Rogues.values()) {
            if (r.getMenuPosition() == (c.charValue() - 48)) {
                chosenPlayer = new Rogue(null, r);
                return;
            }
        }

        for (Hunters h : Hunters.values()) {
            if (h.getMenuPosition() == (c.charValue() - 48)) {
                chosenPlayer = new Hunter(null, h);
                return;
            }
        }
    }
}
