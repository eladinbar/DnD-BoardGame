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
    public String visit(Enemy enemy) {
        return ""; //Do nothing
    }

    @Override
    public String visit(Player player) {
        return this.engage(player);
    }

    @Override
    public String visit(Wall wall) {
        return ""; //Do nothing
    }

    @Override
    public String visit(EmptyTile emptyTile) {
        this.setPosition(emptyTile.getPosition());
        return "";
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
