
import greenfoot.*;
import java.util.HashMap;
import java.util.Map;

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
    private int walkStatus = 0;
    private int status = 0;
    private String direction = "right";
    private int lives = 2;
    private int spawnX;
    private int spawnY;
    private int coin = 0;
    private boolean gotKey;
    private Overlay overlay;
    private int player = 3;
    private int diamanten;
    private int level;
    private GreenfootImage[] player1Walk = new GreenfootImage[11];
    private GreenfootImage[] player2Walk = new GreenfootImage[11];
    private GreenfootImage[] player3Walk = new GreenfootImage[11];
    private GreenfootImage player1Jump = new GreenfootImage("Player/p1_jump.png");
    private GreenfootImage player2Jump = new GreenfootImage("Player/p2_jump.png");
    private GreenfootImage player3Jump = new GreenfootImage("Player/p3_jump.png");
    private Map<Integer, GreenfootImage[]> playerImage = new HashMap();
    private Map<Integer, GreenfootImage> playerImageJump = new HashMap();

    public Hero(Overlay overlay) {
        super();
        for (int i = 0; i < player1Walk.length; i++) {

            player1Walk[i] = new GreenfootImage("Player/p1_walk/PNG/p1_walk" + (i + 1) + ".png");
        }
        for (int i = 0; i < player2Walk.length; i++) {
            player2Walk[i] = new GreenfootImage("Player/p2_walk/PNG/p2_walk" + (i + 1) + ".png");
        }
        for (int i = 0; i < player3Walk.length; i++) {
            player3Walk[i] = new GreenfootImage("Player/p3_walk/PNG/p3_walk" + (i + 1) + ".png");
        }
        playerImage.put(1, player1Walk);
        playerImage.put(2, player2Walk);
        playerImage.put(3, player3Walk);
        playerImageJump.put(1, player1Jump);
        playerImageJump.put(2, player2Jump);
        playerImageJump.put(3, player3Jump);
        setImage("Player/p1_walk/PNG/p1_walk1.png");
        gravity = 9.8;
        acc = 0.6;
        drag = 0.8;
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
                    getWorld().removeObject(tile);
                    coin += 2;
                    coinCheck();
                    if (coin != 0) {
                        overlay.addCoin("Gold");
                    }
                    break;
                } else if (tile.getImage().toString().contains("Silver")) {
                    getWorld().removeObject(tile);
                    coin++;
                    coinCheck();
                    if (coin != 0) {
                        overlay.addCoin("Silver");
                    }
                    break;

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
                    DiamantsGot.getInstance().gotDiamand(level, tile.getColom(), tile.getRow());
                }
                if (tile.getImage().toString().contains("door_openMid")) {
                    Greenfoot.setWorld(new LevelKeuze(level + 1, player));
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
        System.out.println(coin);
        System.out.println(lives);
        if (coin >= 40) {
            lives++;
            coin -= 40;
            overlay.extraLeven();
        }
        System.out.println(coin);
        System.out.println(lives);
        System.out.println("");

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
    }

    public void animationWalk(int width, int heigth) {

        if (status == 2) {
            if (walkStatus > 10) {
                walkStatus = 0;
            }

            if (isOnGround) {
//              
                setImage(new GreenfootImage(playerImage.get(player)[walkStatus]));
            } else {
                setImage(new GreenfootImage(playerImageJump.get(player)));
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
        setImage(new GreenfootImage(playerImageJump.get(player)));
        mirror();
        getImage().scale(width, heigth);
    }

    public void animationStand(int width, int heigth) {
        if (isOnGround) {
            setImage(new GreenfootImage(playerImage.get(player)[0]));

            walkStatus = 0;

        } else {
            setImage(new GreenfootImage(playerImageJump.get(player)));
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
