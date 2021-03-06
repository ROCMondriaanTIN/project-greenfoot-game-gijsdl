
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DiamantsGot {

    private static DiamantsGot instance;
    private ArrayList<Tile> diamandMap1 = new ArrayList<>();
    private ArrayList<Tile> diamandMap2 = new ArrayList<>();
    private ArrayList<Tile> diamandMap3 = new ArrayList<>();
    private ArrayList<Tile> diamandMap4 = new ArrayList<>();
    private ArrayList<Tile> diamandMap5 = new ArrayList<>();
    private ArrayList<Tile> diamandMap6 = new ArrayList<>();
    private ArrayList<Tile> diamandMap7 = new ArrayList<>();
    private Map<Integer, ArrayList> diamand = new HashMap();

    private DiamantsGot() {
        diamand.put(1, diamandMap1);
        diamand.put(2, diamandMap2);
        diamand.put(3, diamandMap3);
        diamand.put(4, diamandMap4);
        diamand.put(5, diamandMap5);
        diamand.put(6, diamandMap6);
        diamand.put(7, diamandMap7);
    }

    //static block initialization for exception handling
    static {
        try {
            instance = new DiamantsGot();
        } catch (Exception e) {
            throw new RuntimeException("Exception occured in creating singleton instance");
        }
    }

    public static DiamantsGot getInstance() {
        return instance;
    }

    public void gotDiamand(int map, int colum, int row) {
        Tile tile = new Tile("gemGreen.png", 70, 70);
        tile.setColom(colum);
        tile.setRow(row);
        diamand.get(map).add(tile);
    }
    
    public void removeDiamants(TileEngine te, int map){
        ArrayList<Tile> tiles = diamand.get(map);
        for (Tile tile : tiles) {
            te.removeTile(tile);
        }
    }
    
    public void reset(){
        diamandMap1.clear();
        diamandMap2.clear();
        diamandMap3.clear();
        diamandMap4.clear();
        diamandMap5.clear();
        diamandMap6.clear();
        diamandMap7.clear();
    }

}
