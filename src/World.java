import java.util.ArrayList;

public class World {

    static final int WORLD_SIZE = 10;

    static public int[][] world = new int[WORLD_SIZE][WORLD_SIZE];

    public static void clearWorld(){
        for(int i = 0; i<WORLD_SIZE; i++){
            for(int j =0; j<WORLD_SIZE; j++) {
                if( i==0 || j==0 || i==WORLD_SIZE-1 || j==WORLD_SIZE-1){
                    world[i][j] = -1;
                } else world[i][j] = 0;
            }
        }
    }

}
