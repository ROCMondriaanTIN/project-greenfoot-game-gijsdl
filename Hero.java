
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

    public Hero(String image, int width, int heigth, int spawnX, int spawnY, Overlay overlay) {
        super();
        gravity = 9.8;
        acc = 0.6;
        drag = 0.8;
        setImage(image);
        getImage().scale(width, heigth);
        this.spawnX = spawnX;
        this.spawnY = spawnY;
        this.overlay = overlay;
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
        checkForBlock();
        coinCheck();

    }

    public void checkForEnemy() {
        for (Actor enemy : getIntersectingObjects(Enemy.class)) {
            if (enemy != null) {
                dood();
                break;
            }
        }
    }

    public void checkForBlock() {
        for (Tile tile : getIntersectingObjects(Tile.class)) {
            if (tile.getImage().toString().contains("liquid")
                    && !tile.getImage().toString().contains("Top")) {
                dood();
                break;
            }
            if (tile.getImage().toString().contains("Gold")) {
                overlay.addCoin("Gold");
                getWorld().removeObject(tile);
                coin += 2;
                break;
            } else if (tile.getImage().toString().contains("Silver")) {
                overlay.addCoin("Silver");
                getWorld().removeObject(tile);
                overlay.update();
                coin++;
            }
            if (tile.getImage().toString().contains("key")) {
                getWorld().removeObject(tile);
                gotKey = true;
                System.out.println("key");
                break;
            }
            if (tile.getImage().toString().contains("door_closedMid") && gotKey) {
                tile.setImage("door_openMid.png");
                getOneObjectAtOffset(tile.getImage().getWidth() / 2, tile.getImage().getHeight() / 2 - 70, Tile.class).setImage("door_openTop.png");

                break;
            }
        }
    }

    public void dood() {
        lives--;
        if (lives > 0) {
            setLocation(spawnX, spawnY);
        } else {
            getWorld().removeObject(this);
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

        Tile tile = (Tile) getOneObjectAtOffset(0, getImage().getHeight() / 2 + 1, Tile.class);
        if (tile == null) {
            tile = (Tile) getOneObjectAtOffset(this.width - 3, getImage().getHeight() / 2 + 1, Tile.class);
        }
        if (tile == null) {
            tile = (Tile) getOneObjectAtOffset((int) posToNeg(this.width) + 3, getImage().getHeight() / 2 + 1, Tile.class);
        }

        if (tile != null && tile.isSolid) {
            isOnGround = true;
        } else {
            isOnGround = false;
        }
        if (Greenfoot.isKeyDown("space")) {

            if (isOnGround) {
                velocityY = -17;
                animationJump(getWidth(), getHeight(), 1);
            }

        }

        if (Greenfoot.isKeyDown("left")) {
            velocityX = -10;
            direction = "left";
            animationWalk(getWidth(), getHeight(), 1);

        } else if (Greenfoot.isKeyDown("right")) {
            velocityX = 10;
            direction = "right";
            animationWalk(getWidth(), getHeight(), 1);
        } else {
            animationStand(getWidth(), getHeight(), 1);
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

    public int getWidth() {
        return getImage().getWidth();
    }

    public int getHeight() {
        return getImage().getHeight();
    }
}
