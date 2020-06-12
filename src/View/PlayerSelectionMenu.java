package View;

import Model.ANSIColors;
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
                map((character) -> ANSIColors.RED + "" + character.getMenuPosition() + ". " + new Warrior(null, character).describe() + ANSIColors.RESET).collect(Collectors.toList());
        roguesList = Arrays.stream(Rogues.values()).
                map((character) -> ANSIColors.BRIGHT_BLACK + "" + character.getMenuPosition() + ". " + new Rogue(null, character).describe() + ANSIColors.RESET).collect(Collectors.toList());
        magesList = Arrays.stream(Mages.values()).
                map((character) -> ANSIColors.BRIGHT_BLUE + "" + character.getMenuPosition() + ". " + new Mage(null, character).describe() + ANSIColors.RESET).collect(Collectors.toList());
        huntersList = Arrays.stream(Hunters.values()).
                map((character) -> ANSIColors.BRIGHT_GREEN + "" + character.getMenuPosition() + ". " + new Hunter(null, character).describe() + ANSIColors.RESET).collect(Collectors.toList());
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
