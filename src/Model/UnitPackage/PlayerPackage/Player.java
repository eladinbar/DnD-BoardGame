package Model.UnitPackage.PlayerPackage;

import Model.TilePackage.EmptyTile;
import Model.TilePackage.Tile;
import Model.TilePackage.Wall;
import Model.UnitPackage.EnemyPackage.Enemy;
import Model.UnitPackage.HeroicUnit;
import Model.UnitPackage.Unit;

import java.awt.Point;

public abstract class Player extends Unit implements HeroicUnit {
    protected Integer experience;
    protected Integer level;
    protected Integer experienceThreshold;

    public Player(Point position) {
        super(position);
        this.symbol = PlayerStatus.ALIVE.playerStatus;
        this.experience = 0;
        this.level = 1;
        this.experienceThreshold = 50 * level;
    }

    public void levelUp() {
        experience -= 50 * level;
        level++;
        healthPool += 10 * level;
        currentHealth = healthPool;
        attack += 4 * level;
        defense += level;
    }

    public void interact(Tile tile) {
        tile.accept(this);
    }

    @Override
    public void visit(Enemy enemy) {
        this.attack(enemy);
    }

    @Override
    public void visit(Player player) {

    }

    @Override
    public void visit(Wall wall) {

    }

    @Override
    public void visit(EmptyTile emptyTile) {

    }

    @Override
    public void attack(Player player) {

    }

    @Override
    public void attack(Enemy enemy) {

    }

    @Override
    public void defend(Unit unit) {

    }
}
