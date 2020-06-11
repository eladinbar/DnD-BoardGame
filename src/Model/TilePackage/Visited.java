package Model.TilePackage;

import Model.UnitPackage.Visitor;

public interface Visited {
    String accept(Visitor visitor);
}
