package com.pigeonstudios.russianpigeon.gamelogic;


import android.graphics.Rect;

import com.pigeonstudios.russianpigeon.framework.Game;
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
        enemy = new Enemy(AssetSingleton.instance.getEnemy(), 0, 0);
        pigeon = new Pigeon(AssetSingleton.instance.getPigeon(), 1080-680, 1920-600);
    }

    public void update(float deltaTime) {
        pigeon.update();
        enemy.update();

        generateSeeds(deltaTime);
        updateSeeds();
        isCaught();

    }


    public void generateSeeds(float deltaTime) {
        time += deltaTime;
        if (time >= 1) {
            if (countSeeds>=10){
                seeds.removeFirst();
                seeds.add(new Seed(AssetSingleton.instance.getSeed(), enemy.getX(), enemy.getY() + 75));
            }else {
                seeds.add(new Seed(AssetSingleton.instance.getSeed(), enemy.getX(), enemy.getY() + 75));
                countSeeds++;
            }
            time = 0;
        }

    }

    public void updateSeeds() {
        for (Seed s : seeds) {
            s.update();
        }
    }

    public void isCaught() {
        //TODO can you make this shit better?
        for (Seed s : seeds) {
            if(getRect(pigeon).intersect(getRect(s))){
                s.catched = true;
                s.setNewLocation(3000,3000);
            }
        }
    }

    public boolean isSkiped(){
        for (Seed s : seeds) {
            if(s.getY()>1920 && s.catched == false){
                s.skiped = true;
                return true;
            }
        }
        return  false;
    }

    public Rect getRect(Drawable d){
        Rect r = new Rect(d.getX(),d.getY(),d.getX()+d.getPixmap().getWidth(),d.getY()+d.getPixmap().getHeight());
        return r;
    }


}
