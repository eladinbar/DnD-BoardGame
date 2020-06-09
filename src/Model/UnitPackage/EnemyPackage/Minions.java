package Model.UnitPackage.EnemyPackage;

public enum Minions {
    LANNISTER_SOLDIER("Lannister Soldier", 's', 80, 8, 3, 3, 25),
    LANNISTER_KNIGHT("Lannister Knight", 'k', 200, 14, 8, 4, 50),
    QUEENS_GUARD("Queen's Guard", 'q', 400, 20, 15, 5, 100),
    WIRGHT("Wright", 'z', 600, 30, 15, 3, 100),
    BEAR_WRIGHT("Bear-Wright", 'b', 1000, 75, 30, 4, 250),
    GIANT_WRIGHT("Giant-Wright", 'g', 1500, 100, 40, 5, 500),
    WHITE_WALKER("White Walker", 'w', 2000, 150, 50, 6, 1000);

    String name;
    char symbol;
    int healthPool;
    int attack;
    int defense;
    int visionRange;
    int experienceValue;
    int menuPosition;

    Minions(String name, char symbol, int healthPool, int attack, int defense, int visionRange, int experienceValue) {
        this.name = name;
        this.symbol = symbol;
        this.healthPool = healthPool;
        this.attack = attack;
        this.defense = defense;
        this.visionRange = visionRange;
        this.experienceValue = experienceValue;
        this.menuPosition = menuPosition;
    }
}
