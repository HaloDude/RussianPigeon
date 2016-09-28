package com.pigeonstudios.russianpigeon.gamelogic;


import com.pigeonstudios.russianpigeon.russianpigeongame.AssetSingleton;
import java.util.LinkedList;

/**
 * Created by tosch on 10.08.2016.
 */
public class GameWorld {
    //TODO need better algoritm to generate seeds and remove it after fall
    public Enemy enemy;
    public int countSeeds=0;
    public int seedPeriod = 0;
    public LinkedList<Seed> seeds = new LinkedList<Seed>();

    public void update(){
        this.enemy.move();
        if(seedPeriod%50 == 0){
            seeds.add(new Seed(AssetSingleton.instance.getSeed(), enemy.getX(), enemy.getY()));
            countSeeds++;
        }
        seedPeriod++;

        //testing code
        for(Seed s : seeds){
            if(s.getY()>1200) {
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

    public void checkSeed(){
        for(Seed s : seeds){
            if(s.getY()>1400){
                s.moveUp();
            }
        }
    }

    public void isCatched(){
        //TODO need to check if seed was catched by pigeon
    }
}
