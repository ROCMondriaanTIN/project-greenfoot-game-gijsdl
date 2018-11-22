
public class DiamantsGot {
    
    private static DiamantsGot instance;
//    private static
    private DiamantsGot() {
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

    public static void gotDiamand() {
        
    }
    
}
