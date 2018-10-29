
import greenfoot.*;
import java.util.List;

/**
 *
 * @author Roy
 */
public class TileEngine {

    public static int TILE_WIDTH;
    public static int TILE_HEIGHT;
    public static int SCREEN_HEIGHT;
    public static int SCREEN_WIDTH;
    public static int MAP_WIDTH;
    public static int MAP_HEIGHT;

    private World world;
    private int[][] map;
    private Tile[][] generateMap;
    private TileFactory tileFactory;

    public TileEngine(World world, int tileWidth, int tileHeight) {
        this.world = world;
        TILE_WIDTH = tileWidth;
        TILE_HEIGHT = tileHeight;
        SCREEN_WIDTH = world.getWidth();
        SCREEN_HEIGHT = world.getHeight();
        this.tileFactory = new TileFactory();
    }

    public TileEngine(World world, int tileWidth, int tileHeight, int[][] map) {
        this(world, tileWidth, tileHeight);
        this.setMap(map);
    }

    public void setMap(int[][] map) {
        this.map = map;
        MAP_HEIGHT = this.map.length;
        MAP_WIDTH = this.map[0].length;
        this.generateMap = new Tile[MAP_HEIGHT][MAP_WIDTH];
        this.clearTilesWorld();
        this.generateWorld();
    }

    public void setTileFactory(TileFactory tf) {
        this.tileFactory = tf;
    }

    public void clearTilesWorld() {
        List<Tile> removeObjects = this.world.getObjects(Tile.class);
        this.world.removeObjects(removeObjects);
    }

    public void generateWorld() {
        for (int y = 0; y < MAP_HEIGHT; y++) {
            for (int x = 0; x < MAP_WIDTH; x++) {
                // Nummer ophalen in de int array
                int mapIcon = this.map[y][x];
                if (mapIcon == -1) {
                    continue;
                }
                // Als de mapIcon -1 is dan wordt de code hieronder overgeslagen
                // Dus er wordt geen tile aangemaakt. -1 is dus geen tile;
                Tile createdTile = this.tileFactory.createTile(mapIcon);

                addTileAt(createdTile, x, y);
            }
        }
    }

    public void addTileAt(Tile tile, int x, int y) {
        // De X en Y positie zitten het midden van de Actor. 
        // De tilemap genereerd een wereld gebaseerd op dat de X en Y
        // positie links boven in zitten. Vandaar de we de helft van de 
        // breedte en hoogte optellen zodat de X en Y links boven zit voor 
        // het toevoegen van het object.
        this.world.addObject(tile, (x * TILE_WIDTH) + TILE_WIDTH / 2, (y * TILE_HEIGHT) + TILE_HEIGHT / 2);
        // Toevoegen aan onze lokale array. Makkelijk om de tile op te halen
        // op basis van een x en y positie van de map
        this.generateMap[y][x] = tile;
    }

    public Tile getTileAt(int colom, int row) {
        try {
            return this.generateMap[row][colom];
        } catch (Exception e) {
            return null;
        }
    }

    public Tile getTileAtXY(int x, int y) {
        int col = (int) Math.floor(x / TileEngine.TILE_WIDTH);
        int row = (int) Math.floor(y / TileEngine.TILE_HEIGHT);

        Tile tile = getTileAt(col, row);
        return tile;
    }

    public boolean checkTileSolid(int x, int y) {
        Tile tile = getTileAtXY(x, y);
        if (tile != null && tile.isSolid) {
            return true;
        }
        return false;
    }

    public int getColumn(int x) {
        return (int) Math.floor(x / TILE_WIDTH);
    }

    public int getRow(int y) {
        return (int) Math.floor(y / TILE_HEIGHT);
    }

    public int getX(int col) {
        return col * TILE_WIDTH;
    }

    public int getY(int row) {
        return row * TILE_HEIGHT;
    }

}
