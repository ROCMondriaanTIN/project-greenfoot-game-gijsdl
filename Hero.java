
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

    public Hero(String image, int width, int heigth) {
        super();
        gravity = 9.8;
        acc = 0.6;
        drag = 0.8;
        setImage(image);
        getImage().scale(width, heigth);
    }

    @Override
    public void act() {
        handleInput();
        animationWalk(getWidth(), getHeight(), 1);
        velocityX *= drag;
        velocityY += acc;
        if (velocityY > gravity) {
            velocityY = gravity;
        }
        applyVelocity();

        for (Actor enemy : getIntersectingObjects(Enemy.class)) {
            if (enemy != null) {
                getWorld().removeObject(this);
                break;
            }
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
                velocityY = -10;
                animationJump(getWidth(), getHeight(), 1);
            }

        }

        if (Greenfoot.isKeyDown("left")) {
            velocityX = -10;
            animationWalk(getWidth(), getHeight(), 1);
        } else if (Greenfoot.isKeyDown("right")) {
            velocityX = 10;
            animationWalk(getWidth(), getHeight(), 1);
        } else {
            animationStand(getWidth(), getHeight(), 1);
        }
    }
    int walkStatus = 1;
    int status = 0;

    public void animationWalk(int width, int heigth, int player) {

        if (status == 3) {
            if (walkStatus >= 11) {
                walkStatus = 1;
            }

            if (isOnGround) {
                setImage("Player/p" + player + "_walk/PNG/p" + player + "_walk"
                        + walkStatus + ".png");

            }else{
                setImage("Player/p" + player + "_jump.png");
            }

            walkStatus++;
            status = 0;
        } else {
            
            status++;
        }
        
        getImage().scale(width, heigth);
    }

    public void animationJump(int width, int heigth, int player) {
        setImage("Player/p" + player + "_jump.png");
        getImage().scale(width, heigth);
    }

    public void animationStand(int width, int heigth, int player) {
        if (isOnGround) {
            setImage("Player/p" + player + "_walk/PNG/p" + player + "_walk1.png");
            getImage().scale(width, heigth);
            walkStatus = 1;
        }else{
            setImage("Player/p" + player + "_jump.png");
        }
        getImage().scale(width, heigth);
    }

    

    public int getWidth() {
        return getImage().getWidth();
    }

    public int getHeight() {
        return getImage().getHeight();
    }
}
