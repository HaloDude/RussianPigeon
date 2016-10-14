package com.pigeonstudios.russianpigeon.gamelogic;


import android.graphics.Rect;

import com.pigeonstudios.russianpigeon.russianpigeongame.AssetSingleton;
import com.pigeonstudios.russianpigeon.russianpigeongame.Drawable;

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
        isCatched();

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
            if(getRect(pigeon).contains(getRect(s))){
                s.setNewLocation(3000,3000);
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

    public Rect getRect(Drawable d){
        Rect r = new Rect(d.getX(),d.getY(),d.getX()+d.getPixmap().getWidth(),d.getY()+d.getPixmap().getHeight());
        return r;
    }


}
