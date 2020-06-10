package Controller.LevelCreationPackage;
import Model.TilePackage.Tile;
import java.awt.*;

public interface AbstractTileFactory {

    Tile getTile(char c, Point position);
}
