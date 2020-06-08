package Model.UnitPackage.PlayerPackage;

public enum Rogues {
    ARYA_STARK("Arya Stark", 150, 40, 2, 20),
    BRONN("Bronn", 250, 35, 3, 50);

    String name;
    int healthPool;
    int attack;
    int defense;
    int energyCost;

    Rogues(String name, int healthPool, int attack, int defense, int energyCost) {
        this.name = name;
        this.healthPool = healthPool;
        this.attack = attack;
        this.defense = defense;
        this.energyCost = energyCost;
    }
}
