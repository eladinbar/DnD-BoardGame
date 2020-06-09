package View;

import Model.*;

import java.util.List;

public class PlayerSelectionMenu implements GameInfo {
    private List<Warrior> warrioresList;
    private List<Rogue> roguesList;
    private List<Mage> magesList;
    private List<Hunter> huntersList;

    public PlayerSelectionMenu(){
        //use lambda expresion o the enums to create the  individual lists.
    }

    @Override
    public void printInfo() {
        System.out.println("Welcome to \"Game of Thrones Dungeon\". Get ready for your quest.");
        System.out.println("Choose Player: ");
        int characterMenuPosition = 1;
        for (Warrior w: warrioresList) {
            System.out.println(characterMenuPosition + ". " + w.describe());
            characterMenuPosition++;
        }
        for (Mage m: magesList) {
            System.out.println(characterMenuPosition + ". " + m.describe());
            characterMenuPosition++;
        }
        for (Rogue r: roguesList) {
            System.out.println(characterMenuPosition + ". " + r.describe());
            characterMenuPosition++;
        }
        for (Hunter h: huntersList) {
            System.out.println(characterMenuPosition + ". " + h.describe());
            characterMenuPosition++;
        }
    }

    public void SelectPlayer(char choice){

    }
}
