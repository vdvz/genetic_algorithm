import Commands.*;
import Environment.Environment;
import Exceptions.NotConverge;
import Gui.Gui_Interface;

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
        Commands.addCommand(8, new Check_Up_Left());
        Commands.addCommand(9, new Check_Up());
        Commands.addCommand(10, new Check_Up_Right());
        Commands.addCommand(11, new Check_Down_Left());
        Commands.addCommand(12, new Check_Down_Right());
        Commands.addCommand(13, new Check_Down());
        Commands.addCommand(14, new Check_Right());
        Commands.addCommand(15, new Check_Left());
        Commands.addCommand(16, new Get_Up_Left());
        Commands.addCommand(17, new Get_Up());
        Commands.addCommand(18, new Get_Up_Right());
        Commands.addCommand(19, new Get_Down_Left());
        Commands.addCommand(20, new Get_Down_Right());
        Commands.addCommand(21, new Get_Down());
        Commands.addCommand(22, new Get_Right());
        Commands.addCommand(23, new Get_Left());

        Gui_Interface.getInstance();


    }
}
