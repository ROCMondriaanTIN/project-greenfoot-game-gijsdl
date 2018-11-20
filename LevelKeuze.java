
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LevelKeuze here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class LevelKeuze extends World {

    int levelMax = 4;
    int level = 1;
    LevelKeuzeObject[] levelTrue = new LevelKeuzeObject[levelMax];
    LevelKeuzeObject[] levelfalse = new LevelKeuzeObject[levelMax];
    int y = 400;
    int x = 200;

    /**
     * Constructor for objects of class LevelKeuze.
     *
     */
    public LevelKeuze(int level, int speler,boolean first) {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 800, 1, false);
        setBackground("bg.png");
        if (this.level < level) {
            this.level = level;
        }
        for (int i = 0; i < level; i++) {
            addObject(new LevelKeuzeObject(i + 1, true, speler, first), x, y);
            x= x + 100;
        }
        for (int i = level; i < levelMax; i++) {
            addObject(new LevelKeuzeObject(i+1, false, speler, first), x, y);
            x = x + 100;
        }
        
    }

    @Override
    public void act() {
        
    }
}