package Environment;

import Entities.Bot;
import Exceptions.NotConverge;

import java.util.ArrayList;

public interface Environment_Interface {

    void addBots();

    void getSlice() throws NotConverge;

    void generateNewGeneration();

    void addMeals();

    void addPoison();

    void addNewGeneration() throws NotConverge;

    void iteration();

    ArrayList<Bot> getBotList();
}