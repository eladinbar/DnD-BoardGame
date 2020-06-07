package Model;

public class Hunter extends Player {
    //Special ability: Shoot, hits the closest enemy for an amount equals to the hunter’s attack points at
    //the cost of an arrow.
    Integer range;
    Integer arrowsCount;
    Integer ticksCount;

    public Hunter() {
        //insert initializers here
        //Using arrows as resource. Starting amount of arrows in quiver is equals to (10 × level).
    }

    @Override
    public void levelUp() {
        super.levelUp();
        arrowsCount += 10 * level;
        attack += 2 * level;
        defense += level;
    }

    public void onGameTick() {
        if (ticksCount == 10) {
            arrowsCount += level;
            ticksCount = 0;
        }
        else
            ticksCount++;
    }

    @Override
    public void castAbility() throws Exception {
        if (arrowsCount == 0)
            throw new Exception(/*Insert appropriate message here*/);
        else if (/*There is no enemy within range*/)
            throw new Exception(/*Insert appropriate message here*/);
        else {
            arrowsCount--;
            //Deal damage equals to attack points to the closest enemy within range (The enemy will try to
            //defend itself).
        }
    }
}
