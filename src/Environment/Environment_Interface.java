package Environment;

import Exceptions.NotConverge;

public interface Environment_Interface {

    void addBots();

    void getSlice() throws NotConverge;

    void generateNewGeneration();

    void addMeals();

    void addPoison();

    void addNewGeneration() throws NotConverge;

    void iteration();

}