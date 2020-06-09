package Controller.LevelCreationPackage;

import Model.TilePackage.Tile;
import Model.TilePackage.Wall;

import java.awt.*;

public class WallTileFactory implements AbstractTileFactory {

    @Override
    public Tile getTile(char c, Point position) {
        return new Wall(position);
    }
}
