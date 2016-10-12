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
    public int seedPeriod = 0;
    public LinkedList<Seed> seeds = new LinkedList<Seed>();

    public GameWorld(){
    }


    public void update(float deltaTime){
        this.pigeon.move();
        this.enemy.move();
        generateSeeds(0);
        isCatched();

    }

    public void checkSeed(){
        for(Seed s : seeds){
            if(s.getY()>1400){
                s.moveUp();
            }
        }
    }

    public void generateSeeds(double time){
        //TODO need to check delta time and generate seeds
        if(seedPeriod%50 == 0){
            seeds.add(new Seed(AssetSingleton.instance.getSeed(), enemy.getX(), enemy.getY()));
            countSeeds++;
        }
        seedPeriod++;
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
