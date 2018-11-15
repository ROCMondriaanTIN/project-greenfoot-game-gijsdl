import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Diamant here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Diamant extends Actor
{
    public Diamant(String color, int width, int height) {
        setImage("gem" + color + ".png");
        getImage().scale(width, height);
    }
    /**
     * Act - do whatever the Diamant wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    @Override
    public void act() 
    {
        // Add your action code here.
    }    
}
