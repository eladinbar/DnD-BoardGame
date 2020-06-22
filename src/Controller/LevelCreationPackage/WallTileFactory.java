package Controller.LevelCreationPackage;

import Model.TilePackage.Tile;
import Model.TilePackage.Wall;

import java.awt.Point;

public class WallTileFactory implements AbstractTileFactory {
    //creates a Wall tile.
    @Override
    public Tile getTile(char c, Point position) {
        return new Wall(position);
    }
}
