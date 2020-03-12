import commands.Command_Interface;
import commands.Commands;

public class Bot implements Comparable<Bot>{

    final private int SIZE_COMMANDS = 20;
    final private int SIZE_DNA = 20;
    final private int COUNT_CHANGED_COMMANDS = 20;
    private int position_x;
    private int position_y;
    public int[] dna = new int[SIZE_DNA];
    private int health;
    int itter_head = 0;

    Bot(int health){
        this.health = health;
        makeDna();
    }

    Bot(Bot bot_1, Bot bot_2){
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

    private void makeDna(){
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
    }

    private boolean isAlive(){
        return health>0;
    }

    private void die(){
        World.world[position_x][position_y] = 0;
    }

    public boolean itter(){
        //Пока не выполнится завершающая команда либо пока не выполнится 10 команд либо
        //пока бот жив выполняем команду обновляем состояние бота
        //сдвигаем головку команды
        int count = 10;
        boolean isEndCommand = false;
        while(!isEndCommand || count!=0 || isAlive()){
            Command_Interface command = Commands.getCommand(dna[itter_head]);
            isEndCommand = command.action(position_x, position_y, health);
            updateParam(command);
            count--;
        }

        if(!isAlive()){
            die();
        }

        return isAlive();
    }

    @Override
    public int compareTo(Bot o) {
        return Integer.compare(health, o.health);
    }

}
