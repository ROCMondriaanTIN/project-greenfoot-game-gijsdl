
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class cloud here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Cloud extends Mover {

    private int mapWidth;
    int speed = 5;

    public Cloud(int cloud, int mapWidth, int direction) {
        setImage("cloud" + cloud + ".png");
        this.mapWidth = mapWidth;
        if (direction == 1){
            speed *= -1;
        }
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
