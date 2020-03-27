package World;
import Entities.worldObject;


public interface World_Interface {

    void setObject(int x, int y, worldObject obj);

    worldObject getObject(int x, int y);

    void clearPossition(int x, int y);

    void clearWorld();

    int getWorldSize();

}
