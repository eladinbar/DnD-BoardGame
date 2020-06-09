package Model.UnitPackage.PlayerPackage;

public class Rogue extends Player {
    private Integer energyCost;
    private Integer currentEnergy;

    public Rogue(Rogues rogue) {
        this.name = rogue.name;
        this.healthPool = rogue.healthPool;
        this.currentHealth = healthPool;
        this.attack = rogue.attack;
        this.defense = rogue.defense;
        this.energyCost = rogue.energyCost;
        this.currentEnergy = energyCost;
        //Using energy as resource. Starting energy equals to the rogue’s maximum energy which is 100.
    }

    @Override
    public void levelUp() {
        super.levelUp();
        currentEnergy = 100;
        attack += 3 * level;
    }

    public void onGameTick() {
        currentEnergy = Math.min(currentEnergy + 10, 100);
    }

    @Override
    public void castAbility(/*insert parameters*/) throws Exception {
        if (currentEnergy < energyCost) {
            throw new Exception(/*insert appropriate message*/);
        }
        else {
            currentEnergy -= energyCost;
            //For each enemy within range < 2, deal damage (reduce health value) equals to the rogue’s
            //attack points (each enemy will try to defend itself).
        }
    }

    @Override
    public String describe() {
        //returns full information of the current unit (don’t forget to
        //override this method in each subclass). Use it to print the information of each unit during
        //combat / on player’s turn.
        throw new UnsupportedOperationException();
    }
}
