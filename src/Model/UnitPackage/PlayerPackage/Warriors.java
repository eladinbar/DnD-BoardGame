package Model.UnitPackage.PlayerPackage;

public enum Warriors {
    JON_SNOW("Jon Snow", 300, 30, 4, 3, 1),
    THE_HOUND("The Hound", 400, 20, 6, 5, 2);

    String name;
    int healthPool;
    int attack;
    int defense;
    int abilityCooldown;
    int menuPosition;

    Warriors(String name, int healthPool, int attack, int defense, int abilityCooldown, int menuPosition) {
        this.name = name;
        this.healthPool = healthPool;
        this.attack = attack;
        this.defense = defense;
        this.abilityCooldown = abilityCooldown;
        this.menuPosition = menuPosition;
    }
}
