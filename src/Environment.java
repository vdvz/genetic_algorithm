import javafx.util.Pair;

import java.util.*;

public class Environment{

    static final int POPULATION_SIZE = 64;
    static final float MEAL_PROBABILITY = 0.35f;
    static final private ArrayList<Bot> population = new ArrayList<>();

    Environment(){

    }

    private int probabilityFunction(){
        return RandomGenerator.nextFloat() > MEAL_PROBABILITY ? 0 : 2;
    }

    private void addMeals(){
        for(int i = 0; i<World.WORLD_SIZE; i++){
            for(int j =0; j<World.WORLD_SIZE; j++){
                if(World.world[i][j]==0){
                    World.world[i][j] = probabilityFunction();
                }
            }
        }
    }

    private void addBots(){
        //Добавление ботов на карту - генерируем рандомнуе позици по x и y
        //Если клетка свободна добавляем в нее бота у бота меняем координаты бота на заданые
        for(Bot bot: population){
            boolean isNotOkay = true;
            while(isNotOkay) {
                int x = RandomGenerator.nextInt(World.WORLD_SIZE);
                int y = RandomGenerator.nextInt(World.WORLD_SIZE);
                if (World.world[x][y] == 0) {
                    bot.set_x(x);
                    bot.set_y(y);
                    World.world[x][y] = 1;
                    isNotOkay = false;
                }
            }
        }
    }

    private void getSlice(){
        Collections.sort(population);
        int size_population_slice = (int) Math.sqrt(POPULATION_SIZE);

        if(population.size() >= size_population_slice){
            population.removeAll(population.subList(size_population_slice, population.size()-1));
        } else {
            System.out.println("Sorry errors TODO print exception");
        }
    }

    private void generateNewGeneration(){
        int size_population_slice = (int) Math.sqrt(POPULATION_SIZE);
        for(int i = 0; i<size_population_slice; i++){
            for(int j = 0; j<size_population_slice; j++){
                if(j != i){
                    population.add(new Bot(population.get(i), population.get(j)));
                }
            }
        }
    }

    public void itterateNewGeneration(){
        World.clearWorld();
        if(population.size()==0){
            for(int j = 0; j<POPULATION_SIZE; j++){
                population.add(new Bot(100));
            }
        } else {
            getSlice();
            generateNewGeneration();
        }
        addMeals();
        addBots();



    }

    public void itteration(){
        //Проходит через все объекты если клетка жива устанавливает ее здоровье иначе удаляет ее из карты
        for(Bot bot: population){
            if(!bot.itter()){
                population.remove(bot);
            }
        }
    }

}

