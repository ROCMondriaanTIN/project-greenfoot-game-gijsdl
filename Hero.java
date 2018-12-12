
import greenfoot.*;
import java.util.List;

/**
 *
 * @author R. Springer
 */
public class Hero extends Mover {

    private double gravity;
    private double acc;
    private double drag;
    private int walkStatus = 0;
    private int status = 0;
    private String direction = "right";
    private int lives = 2;
    private int spawnX;
    private int spawnY;
    private int coin = 0;
    private boolean hasKey;
    private Overlay overlay;
    private int player = 3;
    private int diamonds;
    private int level;
    private GreenfootImage[] playerWalk = new GreenfootImage[11];
    private GreenfootImage playerJump;
    private CollisionEngine collisionEngine;
    private TileEngine tileEngine;
    private boolean onMovingPlatform;
    private boolean onMovingPlatformX;
    private boolean fireBallHit;
    private int fireBallTick;

    public Hero(Overlay overlay) {
        super();
        gravity = 9.8;
        acc = 0.6;
        drag = 0.8;
        this.overlay = overlay;
    }

    public void addTileEngine(CollisionEngine collisionEngine, TileEngine tileEngine) {
        this.collisionEngine = collisionEngine;
        this.tileEngine = tileEngine;
    }

    @Override
    public void act() {

        handleInput();

        if (!checkForMovingPlatform()) {

            velocityY += acc;
            if (velocityY > gravity) {
                velocityY = gravity;
            }

        }
        if (!onMovingPlatformX) {
            velocityX *= drag;
        }

        if (fireBallTick >= 2 && fireBallHit) {
            fireBallHit = false;
            fireBallTick = 0;
        } else if (fireBallHit) {
            fireBallTick++;
        }

        applyVelocity();
        checkForEnemy();
        checkForFireBall();

        checkForBlock();

    }

    public void checkForEnemy() {
        for (Enemy enemy : getIntersectingObjects(Enemy.class)) {
            if (enemy != null) {
                if (!enemy.getImage().toString().contains("upside")) {
                    if (velocityY > 1 && getY() < enemy.getY()) {
                        enemy.dead();
                        velocityY = -10;
                    } else {
                        died();
                    }
                    break;
                }
            }
        }
    }

    public void checkForFireBall() {
        for (FireBall enemy : getIntersectingObjects(FireBall.class)) {
            if (enemy != null && !fireBallHit) {
                fireBallHit = true;
                died();
                return;
            }
        }
    }

    public void checkForBlock() {

        List<Tile> tiles = collisionEngine.getCollidingTiles(this, true);

        for (Tile tile : tiles) {
            if (tile != null) {
                if (tile.type == TileType.WATER) {
                    died();
                    break;
                } else if (tile.type == TileType.GOLDCOIN) {
                    tileEngine.removeTile(tile);
                    Greenfoot.playSound("goldCoin.wav");
                    coin += 2;
                    coinCheck();
                    if (coin != 0) {
                        overlay.addCoin("Gold");
                    }
                    break;
                } else if (tile.type == TileType.SILVERCOIN) {
                    tileEngine.removeTile(tile);
                    Greenfoot.playSound("silverCoin.wav");
                    coin += 1;
                    coinCheck();
                    if (coin != 0) {
                        overlay.addCoin("Silver");
                    }
                    break;
                } else if (tile.type == TileType.KEY) {
                    tileEngine.removeTile(tile);
                    Greenfoot.playSound("key.wav");
                    hasKey = true;
                    overlay.gotKey(getColor(tile));
                    break;
                } else if (tile.type == TileType.DOORCLOSE && hasKey) {
                    tile.setImage("door_openMid.png");
                    tile.setTileType(TileType.DOOROPEN);
                    tileEngine.getTileAt(tile.getColom(), tile.getRow() - 1).setImage("door_openTop.png");
                    hasKey = false;
                    overlay.openedDoor();
                    break;
                } else if (tile.type == TileType.GEM) {
                    tileEngine.removeTile(tile);
                    Greenfoot.playSound("diamond.wav");
                    diamonds++;
                    overlay.addDiamant(getColor(tile));
                    DiamantsGot.getInstance().gotDiamand(level, tile.getColom(), tile.getRow());
                } else if (tile.type == TileType.DOOROPEN) {
                    if (level < 7) {
                        Greenfoot.setWorld(new LevelKeuze(level + 1, player));
                        Greenfoot.playSound("fanfare.wav");
                    } else {
                        Greenfoot.setWorld(new EndScreen(diamonds, player));
                    }
                }
            }
        }
    }

