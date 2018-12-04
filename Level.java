
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

/**
 *
 * @author R. Springer
 */
public abstract class Level extends World {

    private CollisionEngine ce;
    static protected Overlay overlay;
    static protected Hero hero;
    int player = 1;
    int[][] map;
    Camera camera;
    TileEngine te;
    public static boolean firstTime = true;
    Random rand = new Random();

    /**
     * Constructor for objects of class MyWorld.
     *
     */
    public Level() {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 800, 1, false);
        if (firstTime) {
            overlay = new Overlay(player);
            hero = new Hero(overlay);
            firstTime = false;
        }
        this.setBackground("bg.png");

    }

    public abstract void load();

    public void create(int level, int heroSpawnX, int heroSpawnY) {

        te = new TileEngine(this, 70, 70, this.map);
        Camera camera = new Camera(te);
        hero.setSpawn(heroSpawnX, heroSpawnY);
        hero.setLevel(level);
        
        int mapWidth = te.MAP_WIDTH * te.TILE_WIDTH;
        for (int i = 0; i < 10; i++) {
            addObject(new Cloud(rand.nextInt(3) + 1, mapWidth), rand.nextInt(mapWidth), rand.nextInt(500));
        }
        
        camera.follow(hero);
        ce = new CollisionEngine(te, camera);
        addObject(camera, 0, 0);
        addObject(hero, heroSpawnX, heroSpawnY);
        hero.addTileEngine(ce, te);
        addObject(overlay, getWidth() / 2, getHeight() / 2);
        camera.act();
        hero.act();

        ce.addCollidingMover(hero);

        setPaintOrder(Overlay.class);
    }

    @Override
    public void act() {
        ce.update();
    }

    public Level reset() {
        return this;
    }

}
