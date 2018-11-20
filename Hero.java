
import greenfoot.*;

/**
 *
 * @author R. Springer
 */
public class Hero extends Mover {

    private final double gravity;
    private final double acc;
    private final double drag;
    private int width;
    private boolean isOnGround;
    private int walkStatus = 1;
    private int status = 0;
    private String direction = "right";
    private int lives = 2;
    private int spawnX;
    private int spawnY;
    private int coin = 0;
    private boolean gotKey;
    private Overlay overlay;
    private int player;
    private int diamanten;
    int level;

    public Hero(int player, int spawnX, int spawnY, Overlay overlay, int level) {
        super();
        gravity = 9.8;
        acc = 0.6;
        drag = 0.8;
        setImage("Player/p" + player + "_walk/PNG/p" + player + "_walk1.png");
        this.level = level;
        this.spawnX = spawnX;
        this.spawnY = spawnY;
        this.overlay = overlay;
        this.player = player;
    }

    @Override
    public void act() {
        handleInput();

        velocityX *= drag;
        velocityY += acc;
        if (velocityY > gravity) {
            velocityY = gravity;
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
                    if (velocityY > 0) {
                        enemy.dood();
                    } else {
                        dood();
                    }

                    break;
                }
            }

        }
    }

    public void checkForFireBall() {
        for (FireBall enemy : getIntersectingObjects(FireBall.class)) {
            if (enemy != null) {
                dood();
                break;
            }

        }
    }

    public void checkForBlock() {
        for (Tile tile : getIntersectingObjects(Tile.class
        )) {
            if (tile != null) {
                if (tile.getImage().toString().contains("liquid")
                        && !tile.getImage().toString().contains("Top")) {
                    dood();
                    break;
                }
                if (tile.getImage().toString().contains("Gold")) {
                    coinCheck();
                    overlay.addCoin("Gold");
                    getWorld().removeObject(tile);
                    coin += 2;
                    break;
                } else if (tile.getImage().toString().contains("Silver")) {
                    coinCheck();
                    overlay.addCoin("Silver");
                    getWorld().removeObject(tile);
                    coin++;
                }
                if (tile.getImage().toString().contains("key")) {
                    getWorld().removeObject(tile);
                    gotKey = true;
                    overlay.gotKey(getColor(tile));
                    break;
                }
                if (tile.getImage().toString().contains("door_closedMid") && gotKey) {
                    tile.setImage("door_openMid.png");
                    getOneObjectAtOffset(tile.getImage().getWidth() / 2, tile.getImage().getHeight() / 2 - 70, Tile.class).setImage("door_openTop.png");
                    gotKey = false;
                    overlay.openedDoor();
                    break;
                }
                if (tile.getImage().toString().contains("gem")) {
                    getWorld().removeObject(tile);
                    diamanten++;
                    overlay.addDiamant(getColor(tile));
                }
                if (tile.getImage().toString().contains("door_openMid")) {
                    Greenfoot.setWorld(new LevelKeuze(level + 1, player, false));
                }
            }
        }
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

    public void dood() {
        lives--;
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
            coin = 0;
            overlay.extraLeven();
        }
    }

    private double posToNeg(double x) {
        return (x - (x * 2));
    }

    public void handleInput() {
        //on ground check and handling

        width = getImage().getWidth() / 2;

        Tile tile = (Tile) getOneObjectAtOffset(0, getImage().getHeight() / 2 + 1, Tile.class
        );

        if (tile == null) {
            tile = (Tile) getOneObjectAtOffset(this.width - 3, getImage().getHeight() / 2 + 1, Tile.class
            );

        }
        if (tile == null) {
            tile = (Tile) getOneObjectAtOffset((int) posToNeg(this.width) + 3, getImage().getHeight() / 2 + 1, Tile.class
            );
        }

        if (tile != null && tile.isSolid) {
            isOnGround = true;
        } else {
            isOnGround = false;
        }
        if (Greenfoot.isKeyDown("space")) {

            if (isOnGround) {
                velocityY = -17;
                animationJump(getWidth(), getHeight(), player);
            }

        }

        if (Greenfoot.isKeyDown("left")) {
            velocityX = -10;
            direction = "left";
            animationWalk(getWidth(), getHeight(), player);

        } else if (Greenfoot.isKeyDown("right")) {
            velocityX = 10;
            direction = "right";
            animationWalk(getWidth(), getHeight(), player);
        } else {
            animationStand(getWidth(), getHeight(), player);
        }
    }

    public void animationWalk(int width, int heigth, int player) {

        if (status == 2) {
            if (walkStatus > 11) {
                walkStatus = 1;
            }

            if (isOnGround) {
                setImage("Player/p" + player + "_walk/PNG/p" + player + "_walk"
                        + walkStatus + ".png");
            } else {
                setImage("Player/p" + player + "_jump.png");
            }
            mirror();
            walkStatus++;
            status = 0;
        } else {

            status++;
        }

        getImage().scale(width, heigth);
    }

    public void animationJump(int width, int heigth, int player) {
        setImage("Player/p" + player + "_jump.png");
        mirror();
        getImage().scale(width, heigth);
    }

    public void animationStand(int width, int heigth, int player) {
        if (isOnGround) {
            setImage("Player/p" + player + "_walk/PNG/p" + player + "_walk1.png");
            getImage().scale(width, heigth);
            walkStatus = 1;

        } else {
            setImage("Player/p" + player + "_jump.png");
        }
        mirror();
        getImage().scale(width, heigth);
    }

    public void mirror() {
        if (direction.equals("left")) {
            getImage().mirrorHorizontally();
        }
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