    public boolean checkForMovingPlatform() {
        for (MovingPlatform platform : getIntersectingObjects(MovingPlatform.class)) {
            if (platform != null) {
                int bottom = getY() + getImage().getHeight() / 2;
                int topPlatform = platform.getY() - platform.getImage().getHeight() / 2;
                int top = getY() - getImage().getHeight() / 2;
                int bottomPlatform = platform.getY() + platform.getImage().getHeight() / 2;
                double overlapY = 0;
                int y = getY();
                int x = getX();

                if (bottom > topPlatform && !onMovingPlatform) {
                    if (velocityY >= 0 && Math.abs(topPlatform - bottom) < Math.abs(top - bottomPlatform)) {
                        overlapY = topPlatform - bottom;
                    }
                }

                if (Math.abs(overlapY) > 0) {
                    velocityY = 0;
                    if (player == 2) {
                        overlapY += 1;
                    }
                    y += overlapY;
                    setLocation(x, y);
                }
                if (Math.abs(topPlatform - bottom) < Math.abs(top - bottomPlatform)) {

                    if (platform.getHorizontal()) {
                        velocityX = platform.getSpeed();
                        onMovingPlatformX = true;
                        handleInput();
                    } else if (bottom - 10 < topPlatform) {
                        velocityY = platform.getSpeed();
                        handleInput();
                    }
                    onMovingPlatform = true;
                    return true;
                }
            }
        }
        onMovingPlatformX = false;
        onMovingPlatform = false;
        return false;
    }

    public String getColor(Tile tile) {
        if (tile.getImage().toString().contains("Blue")) {
            return "Blue";
        } else if (tile.getImage().toString().contains("keyGreen") || tile.getImage().toString().contains("gemGreen")) {
            return "Green";
        } else if (tile.getImage().toString().contains("Red")) {
            return "Red";
        } else {
            return "Yellow";
        }
    }

    public void died() {
        lives--;
        Greenfoot.playSound("au.wav");
        if (lives > 0) {
            setLocation(spawnX, spawnY);
            overlay.removeLive();
        } else {
            Greenfoot.setWorld(new GameOver(player));
        }
    }

    public void coinCheck() {
        if (coin >= 40) {
            lives++;
            coin -= 40;
            overlay.extraLeven();
        }
    }

    private boolean isOnGround() {
        int dx = getImage().getWidth() / 2;
        int dy = getImage().getHeight() / 2 + 1;

        //checks if here is not going up or down
        if (velocityY != 0 && !onMovingPlatform) {
            return false;
        }
        //checks tile under hero
        start:
        for (int i = -1; i <= 1; i++) {
            for (Tile tile : getObjectsAtOffset((dx * i) - (3 * i), dy, Tile.class)) {
                if (tile.isSolid) {
                    return true;
                }
                break start;
            }
        }
        if (onMovingPlatform) {
            return true;
        }
        return false;
    }

    public void handleInput() {
        isOnGround();
        if (Greenfoot.isKeyDown("space")) {

            if (isOnGround()) {
                velocityY = -17;
                animationJump(getWidth(), getHeight());
            }
        }

        if (Greenfoot.isKeyDown("left")) {
            velocityX = -10;
            direction = "left";
            animationWalk(getWidth(), getHeight());

        } else if (Greenfoot.isKeyDown("right")) {
            velocityX = 10;
            direction = "right";
            animationWalk(getWidth(), getHeight());
        } else {
            animationStand(getWidth(), getHeight());
        }
//        if (Greenfoot.isKeyDown("w")) {
//            velocityY = -20;
//        }
//        if (Greenfoot.isKeyDown("a")) {
//            velocityX = -20;
//        } else if (Greenfoot.isKeyDown("d")) {
//            velocityX = 20;
//        }
//        if (Greenfoot.isKeyDown("q")) {
//            System.out.println(getX() + ", " + getY());
//            System.out.println("");
//        }
        if (getX() < 0) {
            velocityX = 1;
        }
        if (getX() > tileEngine.MAP_WIDTH * tileEngine.TILE_WIDTH) {
            velocityX = -1;
        }
    }

    public void animationWalk(int width, int heigth) {

        if (status == 2) {
            if (walkStatus > 10) {
                walkStatus = 0;
            }

            if (isOnGround()) {
                setImage(new GreenfootImage(playerWalk[walkStatus]));
            } else {
                setImage(new GreenfootImage(playerJump));
            }
            mirror();
            walkStatus++;
            status = 0;
        } else {

            status++;
        }
        getImage().scale(width, heigth);
    }

    public void animationJump(int width, int heigth) {
        setImage(new GreenfootImage(playerJump));
        mirror();
        getImage().scale(width, heigth);
    }

    public void animationStand(int width, int heigth) {
        if (isOnGround()) {
            setImage(new GreenfootImage(playerWalk[0]));

            walkStatus = 0;

        } else {
            setImage(new GreenfootImage(playerJump));
        }
        mirror();
        getImage().scale(width, heigth);
    }

    public void mirror() {
        if (direction.equals("left")) {
            getImage().mirrorHorizontally();
        }
    }

    public void setPlayer(int player) {
        for (int i = 0; i < playerWalk.length; i++) {
            playerWalk[i] = new GreenfootImage("Player/p" + player + "_walk/PNG/p" + player + "_walk" + (i + 1) + ".png");
        }
        playerJump = new GreenfootImage("Player/p" + player + "_jump.png");
        setImage(playerWalk[0]);
        this.player = player;
        overlay.setPlayer(player, lives);
    }

    public void setSpawn(int x, int y) {
        spawnX = x;
        spawnY = y;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getWidth() {
        return getImage().getWidth();
    }

    public int getHeight() {
        return getImage().getHeight();
    }
}
