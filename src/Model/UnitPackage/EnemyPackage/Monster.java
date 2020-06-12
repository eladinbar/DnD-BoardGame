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
        this.switchPosition(emptyTile);
        return "";
    }

    protected String moveLeft(Tile[][] layout) {
        try {
            return this.interact(layout[this.position.x - 1][this.position.y]);
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            return ""; //Do nothing
        }
    }

    protected String moveRight(Tile[][] layout) {
        try {
            return this.interact(layout[this.position.x + 1][this.position.y]);
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            return ""; //Do nothing
        }
    }

    protected String moveUp(Tile[][] layout) {
        try {
            return this.interact(layout[this.position.x][this.position.y + 1]);
        }
        catch(ArrayIndexOutOfBoundsException ex) {
            return ""; //Do nothing
        }
    }

    protected String moveDown(Tile[][] layout) {
        try {
            return this.interact(layout[this.position.x][this.position.y - 1]);
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            return ""; //Do nothing
        }
    }

    protected String randomMovement(Tile[][] layout) {
        int rand = (int)Math.random()*5;
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
