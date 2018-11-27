
import greenfoot.*;

/**
 *
 * @author R. Springer
 */
public class Enemy extends Mover {

    private int walkRange;
    private int xMin;
    private int xMax;
    private boolean firstAct;
    private int speed;
    private int status = 0;
    private int walkStatus = 1;
    private String direction = "left";
    private boolean alive = true;

    public Enemy(int walkRange) {
        super();
        setImage("Enemies/snailWalk1.png");
        getImage().mirrorHorizontally();
        this.walkRange = 150;
        firstAct = true;
        speed = 1;
    }

    @Override
    public void act() {
        int x = getX();
        int y = getY();
        
        if (firstAct) {
            firstAct = false;
            xMin = x - walkRange / 2;
            xMax = x + walkRange / 2;
            
        }
        if (alive) {
            velocityX = speed;
            applyVelocity();
            if (getX() >= xMax) {
                speed *= -1;
                x = xMax;
                direction = "left";
            } else if (getX() <= xMin) {
                speed *= -1;
                x = xMin;
                direction = "right";

            }
            animation();
        }else{
            speed = 0;
            velocityX = speed;
            applyVelocity();
        }
        

    }

    public void animation() {
        if (status == 2) {
            if (walkStatus > 2) {
                walkStatus = 1;
            }
            setImage("Enemies/snailWalk" + walkStatus + ".png");
            walkStatus++;
            status = 0;

            if (direction.equals("right")) {
                getImage().mirrorHorizontally();
            }
        } else {
            status++;
        }

    }

    public void dead() {

        setImage("Enemies/snailShell_upsidedown.png");
        alive = false;
    }
}
