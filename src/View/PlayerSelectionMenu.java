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
        System.out.println("Welcome to " + ANSIColors.BRIGHT_YELLOW.value() + "\"Game of Thrones Dungeon\"" + ANSIColors.RESET.value());
        System.out.println("The Lannisters have been in hiding ever since the Mother of Dragons destroyed King's Landing.\n" +
                "In that time, a lady who claims to be Cersei Lannister, performed a ritual and brought back to life (or more accurately, back to undeath) the horrible Night King.\n" +
                "Together, they’re rebuilding an army to take the Seven Kingdoms and claim the Iron Throne.\n" +
                "\n" +
                "An intelligence report from lord Varis has arrived revealing the location of the base where Cersei and the Night King are building their army.\n" +
                "The need for a hero, who's willing to delve into their stronghold and take out the army and its leaders, is required!\n" +
                "\n" +
                "WHO WOULD YOU CHOOSE?\n");
        warriorsList.stream().forEach((x) -> System.out.println(x));
        magesList.stream().forEach((x) -> System.out.println(x));
        roguesList.stream().forEach((x) -> System.out.println(x));
        huntersList.stream().forEach((x) -> System.out.println(x));
    }
}
