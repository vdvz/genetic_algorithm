package Entities;

public class obj_Stop implements Object_Interface {

    final private static Integer type = -1;

    private static obj_Stop instance = new obj_Stop();

    private obj_Stop(){}

    public static obj_Stop getInstance() {
        return instance;
    }

    @Override
    public Integer getType(){
        return type;
    }

}
