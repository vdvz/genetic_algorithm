package Entities;

public class obj_Meal implements Object_Interface {


    private static obj_Meal instance = new obj_Meal();

    final private static Integer type = 2;

    @Override
    public Integer getType(){
        return type;
    }

    private obj_Meal(){}

    public static obj_Meal getInstance() {
        return instance;
    }



}
