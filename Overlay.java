
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Overlay here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Overlay extends Actor {

    Coin[] coin = new Coin[40];
    ArrayList<Lives> lives = new ArrayList<>();
    private static int coinID = 0;

    public Overlay() {
        for (int i = 0; i < coin.length; i++) {
            coin[i] = new Coin();
        }
        
        this.setImage(new GreenfootImage(1, 1));
    }

    /**
     * Act - do whatever the Overlay wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    @Override
    public void act() {
        update();
    }

    public void addCoin(String color) {

        coin[coinID].setImage("coin" + color + ".png");
        coin[coinID].setSize(40, 40);
        coinID++;
    }

    public void update() {
        int test = 0;
        if (coinID > 0) {
            for (int i = 0; i < coinID; i++) {
                getWorld().addObject(coin[i], 950 - 10 * i, 50);
            }
            System.out.println(test);
        }
    }

    public void extraLeven() {
        coinID = 0;
        for (Coin coin : coin) {

            getWorld().removeObject(coin);

        }

    }
}
