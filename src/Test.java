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
        Commands.addCommand(24, new UnconditionalStep_10());
        Commands.addCommand(25, new UnconditionalStep_11());
        Commands.addCommand(26, new UnconditionalStep_12());
        Commands.addCommand(27, new UnconditionalStep_13());
        Commands.addCommand(28, new UnconditionalStep_14());
        Commands.addCommand(29, new UnconditionalStep_15());
        Commands.addCommand(30, new UnconditionalStep_16());
        Commands.addCommand(31, new UnconditionalStep_17());
        Commands.addCommand(32, new UnconditionalStep_18());
        Commands.addCommand(33, new UnconditionalStep_19());
        Commands.addCommand(34, new UnconditionalStep_20());

        Gui_Interface.getInstance();

    }
}
