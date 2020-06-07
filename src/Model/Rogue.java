package Model;

public class Rogue extends Player {
    Integer cost;
    Integer currentEnergy;

    public Rogue() {
        //insert initializers here
        //Using energy as resource. Starting energy equals to the rogue’s maximum energy which is 100.
    }

    @Override
    public void levelUp() {
        currentEnergy = 100;
        attack += 3 * level;
    }

    public void onGameTick() {
        currentEnergy = Math.min(currentEnergy + 10, 100);
    }

    @Override
    public void castAbility() throws Exception {
        if (currentEnergy < cost) {
            throw new Exception(/*insert appropriate message*/);
        }
        else {
            currentEnergy -= cost;
            //For each enemy within range < 2, deal damage (reduce health value) equals to the rogue’s
            //attack points (each enemy will try to defend itself).
        }
    }
}
