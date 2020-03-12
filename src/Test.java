import commands.*;

public class Test {
    public static void main(String[] args) {

        Commands.addCommand(0,new Go_Up());
        Commands.addCommand(1,new Go_Up_Right());
        Commands.addCommand(2,new Go_Right());
        Commands.addCommand(3,new Go_Down_Right());
        Commands.addCommand(4,new Go_Down());
        Commands.addCommand(5,new Go_Down_Left());
        Commands.addCommand(6,new Go_Left());
        Commands.addCommand(7,new Go_Up_Left());





        for(int i = 0;i<World.WORLD_SIZE;i++){
            for(int j = 0;j<World.WORLD_SIZE;j++){
                System.out.print(World.world[i][j] + " ");
            }
            System.out.println();
        }

    }
}
