package Model.UnitPackage.PlayerPackage;

public class Mage extends Player {
    //Special ability: Blizzard, randomly hit enemies within range for an amount equals to the mage’s
    //spell power at the cost of mana.
    private Integer manaPool;
    private Integer currentMana;
    private Integer manaCost;
    private Integer spellPower;
    private Integer hitsCount;
    private Integer abilityRange;

    public Mage(Mages mage) {
        super();
        this.name = mage.name;
        this.healthPool = mage.healthPool;
        this.currentHealth = healthPool;
        this.attack = mage.attack;
        this.defense = mage.defense;
        this.manaPool = mage.manaPool;
        this.currentMana = manaPool;
        this.manaCost = mage.manaCost;
        this.spellPower = mage.spellPower;
        this.hitsCount = mage.hitsCount;
        this.abilityRange = mage.abilityRange;
    }

    @Override
    public void levelUp() {
        super.levelUp();
        manaPool += 25 * level;
        currentMana = Math.min(currentMana + manaPool/4, manaPool);
        spellPower += 10 * level;
    }

    public void onGameTick() {
        currentMana = Math.min(manaPool, currentMana + level);
    }

    @Override
    public void castAbility(/*insert parameters*/) throws Exception {
        if (currentMana < manaCost)
            throw new Exception(/*Insert appropriate message*/);
        else {
            currentMana -= manaCost;
            Integer hits = 0;
            while (hits < hitsCount /*& There exists a living enemy within range*/) {
                //Select random enemy within range
                //Deal damage (reduce health value) to the chosen enemy for an amount equal to spell power
                //(each enemy may try to defend itself).
                hits++;
            }
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
