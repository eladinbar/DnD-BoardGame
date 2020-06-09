package Model.UnitPackage.PlayerPackage;

public enum Hunters {
    YGRITTE("Ygritte", 220, 30, 2, 6, 7);

    String name;
    int healthPool;
    int attack;
    int defense;
    int range;
    int menuPosition;

    Hunters(String name, int healthPool, int attack, int defense, int range, int menuPosition) {
        this.name = name;
        this.healthPool = healthPool;
        this.attack = attack;
        this.defense = defense;
        this.range = range;
        this.menuPosition = menuPosition;
    }
}
