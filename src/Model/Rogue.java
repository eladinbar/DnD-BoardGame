package Model;

public class Rogue extends Player {
    private Integer cost;
    private Integer currentEnergy;

    public Rogue() {
        //insert initializers here
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
        if (currentEnergy < cost) {
            throw new Exception(/*insert appropriate message*/);
        }
        else {
            currentEnergy -= cost;
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
