package commands;

public interface Command_Interface {

    //возвращает 0 если была завершающая команда 1 если нет
    public boolean action(int x, int y, int health);

    //Дельта здоровья после выполнения команды
    public int getDeltaHealth();

    public int get_x();

    //возвращает сдвиг головки
    public int getShiftHead();

    public int get_y();
}
