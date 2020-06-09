package Controller.LevelCreationPackage;

import Model.TilePackage.EmptyTile;
import Model.TilePackage.Tile;
import Model.UnitPackage.EnemyPackage.*;

import java.awt.*;

public class EnemyFactory implements AbstractTileFactory {

    @Override
    public Tile getTile(char c, Point position) {
        switch (c){
            case 's':
                return new Minion(Minions.LANNISTER_SOLDIER, position);
            case 'k':
                return new Minion(Minions.LANNISTER_KNIGHT,position);
            case 'q':
                return new Minion(Minions.QUEENS_GUARD,position);
            case 'z':
                return new Minion(Minions.WIRGHT, position);
            case 'b':
                return new Minion(Minions.BEAR_WRIGHT, position);
            case 'g':
                return new Minion(Minions.GIANT_WRIGHT, position);
            case 'w':
                return new Minion(Minions.WHITE_WALKER, position);
            case 'M':
                return new Boss(Bosses.THE_MOUNTAIN, position);
            case 'C':
                return new Boss(Bosses.QUEEN_CERSEI, position);
            case 'K':
                return new Boss(Bosses.NIGHT_KING, position);
            case'B':
                return new Trap(Traps.BONUS_TRAP, position);
            case'Q':
                return new Trap(Traps.QUEENS_TRAP, position);
            case'D':
                return new Trap(Traps.DEATH_TRAP, position);
            default:
                return new EmptyTile(position);
        }
    }
}
