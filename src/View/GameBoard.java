package View;

import Model.Tile;

public class GameBoard implements GameInfo {

    private Tile[][] layout;
    private int boardLength = 0;
    private int boardWidth = 0;

    public int getBoardLength() {
        return boardLength;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    @Override
    public void printInfo() {

    }
}
