package com.pigeonstudios.russianpigeon.gamelogic;


import android.graphics.Rect;

import com.pigeonstudios.russianpigeon.framework.Game;
import com.pigeonstudios.russianpigeon.russianpigeongame.Animation;
import com.pigeonstudios.russianpigeon.russianpigeongame.AssetSingleton;
import com.pigeonstudios.russianpigeon.russianpigeongame.Drawable;

import java.util.LinkedList;


/**
 * Created by tosch on 10.08.2016.
 */
public class GameWorld {
    public Enemy enemy;
    public Pigeon pigeon;
    Animation pigeonAnimation;

    public int countSeeds = 0;
    public float seedFrequency = 1;
    private int seedSpeed = 6;

    private int score = 0;
    public LinkedList<Seed> seeds = new LinkedList<Seed>();
    static private float time = 0;

    public GameWorld(Game game) {
        pigeonAnimation = new Animation(0.05f,game.getGraphics(), AssetSingleton.instance.getPigeon(),1,4);
        enemy = new Enemy(AssetSingleton.instance.getEnemy(), 390, 0);
        pigeon = new Pigeon(pigeonAnimation.getKeyFrame(0), 1080-680, 1920-600);
    }

    public void update(float deltaTime) {
        pigeon.update(pigeonAnimation, deltaTime);
        enemy.update();


        generateSeeds(deltaTime);
        updateSeeds();
        isCaught();

    }


    public void generateSeeds(float deltaTime) {
        /*if(score>=40){
            seedFrequency = 0.05f;
        }else if(score>=30){
            seedFrequency = 0.2f;
            seedSpeed = 50;
        }else if(score>=20){
            seedFrequency = 0.4f;
            seedSpeed = 15;
        }else if(score>=10){
            seedFrequency = 0.7f;
            seedSpeed = 9;
        }*/
        switch (score){
            case 0:
                seedFrequency = 1;
                break;
            case 10:
                seedFrequency = 0.8f;
                seedSpeed = 9;
                break;
            case 25:
                seedFrequency = 0.7f;
                seedSpeed = 12;
                break;
            case 40:
                seedFrequency = 0.6f;
                seedSpeed = 15;
                break;
        }
        time += deltaTime;
        if (time >= seedFrequency) {
            if (countSeeds>=10){
                seeds.removeFirst();
                seeds.add(new Seed(AssetSingleton.instance.getSeed(), enemy.getX(), enemy.getY() + 75, seedSpeed));
            }else {
                seeds.add(new Seed(AssetSingleton.instance.getSeed(), enemy.getX(), enemy.getY() + 75, seedSpeed));
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
                score++;
            }
        }
    }

    public boolean isSkipped(){
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

    public int getScore(){
        return score;
    }
}
