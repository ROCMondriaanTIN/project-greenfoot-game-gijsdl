
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Overlay here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Overlay extends Actor {

    private Coin[] coin = new Coin[40];
    private ArrayList<Lives> lives = new ArrayList<>();
    private ArrayList<Diamant> diamant = new ArrayList<>();
    private static int coinID = 0;
    private int player;
    private boolean gotKey = false;
    Key key = new Key(40, 40);

    public Overlay(int player) {
        for (int i = 0; i < coin.length; i++) {
            coin[i] = new Coin();
        }
        this.player = player;
        for (int i = 0; i < 2; i++) {
            lives.add(new Lives(player, 40, 40));
        }

        this.setImage(new GreenfootImage(1, 1));
        coinID = 0;
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

        if (coinID > 0) {
            for (int i = 0; i < coinID; i++) {
                getWorld().addObject(coin[i], 950 - 10 * i, 50);
            }
        }

        for (int i = 0; i < lives.size(); i++) {
            getWorld().addObject(lives.get(i), 50 + 50 * i, 50);
        }

        if (diamant.size() > 0) {
            for (int i = 0; i < diamant.size(); i++) {
                getWorld().addObject(diamant.get(i), 50 + 50 * i, 100);

            }
        }

        if (gotKey) {

            getWorld().addObject(key, 950, 100);
        }
    }

    public void extraLeven() {
        coinID = 0;
        for (Coin coin : coin) {
            getWorld().removeObject(coin);
        }
        lives.add(new Lives(player, 40, 40));

    }

    public void removeLive() {

        getWorld().removeObject(lives.get(lives.size() - 1));
        lives.remove(lives.size() - 1);

    }

    public void gotKey(String color) {
        gotKey = true;
        key.setKeyColor(color);
    }

    public void openedDoor() {
        gotKey = false;
        getWorld().removeObject(key);
    }

    public void addDiamant(String color) {
        diamant.add(new Diamant(color, 50, 50));
    }
}
