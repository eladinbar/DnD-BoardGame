package Controller.LevelCreationPackage;

import Model.TilePackage.Tile;
import Model.UnitPackage.EnemyPackage.Enemy;

import java.awt.*;
import java.util.*;
import java.util.List;


public class LevelCreator {

    private Point playerPosition;
    private List<Enemy> enemyList;
    private TileFactoryProducer tileFactoryProducer;

    public LevelCreator(){
        enemyList = new ArrayList<>();
        tileFactoryProducer = new TileFactoryProducer();
    }

    public Tile[][] createLevel(List<String> level, int length, int width){
        Tile[][] layOut = new Tile[length][width];
        for (int j = 0; j < layOut[0].length; j++) {
            for (int i = 0; i < layOut.length; i++) {
                char toDecode;
                if(!level.get(j).isEmpty())
                    toDecode = level.get(j).charAt(0);
                else
                    return null;
                //determine if player or not
                Point tilePlacment = new Point(i,j);
                if(toDecode == '@') {
                    playerPosition = tilePlacment;
                    layOut[i][j] = null;
                }
                else{
                    layOut[i][j] = tileFactoryProducer.getTileFactory(toDecode).getTile(toDecode,tilePlacment);
                }
                level.get(j).substring(1);
            }
        }
        return layOut;
    }

    public Point getPlayerPosition() {
        return playerPosition;
    }
}
