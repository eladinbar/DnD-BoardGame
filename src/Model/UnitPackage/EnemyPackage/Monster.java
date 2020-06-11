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

    protected void moveLeft(Tile[][] layout) {
        this.interact(layout[this.position.x-1][this.position.y]);
    }

    protected void moveRight(Tile[][] layout) {
        this.interact(layout[this.position.x+1][this.position.y]);
    }

    protected void moveUp(Tile[][] layout) {
        this.interact(layout[this.position.x][this.position.y+1]);
    }

    protected void moveDown(Tile[][] layout) {
        this.interact(layout[this.position.x][this.position.y-1]);
    }

    protected void randomMovement(Tile[][] layout) {
        int rand = (int)Math.random()*5;
        switch(rand) {
            case 0:
                break; //Do nothing
            case 1:
                this.moveLeft(layout);
                break;
            case 2:
                this.moveRight(layout);
                break;
            case 3:
                this.moveUp(layout);
                break;
            case 4:
                this.moveDown(layout);
                break;
        }
    }
}
