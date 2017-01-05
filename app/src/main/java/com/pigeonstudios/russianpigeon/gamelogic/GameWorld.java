package com.pigeonstudios.russianpigeon.gamelogic;


import android.graphics.Rect;

import com.pigeonstudios.russianpigeon.framework.Game;
import com.pigeonstudios.russianpigeon.framework.graphics.Graphics;
import com.pigeonstudios.russianpigeon.russianpigeongame.Animation;
import com.pigeonstudios.russianpigeon.russianpigeongame.AssetSingleton;
import com.pigeonstudios.russianpigeon.russianpigeongame.Drawable;

import java.util.LinkedList;


/**
 * Created by tosch on 10.08.2016.
 */
public class GameWorld {
    Game game;

    public Enemy enemy;
    public Pigeon pigeon;
    public Seeds seeds;

    private int score = 0;

    public GameWorld(Game game) {
        this.game = game;
        enemy = new Enemy(AssetSingleton.instance.getEnemy(), 390, 0, game.getGraphics());
        pigeon = new Pigeon(AssetSingleton.instance.getPigeon(), 1080-680, 1920-600, game.getGraphics() );
    }

    public void update(float deltaTime) {
        pigeon.update(deltaTime);
        enemy.update(deltaTime);
        seeds.update(deltaTime, enemy.getX(), enemy.getY());
        isSeedCaught();

    }

    public void isSeedCaught() {
        //TODO can you make this shit better?
        for (Seed s : seeds.getSeeds()) {
            if(pigeon.getRectangle().intersect(s.getRectangle())){
                s.catched = true;
                s.setNewLocation(3000,3000);
                score++;
                seeds.seedCaught();
            }
        }
    }
    public boolean isSkipped(){
        for (Seed s : seeds.getSeeds()) {
            if(s.getY()>1920 && s.catched == false){
                s.skiped = true;
                //return true;
                return false;
            }
        }
        return  false;
    }



    public int getScore(){
        return score;
    }
}
