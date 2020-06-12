package Controller.LevelCreationPackage;

import Model.TilePackage.EmptyTile;
import Model.TilePackage.Tile;
import Model.UnitPackage.EnemyPackage.*;

import java.awt.Point;

public class EnemyFactory implements AbstractTileFactory {

    @Override
    public Tile getTile(char c, Point position) {
        switch (c) {
            case 's':
                return new Minion(position, Minions.LANNISTER_SOLDIER);
            case 'k':
                return new Minion(position, Minions.LANNISTER_KNIGHT);
            case 'q':
                return new Minion(position, Minions.QUEENS_GUARD);
            case 'z':
                return new Minion(position, Minions.WRIGHT);
            case 'b':
                return new Minion(position, Minions.BEAR_WRIGHT);
            case 'g':
                return new Minion(position, Minions.GIANT_WRIGHT);
            case 'w':
                return new Minion(position, Minions.WHITE_WALKER);
            case 'M':
                return new Boss(position, Bosses.THE_MOUNTAIN);
            case 'C':
                return new Boss(position, Bosses.QUEEN_CERSEI);
            case 'K':
                return new Boss(position, Bosses.NIGHT_KING);
            case 'B':
                return new Trap(position, Traps.BONUS_TRAP);
            case 'Q':
                return new Trap(position, Traps.QUEENS_TRAP);
            case 'D':
                return new Trap(position, Traps.DEATH_TRAP);
            default:
                return new EmptyTile(position);
        }
    }
}
