package commands;
import Entities.*;
import World.World;

public class Go_Up implements Command_Interface {

    static int new_x;
    static int health;
    static int receive_health;
    static int new_y;
    static int shift_head;

    @Override
    public boolean action(worldObject obj, int x, int y, int health_) {
        receive_health = health_;
        health = health_ - 1;
        worldObject w_obj = World.getInstance().getObject(x,y+1);
        if(w_obj instanceof Bot){
            new_x = x;
            new_y = y;
            shift_head = 1;
        }
        else if(w_obj instanceof obj_Stop){
            new_x = x;
            new_y = y;
            shift_head = 2;
        }
        else if (w_obj instanceof obj_None){
            World.getInstance().setObject(x, y, obj_None.getInstance());
            World.getInstance().setObject(x, y+1, obj);
            new_x = x;
            new_y = y+1;
            shift_head = 3;
        }
        else if(w_obj instanceof obj_Meal){
            new_x = x;
            new_y = y+1;
            World.getInstance().setObject(x, y, obj_None.getInstance());
            World.getInstance().setObject(x, y+1, obj);
            health += 10;
            shift_head = 4;
        }

        return true;
    }

    @Override
    public int getDeltaHealth() {
        return health - receive_health ;
    }

    @Override
    public int get_x() {
        return new_x;
    }

    @Override
    public int getShiftHead() {
        return shift_head;
    }

    @Override
    public int get_y() {
        return new_y;
    }

}
