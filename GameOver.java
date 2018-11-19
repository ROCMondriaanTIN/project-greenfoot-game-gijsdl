import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameOver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOver extends World
{

    /**
     * Constructor for objects of class GameOver.
     * 
     */
    public GameOver(int player)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 800, 1, false);
        setBackground("gameOverBG.png");
        addObject(new GameOverObject(player), 500, 400);
        addObject(new GameOverObject("Game Over", 100, Color.RED), 500, 100);
        addObject(new GameOverObject("Klik op het poppetje om opniew te beginnen.", 25, Color.BLUE), 500, 700);
        
    }
}
