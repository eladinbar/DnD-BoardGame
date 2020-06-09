package Controller;

import Controller.LevelCreationPackage.LevelCreator;
import Model.UnitPackage.PlayerPackage.Player;
import View.GameBoard;
import View.PlayerSelectionMenu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandLineInterface {

    private GameController controller;
    private PlayerSelectionMenu selectionMenu;
    private List<Level> levels;
    private LevelCreator levelCreator;
    private Player chosenPlayer;

    public CommandLineInterface(){
        controller = null;
        selectionMenu = new PlayerSelectionMenu();
        levels = new ArrayList<>();
        levelCreator = new LevelCreator();
    }

    public void decipherLevels(String... rawLevelsPath){

        for (int i = 0; i < rawLevelsPath.length; i++) {
            List<String> levelLines = readAllLines(rawLevelsPath[i]);
            int boardLength = levelLines.get(0).length();
            int boardWidth = levelLines.size();
            GameBoard constructedGameBoard = new GameBoard((levelCreator.createLevel(levelLines,boardLength,boardWidth)),boardLength,boardWidth);
            Level newLevel = new Level(constructedGameBoard,levelCreator.getPlayerPosition());
            levels.add(newLevel);
        }
    }

    private List<String> readAllLines(String path) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch(IOException ex){
            System.out.println("something went wrong with reading the file. Please restart the program\n" + ex.getMessage() + "\n" + ex.getStackTrace());
        }
        return lines;
    }

    public void startGame(){
        controller = new GameController()
    }

    public void selectPlayer(char c){

    }

}
