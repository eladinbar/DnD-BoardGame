package Model;

public abstract class Player extends Unit implements HeroicUnit {
    Integer experience;
    Integer level;
    Integer experienceThreshold;

    public Player() {
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
