package Commands;
import Entities.*;
import World.World;

public class Go_Down_Left implements Command_Interface{

     int new_x;
     int health;
     int receive_health;
     int new_y;
     int shift_head;

    @Override
    public boolean action(Object_Interface obj, int x, int y, int health_) {
        receive_health = health_;
        health = health_ - 1;
        Object_Interface w_obj = World.getInstance().getObject(x-1,y-1);
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
            World.getInstance().setObject(x-1, y-1, obj);
            new_x = x-1;
            new_y = y-1;
            shift_head = 3;
        }
        else if(w_obj instanceof obj_Meal){
            new_x = x-1;
            new_y = y-1;
            World.getInstance().setObject(x, y, obj_None.getInstance());
            World.getInstance().setObject(x-1, y-1, obj);
            health += 10;
            shift_head = 4;
        }
        else if(w_obj instanceof obj_Poison){
            new_x = x-1;
            new_y = y-1;
            World.getInstance().setObject(x, y, obj_None.getInstance());
            World.getInstance().setObject(x-1, y-1, obj);
            health -= 100;
            shift_head = 5;
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
