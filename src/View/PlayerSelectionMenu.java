package View;

import Model.UnitPackage.PlayerPackage.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerSelectionMenu implements GameInfo {
    private List<String> warriorsList;
    private List<String> roguesList;
    private List<String> magesList;
    private List<String> huntersList;

    public PlayerSelectionMenu() {
        warriorsList = Arrays.stream(Warriors.values()).
                map((character) -> character.getMenuPosition() + ". " + new Warrior(null, character).describe()).collect(Collectors.toList());
        roguesList = Arrays.stream(Rogues.values()).
                map((character) -> character.getMenuPosition() + ". " + new Rogue(null, character).describe()).collect(Collectors.toList());
        magesList = Arrays.stream(Mages.values()).
                map((character) -> character.getMenuPosition() + ". " + new Mage(null, character).describe()).collect(Collectors.toList());
        huntersList = Arrays.stream(Hunters.values()).
                map((character) -> character.getMenuPosition() + ". " + new Hunter(null, character).describe()).collect(Collectors.toList());
    }

    @Override
    public void printInfo() {
        System.out.println("Welcome to \"Game of Thrones Dungeon\". Get ready for your quest.");
        System.out.println("Choose Player: ");
        warriorsList.stream().forEach((x) -> System.out.println(x));
        magesList.stream().forEach((x) -> System.out.println(x));
        roguesList.stream().forEach((x) -> System.out.println(x));
        huntersList.stream().forEach((x) -> System.out.println(x));
    }


}
