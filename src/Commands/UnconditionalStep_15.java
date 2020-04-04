package Commands;

import Commands.Command_Interface;
import Entities.Object_Interface;

public class UnconditionalStep_15 implements Command_Interface{

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
        shift_head = 15;
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
