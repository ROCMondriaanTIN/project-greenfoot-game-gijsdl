import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Info here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Info extends World
{

    /**
     * Constructor for objects of class Info.
     * 
     */
    public Info()
    {    
        super(1000, 800, 1, false);
        setBackground("bg_info.png");
        addObject(new PlayerSelect("terug"), 900, 50);
    }
}
