package View;

import Model.TilePackage.Tile;

public class GameBoard implements GameInfo {
    private Tile[][] layout;

    public GameBoard(Tile[][] layout){
        this.layout = layout;
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

    public Tile[][] getLayout() {
        return layout;
    }
}
