package com.pigeonstudios.russianpigeon.gamelogic;

import com.pigeonstudios.russianpigeon.framework.graphics.Graphics;
import com.pigeonstudios.russianpigeon.russianpigeongame.AssetSingleton;
import com.pigeonstudios.russianpigeon.framework.Game;

import java.util.LinkedList;

/**
 * Created by tosch on 10.08.2016.
 */
public class GameWorld {
    //TODO need better algoritm to generate seeds and remove it after fall
    public Enemy enemy;
    public int countSeeds=0;
    public int seedSpeed = 0;
    public LinkedList<Seed> seeds = new LinkedList<Seed>();

    public void update(){
        this.enemy.move();
        if(seedSpeed%100 == 0){
            seeds.add(new Seed(AssetSingleton.instance.getSeed(), enemy.getX(), enemy.getY()));
            countSeeds++;
        }
        seedSpeed++;
        for(Seed s : seeds){
            s.move();
        }
        //this.seed.move();
    }
}
