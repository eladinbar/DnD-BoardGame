package Model.UnitPackage.PlayerPackage;

public enum Mages {
    MELISANDRE("Melisandre", 100, 5, 1, 300, 30, 15, 5, 6, 3),
    THOROS_OF_MYR("Thoros of Myr", 250, 25, 4, 150, 20, 20, 3, 4, 4);

    String name;
    int healthPool;
    int attack;
    int defense;
    int manaPool;
    int manaCost;
    int spellPower;
    int hitsCount;
    int abilityRange;
    int menuPosition;

    Mages(String name, int healthPool, int attack, int defense, int manaPool, int manaCost, int spellPower, int hitsCount, int abilityRange, int menuPosition) {
        this.name = name;
        this.healthPool = healthPool;
        this.attack = attack;
        this.defense = defense;
        this.manaPool = manaPool;
        this.manaCost = manaCost;
        this.spellPower = spellPower;
        this.hitsCount = hitsCount;
        this.abilityRange = abilityRange;
        this.menuPosition = menuPosition;
    }
}
