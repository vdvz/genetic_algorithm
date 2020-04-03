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
    public int[] dna = new int[SIZE_DNA];
    private int health;
    int itter_head = 0;

    public Bot(int health){
        this.health = health;
        makeDna();
    }

    public Bot(int dna_size, int count_mutate_dna, int health){
        SIZE_DNA = dna_size;
        COUNT_CHANGED_COMMANDS = count_mutate_dna;
        this.health = health;
        makeDna();
    }

    public Bot(Bot bot_1, Bot bot_2){
        for(int i = 0; i<SIZE_DNA; i++){
            if (RandomGenerator.nextInt(100) % 2 == 0) {
                dna[i] = bot_1.dna[i];
            }else{
                dna[i] = bot_2.dna[i];
            }
        }
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
        for(int i = 0; i< COUNT_CHANGED_COMMANDS; i++){
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
        return health>0;
    }

    public void die(){
        System.out.println("Bot die: " + health);
        World.getInstance().clearPossition(position_x, position_y);
    }

    public void itter(){
        //Пока не выполнится завершающая команда либо пока не выполнится 10 команд либо
        //пока бот жив выполняем команду обновляем состояние бота
        //сдвигаем головку команды
        int count = 10;
        boolean isEndCommand = false;
        while(!isEndCommand && count!=0 && isAlive()){
            Command_Interface command = Commands.getCommand(dna[itter_head]);
            isEndCommand = command.action(this, position_x, position_y, health);
            //System.out.println("Command: " + dna[itter_head]);
            //System.out.println("IsEndCommand: " + isEndCommand + " Count: " + count + "IsALIVE: " + isAlive());
            updateParam(command);
            count--;
        }

        if(!isAlive()){
            die();
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
