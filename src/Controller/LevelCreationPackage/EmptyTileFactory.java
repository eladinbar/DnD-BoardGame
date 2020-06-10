package Controller.LevelCreationPackage;

import Model.TilePackage.EmptyTile;
import Model.TilePackage.Tile;

import java.awt.*;

public class EmptyTileFactory implements AbstractTileFactory {
    @Override
    public Tile getTile(char c, Point position) {
        return new EmptyTile(position);
    }
}
