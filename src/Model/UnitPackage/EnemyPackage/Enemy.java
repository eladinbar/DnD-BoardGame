package Model.UnitPackage.EnemyPackage;

import Model.UnitPackage.Unit;

import java.awt.Point;

public abstract class Enemy extends Unit {
    protected Integer experienceValue;

    public Enemy(Point position) {
        super(position);
    }

    @Override
    public String describe() {
        //returns full information of the current unit (don’t forget to
        //override this method in each subclass). Use it to print the information of each unit during
        //combat / on player’s turn.
        throw new UnsupportedOperationException();
    }
}
