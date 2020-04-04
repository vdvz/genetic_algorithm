package Entities;
import World.World;
import Commands.Command_Interface;
import RandomGenerator.RandomGenerator;
import Commands.Commands;

public class Bot implements Comparable<Bot>, Object_Interface, Bot_Interface{

    final private static Integer type = 1;
    private int SIZE_COMMANDS = Commands.getCountCommand();
    private int SIZE_DNA = 60;
    private int COUNT_CHANGED_COMMANDS = 20;
    private int position_x;
    private int position_y;
    public int[] dna;
    private int health;
    int itter_head = 0;

    public String getDnaString(){
        StringBuilder s = new StringBuilder("");
        for(int i = 0; i < SIZE_DNA; i++ ){
            s.append(dna[i]).append(" ");
        }
        return s.toString();
    }

    public Bot(int health){
        this.health = health;
        dna = new int[SIZE_DNA];
        makeDna();
    }

    public Bot(Bot bot_1){
        SIZE_DNA = bot_1.SIZE_DNA;
        COUNT_CHANGED_COMMANDS = bot_1.COUNT_CHANGED_COMMANDS;
        dna = new int[SIZE_DNA];
        System.arraycopy(bot_1.dna, 0, dna, 0, SIZE_DNA);
        health = bot_1.health;
        mutationDna();
    }

    public Bot(int dna_size, int count_mutate_dna, int health){
        SIZE_DNA = dna_size;
        COUNT_CHANGED_COMMANDS = count_mutate_dna;
        this.health = health;
        dna = new int[SIZE_DNA];
        makeDna();
    }

    public Bot(Bot bot_1, Bot bot_2){
        SIZE_DNA = bot_1.SIZE_DNA;
        COUNT_CHANGED_COMMANDS = bot_1.COUNT_CHANGED_COMMANDS;
        dna = new int[SIZE_DNA];
        for(int i = 0; i<SIZE_DNA; i++){
            if (RandomGenerator.nextInt(100) % 2 == 0) {
                dna[i] = bot_1.dna[i];
            }else{
                dna[i] = bot_2.dna[i];
            }
        }
        mutationDna();
        health = (bot_1.health+bot_2.health)/2;
    }

    public void set_x(int x){
        position_x = x;
    }

    public void set_y(int y){
        position_y = y;
    }

    public void makeDna(){
        //fill in dna list form commands(integers from 0 to SIZE_COMMANDS), describes in MAP(TODO)
        for(int i = 0; i<SIZE_DNA; i++){
            dna[i] = RandomGenerator.nextInt(SIZE_COMMANDS);
        }
    }

    public void mutationDna(){
        //Change random count_changed_commands to random commands
        for(int i = 0; i < COUNT_CHANGED_COMMANDS; i++){
            dna[RandomGenerator.nextInt(SIZE_DNA)] = RandomGenerator.nextInt(SIZE_COMMANDS);
        }
    }

    private void updateParam(Command_Interface comm){
        itter_head = (itter_head + comm.getShiftHead()) % SIZE_DNA;
        health += comm.getDeltaHealth();
        position_x = comm.get_x();
        position_y = comm.get_y();

        //System.out.println("PossitionX: " + position_x + " PossitionY: " + position_y);

    }

    public boolean isAlive(){
        if(health<=0){
            die();
            return false;
        }
        return true;
    }

    public void die(){
        //System.out.println("Bot die: " + health);
        World.getInstance().clearPossition(position_x, position_y);
    }

    public void itter(){
        //Пока не выполнится завершающая команда либо пока не выполнится 10 команд либо
        //пока бот жив выполняем команду обновляем состояние бота
        //сдвигаем головку команды
        int count = 10;
        boolean isEndCommand = false;
        while(!isEndCommand && count!=0){
            if(!isAlive()){
                break;
            }
            Command_Interface command = Commands.getCommand(dna[itter_head]);
            isEndCommand = command.action(this, position_x, position_y, health);
            updateParam(command);
            count--;
        }
    }

    @Override
    public int compareTo(Bot o) {
        return Integer.compare(health, o.health);
    }


    @Override
    public Integer getType() {
        return type;
    }

}
