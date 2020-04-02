package World;
import Entities.Object_Interface;


public interface World_Interface {

    void setObject(int x, int y, Object_Interface obj);

    Object_Interface getObject(int x, int y);

    void clearPossition(int x, int y);

    void clearWorld();

    int getWorldSize();

}
