
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Coin here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Coin extends Actor {

    /**
     * Act - do whatever the Coin wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    @Override
    public void act() {
        // Add your action code here.
    }

    public void setSize(int width, int height) {
        getImage().scale(width, height);
    }
}
