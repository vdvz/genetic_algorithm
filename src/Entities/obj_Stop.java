package Entities;

public class obj_Stop implements worldObject {

    final private static Integer type = -1;

    @Override
    public Integer getType(){
        return type;
    }

    private static obj_Stop instance = new obj_Stop();

    private obj_Stop(){}

    public static obj_Stop getInstance() {
        return instance;
    }

}
