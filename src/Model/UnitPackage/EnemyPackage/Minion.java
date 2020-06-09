package Model.UnitPackage.EnemyPackage;

import Model.UnitPackage.EnemyPackage.Monster;

import java.awt.*;

public class Minion extends Monster {
    public Minion(Minions minion, Point position) {
        super.position = position;
        //all other stuff
    }

    @Override
    public String describe() {
        //returns full information of the current unit (don’t forget to
        //override this method in each subclass). Use it to print the information of each unit during
        //combat / on player’s turn.
        throw new UnsupportedOperationException();
    }
}
