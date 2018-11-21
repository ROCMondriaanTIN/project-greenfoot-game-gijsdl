
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class PlayerSelect here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PlayerSelect extends Actor {

    private int player;

    public PlayerSelect(int player) {
        setImage("Player/p" + player + "_stand.png");
        this.player = player;
    }

    public PlayerSelect(String text, int size) {
        setImage(new GreenfootImage(text, size, Color.YELLOW, new Color(0, 0, 0, 0), Color.RED));

    }

    /**
     * Act - do whatever the PlayerSelect wants to do. This method is called
     * whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    @Override
    public void act() {
        // Add your action code here.
        if (getImage().toString().contains("Player")) {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if (mouse != null) {
                List objects = getWorld().getObjectsAt(mouse.getX(), mouse.getY(), PlayerSelect.class);
                for (Object object : objects) {
                    if (object == this) {
                        setImage("Player/p" + player + "_front.png");

                    } else {
                        setImage("Player/p" + player + "_stand.png");
                    }

                }
            }
            if (Greenfoot.mouseClicked(this)) {
                Greenfoot.setWorld(new LevelKeuze(1, player));
                
            }
        }
    }

}
