import java.util.ArrayList;

public class RoomType
{
    private ArrayList<String> types = new ArrayList<String>();

    public RoomType() {
        types.add("SINGLE");
        types.add("DOUBLE");
        types.add("MASTER_SUITE");
        types.add("ROYAL_SUITE");
    }
    public ArrayList<String> getTypes() { return types; }

    public void addType(String type) { types.add(type); }
    public void removeType(String type) { types.remove(types.indexOf(type)); }
    public void updateType (String oldType, String newtype) { types.set(types.indexOf(oldType), newtype); }
}