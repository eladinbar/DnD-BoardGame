package Model.TilePackage;

import Model.UnitPackage.Visitor;

public interface Visited {
    void accept(Visitor visitor);
}
