package Entities;

public class obj_None implements worldObject{

    final private static Integer type = 0;

    @Override
    public Integer getType(){
        return type;
    }


    private static obj_None instance = new obj_None();

    private obj_None(){}

    public static obj_None getInstance() {
        return instance;
    }

}
