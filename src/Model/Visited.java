package Model;

import Model.TilePackage.*;
import Model.UnitPackage.Unit;

public interface Visited {
    void visit(Unit unit);
    void visit(Wall wall);
    void visit(EmptyTile emptyTile);
}
