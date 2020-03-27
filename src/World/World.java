package World;
import Entities.obj_None;
import Entities.obj_Stop;
import Entities.worldObject;

public class World implements World_Interface {

    final int WORLD_SIZE = 10;

    public worldObject[][] world = new worldObject[WORLD_SIZE][WORLD_SIZE];

    private static World instance = new World();

    private World(){
    }

    public static World getInstance() {
        return instance;
    }

    @Override
    public void clearWorld(){
        for(int i = 0; i<WORLD_SIZE; i++){
            for(int j =0; j<WORLD_SIZE; j++) {
                if( i==0 || j==0 || i==WORLD_SIZE-1 || j==WORLD_SIZE-1){
                        getInstance().setObject(i, j, obj_Stop.getInstance());
                } else getInstance().setObject(i, j, obj_None.getInstance());
            }
        }
    }

    public void printWorld(){
        for(int i = 0; i<WORLD_SIZE; i++){
            for(int j =0; j<WORLD_SIZE; j++) {
                System.out.print(getInstance().getObject(i, j).getType() + " ");
            }
            System.out.println();
        }

    }

    @Override
    public int getWorldSize() {
        return WORLD_SIZE;
    }

    @Override
    public void setObject(int x, int y, worldObject obj) {
        world[x][y] = obj;
    }

    @Override
    public worldObject getObject(int x, int y) {
        return world[x][y];
    }

    @Override
    public void clearPossition(int x, int y) {
        world[x][y] = obj_None.getInstance();
    }

}
