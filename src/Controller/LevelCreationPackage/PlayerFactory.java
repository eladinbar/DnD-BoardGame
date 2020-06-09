package Controller.LevelCreationPackage;

import Model.TilePackage.Tile;
import Model.UnitPackage.PlayerPackage.*;

import java.awt.*;

public class PlayerFactory implements AbstractTileFactory {
    @Override
    public Tile getTile(char c, Point position) {
        Player selectedPlayer;
        for (Warriors w: Warriors.values()) {
            if(w.getMenuPosition()==(Character.valueOf(c)-48)){
                selectedPlayer = new Warrior(w, position);
                return selectedPlayer;
            }
        }
        for (Mages m: Mages.values()) {
            if(m.getMenuPosition()==(Character.valueOf(c)-48)){
                selectedPlayer = new Mage(m, position);
                return selectedPlayer;
            }
        }
        for (Rogues r: Rogues.values()) {
            if(r.getMenuPosition()==(Character.valueOf(c)-48)){
                selectedPlayer = new Rogue(r, position);
                return selectedPlayer;
            }
        }
        for (Hunters h : Hunters.values()) {
            if(h.getMenuPosition()==(Character.valueOf(c)-48)){
                selectedPlayer = new Hunter(h, position);
                return selectedPlayer;
            }
        }

    }
}
