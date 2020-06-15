package View;

import Model.ANSIColors;
import Model.UnitPackage.PlayerPackage.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerSelectionMenu implements GameInfo {
    private List<String> warriorsList;
    private List<String> magesList;
    private List<String> roguesList;
    private List<String> huntersList;

    public PlayerSelectionMenu() {
        warriorsList = Arrays.stream(Warriors.values()).
                map((character) -> ANSIColors.RED.value() + character.getMenuPosition() + ". " + new Warrior(null, character).describe() + ANSIColors.RESET.value()).collect(Collectors.toList());
        magesList = Arrays.stream(Mages.values()).
                map((character) -> ANSIColors.BRIGHT_BLUE.value() + character.getMenuPosition() + ". " + new Mage(null, character).describe() + ANSIColors.RESET.value()).collect(Collectors.toList());
        roguesList = Arrays.stream(Rogues.values()).
                map((character) -> ANSIColors.BRIGHT_BLACK.value() + character.getMenuPosition() + ". " + new Rogue(null, character).describe() + ANSIColors.RESET.value()).collect(Collectors.toList());
        huntersList = Arrays.stream(Hunters.values()).
                map((character) -> ANSIColors.BRIGHT_GREEN.value() + character.getMenuPosition() + ". " + new Hunter(null, character).describe() + ANSIColors.RESET.value()).collect(Collectors.toList());
    }

    @Override
    public void printInfo() {
        System.out.println("Welcome to \"Game of Thrones Dungeon\"");
        System.out.println("TThe Lannisters have been in hiding ever since the Mother of Dragons destroyed King's Landing.\n" +
                "In that time a lady who claims to be Cerci Lannister, preformed a ritual and brought back to life (or more accurate back to undead) the horrible Night King.\n" +
                "Together they’re rebuilding an army to take the Seven Kingdoms and claim the Iron Throne.\n" +
                "\n" +
                "a Intelligence report from lord Varis has Arrived reviling the location of the base where cerci and the Night King  are building their army.\n" +
                "The need for a Hero who's willing to go to the base and take out the army and their Leaders.\n" +
                "\n" +
                "WHO WOULD YOU CHOOSE?");
        warriorsList.stream().forEach((x) -> System.out.println(x));
        magesList.stream().forEach((x) -> System.out.println(x));
        roguesList.stream().forEach((x) -> System.out.println(x));
        huntersList.stream().forEach((x) -> System.out.println(x));
    }
}
