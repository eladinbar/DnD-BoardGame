package Controller;

import View.GameBoard;

import java.awt.Point;

public class Level {
    private GameBoard board;
    private Point startLocation;

    public Level(GameBoard board, Point startLoction) {
        this.board = board;
        this.startLocation = startLoction;
    }

    public GameBoard getBoard() {
        return board;
    }

    public Point getStartLocation() {
        return startLocation;
    }
}
