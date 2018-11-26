
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MovingPlatform here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MovingPlatform extends Mover {

    private int range;
    private boolean horizontal;
    private boolean firstAct;
    private int speed;
    private int xMax;
    private int xMin;
    private int yMax;
    private int yMin;

    public MovingPlatform(int range, boolean horizontal) {

        this.range = range;
        this.horizontal = horizontal;
        setImage("bridgeLogs.png");
        getImage().mirrorVertically();
        firstAct = true;
        speed = 2;
    }

    @Override
    public void act() {
        int x = getX();
        int y = getY();
        if (horizontal) {
            if (firstAct) {
                firstAct = false;
                xMax = x + range / 2;
                xMin = x - range / 2;
            }
            velocityX = speed;
            applyVelocity();
            if (getX() >= xMax) {
                speed *= -1;
                x = xMax;

            } else if (getX() <= xMin) {
                speed *= -1;
                x = xMin;
            }
        } else {
            if (firstAct) {
                firstAct = false;
                yMax = y + range / 2;
                yMin = y - range / 2;
            }
            velocityY = speed;
            applyVelocity();
            if (getY() >= yMax) {
                speed *= -1;
                y = yMax;

            } else if (getY() <= yMin) {
                speed *= -1;
                y = yMin;
            }
        }
    }
    
    public int getSpeed(){
        return speed;
    }
    
    public boolean getHorizontal(){
        return horizontal;
    }
}
