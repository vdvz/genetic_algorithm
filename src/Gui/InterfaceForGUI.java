package Gui;

import Entities.Bot;

import javax.swing.*;
import java.util.ArrayList;

public interface InterfaceForGUI{

    //Возвращает таймер
    Timer getTimer();

    //Очищение поля, возвращение в начальное состояние
    void clear();

    //Начало итераций
    void start();

    void start(int population_size, float meal_prob, float poison_prob, int iter_count,
               int size_dna, int health, int count_changed_command) throws Exception;

    //Отрисовывает картинку на текущей итерации
    void draw();

    //Приостановка итераций
    void stop();


}
