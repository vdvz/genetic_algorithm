package Environment;

import Entities.*;
import Gui.Gui_Interface;
import RandomGenerator.RandomGenerator;
import World.*;

import java.util.*;

public class Environment implements Environment_Interface{

    static final int POPULATION_SIZE = 10;
    static final float MEAL_PROBABILITY = 0.2f;
    static final int ITER_PER_GEN = 1000;
    private static final float POISON_PROBABILITY = 0.1f;
    final private ArrayList<Bot> population = new ArrayList<>();

    public Environment(){
    }

    private Object_Interface probabilityFunction(float probability, Object_Interface obj){
        return RandomGenerator.nextFloat() > probability ? obj_None.getInstance() : obj;
    }

    @Override
    public void addMeals(){
        int N = World.getInstance().getWorldSize();
        for(int i = 0; i<N; i++){
            for(int j =0; j<N; j++){
                if(World.getInstance().getObject(i, j) instanceof obj_None){
                    World.getInstance().setObject(i, j, probabilityFunction(MEAL_PROBABILITY, obj_Meal.getInstance()));
                }
            }
        }
    }

    @Override
    public void addPoison(){
        int N = World.getInstance().getWorldSize();
        for(int i = 0; i<N; i++){
            for(int j =0; j<N; j++){
                if(World.getInstance().getObject(i, j) instanceof obj_None){
                    World.getInstance().setObject(i, j, probabilityFunction(POISON_PROBABILITY, obj_Poison.getInstance()));
                }
            }
        }
    }

    @Override
    public void addBots(){
        //Добавление ботов на карту - генерируем рандомнуе позици по x и y
        //Если клетка свободна добавляем в нее бота у бота меняем координаты бота на заданые
        for(Bot bot: population){
            boolean isNotOkay = true;
            while(isNotOkay) {
                int x = RandomGenerator.nextInt(World.getInstance().getWorldSize());
                int y = RandomGenerator.nextInt(World.getInstance().getWorldSize());
                if (World.getInstance().getObject(x, y) instanceof obj_None) {
                    bot.set_x(x);
                    bot.set_y(y);
                    World.getInstance().setObject(x, y, bot);
                    isNotOkay = false;
                }
            }
        }
    }

    @Override
    public void getSlice(){
        Collections.sort(population);
        int size_population_slice = (int) Math.sqrt(POPULATION_SIZE);

        if(population.size() >= size_population_slice){
            population.removeAll(population.subList(size_population_slice, population.size()-1));
        } else {
            System.out.println("Sorry errors TODO print exception");
        }

    }

    @Override
    public void generateNewGeneration(){
        int size_population_slice = (int) Math.sqrt(POPULATION_SIZE);
        for(int i = 0; i<size_population_slice; i++){
            for(int j = 0; j<size_population_slice; j++){
                if(j != i){
                    population.add(new Bot(population.get(i), population.get(j)));
                }
            }
        }
    }

    @Override
    public void addNewGeneration(){
        World.getInstance().clearWorld();
        if(population.size()==0){
            for(int j = 0; j<POPULATION_SIZE; j++){
                population.add(new Bot(100));
            }
        } else {
            getSlice();
            generateNewGeneration();
        }
        addMeals();
        addPoison();
        addBots();
        Gui_Interface.getInstance();
        iteration();
    }

    @Override
    public void iteration() {
        for (int i = 0; i < ITER_PER_GEN; i++) {

            for (Bot bot : population) {
                if (bot.isAlive()) {
                    bot.itter();
                }
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Gui_Interface.getInstance().draw();
            //World.getInstance().printWorld();

        }
    }

}

