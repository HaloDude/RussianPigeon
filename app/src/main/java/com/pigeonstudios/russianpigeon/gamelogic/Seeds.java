package com.pigeonstudios.russianpigeon.gamelogic;

import com.pigeonstudios.russianpigeon.framework.graphics.Graphics;
import com.pigeonstudios.russianpigeon.russianpigeongame.AssetSingleton;

import java.util.LinkedList;

/**
 * Created by DennisFedorchuk on 1/2/2017.
 */

public class Seeds {
    private LinkedList<Seed> seeds = new LinkedList<Seed>();
    private float seedFrequency = 1;
    private int seedSpeed = 6;
    static private float time = 0;

    private int nextSeedX;
    private int nextSeedY;

    private int countSeeds = 0;
    private int caught = 0;

    Graphics g;

    public Seeds(int nextSeedX, int nextSeedY, Graphics g){
        this.nextSeedX = nextSeedX;
        this.nextSeedY = nextSeedY;
        this.g = g;
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
        switch (caught){
            case 0:
                seedFrequency = 1;
                break;
            case 10:
                seedFrequency = 0.8f;
                seedSpeed = 9;
                break;
            case 25:
                seedFrequency = 0.6f;
                seedSpeed = 13;
                break;
            case 40:
                seedFrequency = 0.4f;
                seedSpeed = 15;
                break;
        }
        time += deltaTime;
        if (time >= seedFrequency) {
            if (countSeeds>=10){
                seeds.removeFirst();
                seeds.add(new Seed(AssetSingleton.instance.getSeed(), nextSeedX, nextSeedY + 75, seedSpeed, g));
            }else {
                seeds.add(new Seed(AssetSingleton.instance.getSeed(), nextSeedX, nextSeedY + 75, seedSpeed, g));
                countSeeds++;
            }
            time = 0;
        }

    }

    public void updateSeeds(float deltaTime) {
        for (Seed s : seeds) {
            s.update(deltaTime);
        }
    }

    public void update(float deltaTime, int x, int y) {
        this.nextSeedX = x;
        this.nextSeedY = y;

        generateSeeds(deltaTime);
        updateSeeds(deltaTime);

    }

    public LinkedList<Seed> getSeeds(){
        return seeds;
    }

    public void seedCaught(){
        caught++;
    }
}
