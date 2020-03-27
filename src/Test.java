import Entities.Bot;
import Entities.obj_Meal;
import Entities.obj_Stop;
import Entities.worldObject;
import World.World;
import commands.*;

public class Test {
    public static void main(String[] args) throws InterruptedException {

        Commands.addCommand(0, new Go_Up_Left());
        Commands.addCommand(1, new Go_Up());
        Commands.addCommand(2, new Go_Up_Right());
        Commands.addCommand(3, new Go_Down_Left());
        Commands.addCommand(4, new Go_Down_Right());
        Commands.addCommand(5, new Go_Down());
        Commands.addCommand(6, new Go_Right());
        Commands.addCommand(7, new Go_Left());

        Environment env = new Environment();
        env.itterateNewGeneration();

    }
}
