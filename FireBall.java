import greenfoot.*;
/**
 * Write a description of class FireBall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FireBall extends Mover
{

    public FireBall() {
        super();
        setImage("fireball.png");
        
    }
    
    /**
     * Act - do whatever the FireBall wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    @Override
    public void act() 
    {
        velocityX = 0;
        velocityY = 0;
        applyVelocity();
        turn(17);
    }    
}
