package commands;


import Entities.worldObject;

public interface Command_Interface {
    /*
    * getDeltaHealth - возвращает целое число - изменение здоровья после выполнения команды
    * get_x - возвращает число от 0 до World.getInstance().WORLD_SIZE X-позиция  бота после выполнения команды
    * get_y - возвращает число от 0 до World.getInstance().WORLD_SIZE Y-позиция  бота после выполнения команды
    * getShiftHead - возвращает смещение головки после выполнения данной команды
    * action - выполняет действие и возвращает значение true если команда была завершающей иначе false
    *
    * */

    boolean action(worldObject obj, int x, int y, int health_);

    int getDeltaHealth();

    int get_x();

    int get_y();

    int getShiftHead();

}
