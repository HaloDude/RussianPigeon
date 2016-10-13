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

    public GameWorld(){
        enemy = new Enemy(AssetSingleton.instance.getEnemy(), 0, 0);
        pigeon = new Pigeon(AssetSingleton.instance.getPigeon(), 300, 1000);
    }

    public void update(float deltaTime){
        pigeon.move();
        enemy.move();
        generateSeeds(deltaTime);
        moveSeeds();
        //isCatched();

    }


    public void generateSeeds(float deltaTime){
        time += deltaTime;
        if(time>=1){
            seeds.add(new Seed(AssetSingleton.instance.getSeed(), 70, 70));
            countSeeds++;
            time = 0;
        }
    }

    public void moveSeeds(){
        for(Seed s : seeds){
            s.move();
        }
    }

    public void isCatched(){
        //TODO need to check if seed was catched by pigeon
        //testing code
        for(Seed s : seeds){
            if(s.getY()>1800) {
                s.kill = true;
            }
            if(!s.kill){
                s.move();
            }else{
                s.moveUp();
            }
        }
        //=====================
    }
}
