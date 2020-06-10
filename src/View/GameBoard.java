package View;

import Model.TilePackage.*;
import Model.UnitPackage.PlayerPackage.Player;

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

    public GameBoard(Tile[][] layout, int length, int width){
        this.layout = layout;
        this.boardLength = length;
        this.boardWidth = width;
    }

    @Override
    public void printInfo() {
        for (int j = 0; j < layout[0].length; j++) {
            for (int i = 0; i < layout.length; i++) {
                System.out.print(layout[i][j]);
            }
            System.out.println();
        }
    }

    public void insertPlayer(Tile p, int x, int y){
        layout[x][y] = p;
    }
}
