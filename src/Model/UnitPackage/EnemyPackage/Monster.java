package Model.UnitPackage.EnemyPackage;

import Model.TilePackage.EmptyTile;
import Model.TilePackage.Tile;
import Model.UnitPackage.Visitor;
import Model.TilePackage.Wall;
import Model.UnitPackage.PlayerPackage.Player;

import java.awt.Point;

public abstract class Monster extends Enemy implements Visitor {
    protected Integer visionRange;

    public Monster(Point position) {
        super(position);
    }

    public void interact(Tile tile) {
        tile.accept(this);
    }

    @Override
    public void visit(Enemy enemy) {
        //Do nothing
    }

    @Override
    public void visit(Player player) {
        this.engage(player);
    }

    @Override
    public void visit(Wall wall) {
        //Do nothing
    }

    @Override
    public void visit(EmptyTile emptyTile) {
        this.setPosition(emptyTile.getPosition());
    }

    protected void moveLeft() {
        this.position.x -= 1;
    }

    protected void moveRight() {
        this.position.x += 1;
    }

    protected void moveUp() {
        this.position.y += 1;
    }

    protected void moveDown() {
        this.position.y -= 1;
    }
}
