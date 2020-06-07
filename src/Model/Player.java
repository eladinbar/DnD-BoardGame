package Model;

public abstract class Player extends Unit implements HeroicUnit {
    protected Integer experience;
    protected Integer level;
    protected Integer experienceThreshold;

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

    @Override
    public String describe() {
        //returns full information of the current unit (don’t forget to
        //override this method in each subclass). Use it to print the information of each unit during
        //combat / on player’s turn.
        throw new UnsupportedOperationException();
    }
}
