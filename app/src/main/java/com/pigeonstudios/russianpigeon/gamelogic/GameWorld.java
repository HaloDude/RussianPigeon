package com.pigeonstudios.russianpigeon.gamelogic;


import com.pigeonstudios.russianpigeon.russianpigeongame.AssetSingleton;
import java.util.LinkedList;

/**
 * Created by tosch on 10.08.2016.
 */
public class GameWorld {
    public Enemy enemy;
    public Pigeon pigeon;
    public int countSeeds = 0;
    public LinkedList<Seed> seeds = new LinkedList<Seed>();
    static private float time = 0;

    public GameWorld() {

    }

    public void update(float deltaTime) {
        pigeon.move();
        enemy.move();

        generateSeeds(deltaTime);
        moveSeeds();
        //isCatched();

    }


    public void generateSeeds(float deltaTime) {
        time += deltaTime;
        if (time >= 1) {
            seeds.add(new Seed(AssetSingleton.instance.getSeed(), enemy.getX(), enemy.getY() + 75));
            countSeeds++;
            time = 0;
        }
    }

    public void moveSeeds() {
        for (Seed s : seeds) {
            s.move();
        }
    }

    public void isCatched() {
        //TODO need to check if seed was catched by pigeon
        //testing code

        for (Seed s : seeds) {
            if ((Math.abs(s.getX() - pigeon.getX()) <= (35 + 300) / 2f) && (Math.abs(s.getY() - pigeon.getY()) <= (70 + 300) / 2f)) {
                s.setNewLocation(0,0);
            }
            /*
            if(s.getY()>1800) {
                s.kill = true;
            }
            if(!s.kill){
                s.move();
            }else{
                s.moveUp();
            }
        }*/
            //=====================
        }
    }
}
