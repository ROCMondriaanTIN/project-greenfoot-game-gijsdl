
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HUDImage here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class HUDImage extends Actor {

    public HUDImage() {
    }
    

    public HUDImage(int width, int height) {
        getImage().scale(width, height);
    }

    public HUDImage(int player, int width, int height){
        setImage("HUD/hud_p"+ player+ ".png");
        getImage().scale(width, height);
    }

    public HUDImage(String color, int width, int height) {
        setImage("gem" + color + ".png");
        getImage().scale(width, height);
    }
    
    
   public void setKeyColor(String color){
        setImage("HUD/hud_key" + color + ".png");
    }
    public void setCoinSize(int width, int height) {
        getImage().scale(width, height);
    }
}
