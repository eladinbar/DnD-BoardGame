package Model;

public class Warrior extends Player {
    //Special ability: Avenger’s Shield, randomly hits one enemy withing range < 3 for an amount
    //equals to 10% of the warrior’s max health and heals the warrior for amount equals to (10×defense)
    //(but will not exceed the total amount of health pool).
    private Integer abilityCooldown;
    private Integer remainingCooldown;

    public Warrior() {
        //insert initializers
        //The warrior’s ability has a cooldown, meaning it can only be used once every ability cooldown
        //game ticks.
    }

    @Override
    public void levelUp() {
        super.levelUp();
        remainingCooldown = 0;
        healthPool += 5 * level;
        attack += 2 * level;
        defense += level;
    }

    public void onGameTick() {
        remainingCooldown -= 1;
    }

    @Override
    public void castAbility(/*insert parameters*/) throws Exception {
        if (remainingCooldown>0)
            throw new Exception(/*Insert appropriate message*/);
        else {
            remainingCooldown = abilityCooldown;
            currentHealth = Math.min(currentHealth + (10*defense), healthPool);
            //Randomly hits one enemy within range < 3 for an amount equals to 10% of the warrior’s
            //health pool
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
