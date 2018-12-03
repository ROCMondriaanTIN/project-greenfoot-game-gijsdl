import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EndScreenObject here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EndScreenObject extends Actor
{
    private boolean AllDiamonds;
    private int player;
    public EndScreenObject(boolean AllDiamonds, int player) {
        this.AllDiamonds = AllDiamonds;
        this.player = player;
        setImage("terugButton.png");
    }
    
    
    @Override
    public void act() 
    {
        if (Greenfoot.mouseClicked(this)) {
            if (!AllDiamonds){
                Greenfoot.setWorld(new LevelKeuze(4,player));
            }else{
                Greenfoot.setWorld(new Start());
            }
        }
    }    
}
