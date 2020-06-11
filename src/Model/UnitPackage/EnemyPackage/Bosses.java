package Model.UnitPackage.EnemyPackage;

public enum Bosses {
    THE_MOUNTAIN("The Mountain", 'M', 1000, 60, 25, 6, 500, 3, 1, 500, "Head Smash"),
    QUEEN_CERSEI("Queen Cersei", 'C', 100, 10, 10, 1, 1000, 15, 100, 2000, "Wildfire"),
    NIGHT_KING("Night King", 'K', 5000, 300, 150, 8, 5000, 6, 6, 1000, "Spear Throw");

    String name;
    char symbol;
    int healthPool;
    int attack;
    int defense;
    int visionRange;
    int experienceValue;
    int abilityFrequency;
    int abilityRange;
    int abilityDamage;
    String abilityName;

    Bosses(String name, char symbol, int healthPool, int attack, int defense, int visionRange, int experienceValue, int abilityFrequency, int abilityRange, int abilityDamage, String abilityName) {
        this.name = name;
        this.symbol = symbol;
        this.healthPool = healthPool;
        this.attack = attack;
        this.defense = defense;
        this.visionRange = visionRange;
        this.experienceValue = experienceValue;
        this.abilityFrequency = abilityFrequency;
        this.abilityRange = abilityRange;
        this.abilityDamage = abilityDamage;
        this.abilityName = abilityName;
    }
}
