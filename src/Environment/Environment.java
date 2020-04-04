package Environment;

import Entities.*;
import Exceptions.NotConverge;
import Gui.Gui_Interface;
import RandomGenerator.RandomGenerator;
import World.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import static java.lang.System.exit;

public class Environment implements Environment_Interface{

    private int POPULATION_SIZE = 10;
    private float MEAL_PROBABILITY = 0.2f;
    private int ITER_PER_GEN = 1000;
    private float POISON_PROBABILITY = 0.1f;
    int delay = 100;
    ActionListener actionListener;
    final private ArrayList<Bot> population = new ArrayList<>();

    public Environment(){
        createBots(60, 60, 100);

        Gui_Interface.getInstance().getTimer().addActionListener(createActionListnerForTimer());
    }

    public Environment(int population_size, float meal_prob, float poison_prob, int iter_count,
                       int size_dna, int health, int count_changed_command) throws Exception {
        if(population_size < 1 ||population_size > (World.getInstance().getWorldSize()-2) * (World.getInstance().getWorldSize()-2))
            throw new Exception("Неверный размер популяции, WORLD_SIZE: " + World.getInstance().getWorldSize());
        POPULATION_SIZE = population_size;
        if(meal_prob<0) throw new Exception("Нвееврное значение вероятности пищи");
        MEAL_PROBABILITY = meal_prob;
        if(size_dna < count_changed_command) throw new Exception("Количество мутированных клеток днк должно быть меньше или равно размеру днк");
        if(poison_prob<0) throw new Exception("Нвееврное значение вероятности яда");
        POISON_PROBABILITY = poison_prob;
        if(iter_count<0) throw new Exception("Нвееврное значение количество итераций");
        ITER_PER_GEN = iter_count;
        createBots(size_dna, count_changed_command, health);

        Gui_Interface.getInstance().getTimer().addActionListener(createActionListnerForTimer());

    }

    public ActionListener createActionListnerForTimer(){
        return new ActionListener() {
            int iter = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Bot bot : population) {
                    if (bot.isAlive()) {
                        bot.itter();
                    }
                }

                Gui_Interface.getInstance().draw();
                iter+=1;
                if(iter > ITER_PER_GEN){
                    Gui_Interface.getInstance().getTimer().stop();
                    iter = 0;
                    Gui_Interface.getInstance().newGeneration();
                }
            }
        };
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

    public void createBots(int dna_size, int changed_command, int health){
        for(int j = 0; j<POPULATION_SIZE; j++){
            population.add(new Bot(dna_size, changed_command, health));
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
        /*Добавление ботов на карту в свободные клетки*/
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
    public void getSlice() throws NotConverge {
        /*Сортирует список ботов, а после выбирает срез самых сильных, если ботов не достаточно для среза дублирует
        * самого сильного, если ботов нет совсем, то алгоритм не сошелся.*/

        population.removeIf(b -> !b.isAlive());

        Collections.sort(population);
        Gui_Interface.getInstance().write_message("Alive population: " + population.size());
        int size_population_slice = (int) Math.floor(Math.sqrt(POPULATION_SIZE));

        for (Bot bot : population) {
            bot.setHealth(100);
        }

        if(population.size()<1){
            System.out.println("The algorithm did not converge");
            throw new NotConverge("Too little bot's after iterate generation!");
        }


    }

    @Override
    public void generateNewGeneration(){

        if(population.size() > 1){
            Iterator<Bot> i = population.iterator();
            Iterator<Bot> j = population.iterator();
            while(i.hasNext())
                while(j.hasNext()){
                    Bot b1 = i.next();
                    Bot b2 = j.next();
                    if(b1!=b2){
                        population.add(new Bot(b1, b2));
                    }
                    if(population.size()==POPULATION_SIZE) break;
                }
        }

        while(population.size() < POPULATION_SIZE){
            population.add(new Bot(population.iterator().next()));
        }

        Gui_Interface.getInstance().write_message("New population: " + population.size());

    }

    @Override
    public void addNewGeneration() throws NotConverge {
        World.getInstance().clearWorld();
        getSlice();
        generateNewGeneration();
        iteration();
    }

    @Override
    public void iteration() {
        addBots();
        addMeals();
        addPoison();

        Gui_Interface.getInstance().getTimer().setDelay(delay);
        Gui_Interface.getInstance().getTimer().start();

    }

    public ArrayList<Bot> getBotList() {
        return population;
    }
}

