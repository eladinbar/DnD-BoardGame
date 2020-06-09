package Controller.LevelCreationPackage;


public class TileFactoryProducer {

    public AbstractTileFactory getTileFactory(char c){
        switch (c) {
            case '.':
                return new EmptyTileFactory();
            case '#':
                return new WallTileFactory();
            default:
                return new EnemyFactory();

        }
    }


}
