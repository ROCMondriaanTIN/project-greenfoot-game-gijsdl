
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ChooseChar here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ChooseChar extends World {

    /**
     * Constructor for objects of class ChooseChar.
     *
     */
    public ChooseChar() {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 800, 1, false);
        setBackground("bg.png");
        addObject(new PlayerSelect("Kies met wie je wilt spelen", 50), 500, 200);
        addObject(new PlayerSelect(3), 150, 400);
        addObject(new PlayerSelect(1), 500, 400);
        addObject(new PlayerSelect(2), 850, 400);
    }
}
