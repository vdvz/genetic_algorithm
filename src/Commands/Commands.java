package Commands;

import java.util.*;

public class Commands {

    static private Map<Integer, Command_Interface> commands = new HashMap<Integer, Command_Interface>();

    static public void addCommand(int number, Command_Interface comm) {
        commands.put(number, comm);
    }

    static public int getCountCommand(){
        return commands.size();
    }

    static public Command_Interface getCommand(int number){
        return commands.get(number);
    }

}
