package Model.UnitPackage.EnemyPackage;

public enum Traps {
    BONUS_TRAP("Bonus Trap", 'B', 1, 1, 1, 250, 1, 5, 1),
    QUEENS_TRAP("Queen's Trap", 'Q', 250, 50, 10, 100, 3, 7, 2),
    DEATH_TRAP("Death Trap", 'D', 500, 100, 20, 250, 1, 10, 3);

    String name;
    char symbol;
    int healthPool;
    int attack;
    int defense;
    int experienceValue;
    int visibilityTime;
    int invisibilityTime;
    int menuPosition;

    Traps(String name, char symbol, int healthPool, int attack, int defense, int experienceValue, int visibilityTime, int invisibilityTime, int menuPosition) {
        this.name = name;
        this.symbol = symbol;
        this.healthPool = healthPool;
        this.attack = attack;
        this.defense = defense;
        this.experienceValue = experienceValue;
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        this.menuPosition = menuPosition;
    }
}
