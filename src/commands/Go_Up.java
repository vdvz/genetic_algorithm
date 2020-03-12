package commands;

public class Go_Up implements Command_Interface {
    static int new_x;
    static int health;
    static int receive_health;
    static int new_y;
    static int shift_head;

    @Override
    public boolean action(int x, int y, int health) {
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




