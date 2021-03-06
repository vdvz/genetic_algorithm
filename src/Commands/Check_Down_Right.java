package Commands;
import Entities.*;
import World.World;

public class Check_Down_Right implements Command_Interface{

    int new_x;
    int health;
    int receive_health;
    int new_y;
    int shift_head;

    @Override
    public boolean action(Object_Interface obj, int x, int y, int health_) {
        receive_health = health_;
        health = health_;
        new_x = x;
        new_y = y;
        Object_Interface w_obj = World.getInstance().getObject(x+1,y-1);
        if(w_obj instanceof Bot){
            shift_head = 1;
        }
        else if(w_obj instanceof obj_Stop){
            shift_head = 2;
        }
        else if (w_obj instanceof obj_None){
            shift_head = 3;
        }
        else if(w_obj instanceof obj_Meal){
            shift_head = 4;
        }
        else if(w_obj instanceof obj_Poison){
            World.getInstance().setObject(x+1, y-1, obj_Meal.getInstance());
            shift_head = 5;
        }


        return false;
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
