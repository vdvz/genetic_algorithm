package Entities;

public class obj_Poison implements Object_Interface {
    final private static Integer type = 3;

    @Override
    public Integer getType(){
        return type;
    }


    private static obj_Poison instance = new obj_Poison();

    private obj_Poison(){}

    public static obj_Poison getInstance() {
        return instance;
    }

}
