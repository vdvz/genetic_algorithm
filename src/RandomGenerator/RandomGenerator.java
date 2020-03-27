package RandomGenerator;

import java.util.Random;

public class RandomGenerator {

    private static Random gen = new Random();

    static public int nextInt(int diaposon){
        return gen.nextInt(diaposon);
    }


    static public float nextFloat(){
        return gen.nextFloat();
    }

}
