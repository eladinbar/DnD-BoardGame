package Model.UnitPackage.PlayerPackage;

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
}
