import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LevelKeuze here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LevelKeuze extends World
{

    /**
     * Constructor for objects of class LevelKeuze.
     * 
     */
    public LevelKeuze()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 800, 1, false);
        
    }
    
    @Override
    public void act(){
         if (Greenfoot.isKeyDown("space")) {
             Greenfoot.setWorld(new Level(1, 0, 300, 1800));
         }
    }
}
