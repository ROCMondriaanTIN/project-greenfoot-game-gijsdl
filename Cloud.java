
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class cloud here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Cloud extends Mover {

    private int mapWidth;
    int speed = 17;

    public Cloud(int cloud, int mapWidth) {
        setImage("cloud" + cloud + ".png");
        this.mapWidth = mapWidth;
    }

    @Override
    public void act() {
        velocityX = speed;
        applyVelocity();
        if (getX() >= mapWidth) {
            speed *= -1;
        } else if (getX() <= 0) {
            speed *= -1;
        }
    }
}
