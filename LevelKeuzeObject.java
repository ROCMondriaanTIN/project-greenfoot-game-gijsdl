
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LevelKeuzeObject here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class LevelKeuzeObject extends Actor {

    private int level;
    private boolean speelbaar;
    private int speler;
    private boolean first = true;

    public LevelKeuzeObject(int level, boolean speelbaar, int speler, boolean first) {
       
        if (speelbaar) {
            setImage("Level/level" + level + ".png");
        } else {
            setImage("Level/level" + level + "_false.png");
        }
        this.level = level;
        this.speelbaar = speelbaar;
        this.speler = speler;
        this.first = first;
    }

    /**
     * Act - do whatever the LevelKeuzeObject wants to do. This method is called
     * whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    @Override
    public void act() {
        if (speelbaar) {
            if (Greenfoot.mouseClicked(this)) {
                if(first){
                Greenfoot.setWorld(new Level(speler));
                } else {
                    Greenfoot.setWorld(new Level(speler, level - 1));
                }
            }
        }
    }
}
