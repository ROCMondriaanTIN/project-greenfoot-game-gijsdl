
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameOverObject here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GameOverObject extends Actor {

    public GameOverObject(int player) {
        setImage("Player/p" + player + "_hurt.png");
    }

    public GameOverObject(String text, int size, Color color) {
        setImage(new GreenfootImage(text, size, color, new Color(0, 0, 0, 0)));
    }

    @Override
    public void act() {
        if (getImage().toString().contains("Player")) {
            if (Greenfoot.mouseClicked(this)) {
                Greenfoot.setWorld(new Start());
            }
        }
    }
    /**
     * Act - do whatever the GameOverObject wants to do. This method is called
     * whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
}
