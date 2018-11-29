
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Start here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Start extends World {

    /**
     * Constructor for objects of class Start.
     *
     */

    public static final WorldRegistry worldRegistry = new WorldRegistry();
    
    public Start() {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 800, 1, false);
        Greenfoot.start();;
        createObject();
        setLevels();

    }
    
    

    public void createObject() {
        addObject(new PlayerSelect("Collect the KEY", 100), 500, 150);
        addObject(new PlayerSelect("Kies met wie je wilt spelen", 50), 500, 300);
        addObject(new PlayerSelect("uitleg"), 900, 50);
        

        addObject(new PlayerSelect(3), 150, 500);
        addObject(new PlayerSelect(1), 500, 500);
        addObject(new PlayerSelect(2), 850, 500);
    }

    public void setLevels() {
        Level.firstTime = true;
        worldRegistry.registerLevel(1, new Level1());
//        worldRegistry.registerLevel(2, new Level1());
        
    }
}
