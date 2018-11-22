
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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

    public void create(int player, int heroSpawnX, int heroSpawnY) {
        this.player = player;

        te = new TileEngine(this, 70, 70, this.map);
        Camera camera = new Camera(te);
        hero.setSpawn(heroSpawnX, heroSpawnY);
        hero.setLevel(1);

        camera.follow(hero);

        addObject(camera, 0, 0);
        addObject(hero, heroSpawnX, heroSpawnY);
        addObject(overlay, 0, 0);
        camera.act();
        hero.act();

        ce = new CollisionEngine(te, camera);

        ce.addCollidingMover(hero);
        overlay.create();

    }

    @Override
    public void act() {
        ce.update();
    }

    public Level reset() {
        return this;
    }

}
