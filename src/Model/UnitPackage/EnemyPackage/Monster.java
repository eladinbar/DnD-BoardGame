package Model.UnitPackage.EnemyPackage;

import java.awt.Point;

public abstract class Monster extends Enemy {
    protected Integer visionRange;

    public Monster(Point position) {
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
