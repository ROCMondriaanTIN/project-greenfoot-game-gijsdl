
import java.util.HashMap;
import java.util.Map;

public class WorldRegistry {

    private Map<Integer, Level> levels = new HashMap();
    
    public void registerLevel(int index, Level level){
        levels.put(index, level);
    }
    
    public Level getLevel(int index){
//        levels.putIfAbsent(index, new Level1());
        return levels.get(index);
    }
    
}
