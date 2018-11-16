
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Key here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Key extends Actor {

    public Key( int width, int height) {
        
        getImage().scale(width, height);
    }

    /**
     * Act - do whatever the Key wants to do. This method is called whenever the
     * 'Act' or 'Run' button gets pressed in the environment.
     */
    @Override
    public void act() {
        // Add your action code here.
    } 
    public void setKeyColor(String color){
        setImage("HUD/hud_key" + color + ".png");
    }
}
