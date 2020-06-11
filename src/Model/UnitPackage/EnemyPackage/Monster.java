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

    public String interact(Tile tile) {
        return tile.accept(this);
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

    protected String moveLeft(Tile[][] layout) {
        return this.interact(layout[this.position.x-1][this.position.y]);
    }

    protected String moveRight(Tile[][] layout) {
        return this.interact(layout[this.position.x+1][this.position.y]);
    }

    protected String moveUp(Tile[][] layout) {
        return this.interact(layout[this.position.x][this.position.y+1]);
    }

    protected String moveDown(Tile[][] layout) {
        return this.interact(layout[this.position.x][this.position.y-1]);
    }

    protected String randomMovement(Tile[][] layout) {
        int rand = (int)Math.random()*4;
        switch(rand) {
            case 0:
                return this.moveLeft(layout);
            case 1:
                return this.moveRight(layout);
            case 2:
                return this.moveUp(layout);
            case 3:
                return this.moveDown(layout);
            default:
                return ""; //Do nothing
        }
    }
}
