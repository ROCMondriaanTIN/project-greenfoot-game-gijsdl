import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EndScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EndScreen extends World
{

   int maxdiamonds = 14;
    
    public EndScreen(int diamonds, int player)
    {    
        super(1000, 800, 1, false);
        if(diamonds == 0){
            setBackground("chest_close.png");
            addObject(new EndScreenObject(false, player), 470, 750);
        }else if (diamonds <  maxdiamonds /2){
            setBackground("chest_open1.png");
            addObject(new EndScreenObject(false, player), 470, 750);
        }else if (diamonds < maxdiamonds){
            setBackground("chest_open2.png");
            addObject(new EndScreenObject(false, player), 470, 750);
        }else if (diamonds >= maxdiamonds){
            setBackground("chest_open3.png");
            addObject(new EndScreenObject(true, player), 470, 750);
        }
    }
}
