package Model.UnitPackage;

import Model.TilePackage.Tile;
import Model.TilePackage.Visitor;

import java.awt.Point;

public abstract class Unit extends Tile implements Visitor {
    protected String name;
    protected Integer healthPool;
    protected Integer currentHealth;
    protected Integer attack;
    protected Integer defense;

    public Unit(Point position) {
        super(position);
    }

    public String getName() {
        //Returns the name of the unit. Use it to print the names upon combat
        //engagement on ability cast.
        throw new UnsupportedOperationException();
    }

    public String describe() {
        //returns full information of the current unit (don’t forget to
        //override this method in each subclass). Use it to print the information of each unit during
        //combat / on player’s turn.
        throw new UnsupportedOperationException();
    }
}
