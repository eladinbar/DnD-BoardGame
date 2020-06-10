package Model.UnitPackage.PlayerPackage;

public enum PlayerStatus {
    ALIVE('@'),
    DEAD('X');

    final char playerStatus;

    PlayerStatus(char playerStatus) {
        this.playerStatus = playerStatus;
    }

    public char getPlayerStatus() {
        return playerStatus;
    }
}
