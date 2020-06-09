package Model.TilePackage;

import Model.UnitPackage.Unit;

public interface Visitor {
    void visit(Unit unit);
    void visit(Wall wall);
    void visit(EmptyTile emptyTile);
}
