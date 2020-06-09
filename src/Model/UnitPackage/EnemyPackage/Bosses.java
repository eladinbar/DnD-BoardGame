package Model.UnitPackage.EnemyPackage;

public enum Bosses {
    THE_MOUNTAIN("The Mountain", 'M', 1000, 60, 25, 6, 500, 5, 1),
    QUEEN_CERSEI("Queen Cersei", 'C', 100, 10, 10, 1, 1000, 3, 2),
    NIGHT_KING("Night King", 'K', 5000, 300, 150, 8, 5000, 6, 3);

    String name;
    char symbol;
    int healthPool;
    int attack;
    int defense;
    int visionRange;
    int experienceValue;
    int abilityFrequency;
    int menuPosition;

    Bosses(String name, char symbol, int healthPool, int attack, int defense, int visionRange, int experienceValue, int abilityFrequency, int menuPosition) {
        this.name = name;
        this.symbol = symbol;
        this.healthPool = healthPool;
        this.attack = attack;
        this.defense = defense;
        this.visionRange = visionRange;
        this.experienceValue = experienceValue;
        this.abilityFrequency = abilityFrequency;
        this.menuPosition = menuPosition;
    }
}
