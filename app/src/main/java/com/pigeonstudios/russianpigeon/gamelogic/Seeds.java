package com.pigeonstudios.russianpigeon.gamelogic;

import com.pigeonstudios.russianpigeon.framework.graphics.Graphics;
import com.pigeonstudios.russianpigeon.russianpigeongame.AssetSingleton;

import java.util.LinkedList;

/**
 * Created by DennisFedorchuk on 1/2/2017.
 */

public class Seeds {
    private LinkedList<Seed> seeds = new LinkedList<>();
    private float seedFrequency = 1;
    private int seedSpeed = 6;
    static private float time = 0;

    private int nextSeedX;
    private int nextSeedY;

    private int countSeeds = 0;
    private int caught = 0;

    private Graphics g;

    public Seeds(int nextSeedX, int nextSeedY, Graphics g){
        this.nextSeedX = nextSeedX;
        this.nextSeedY = nextSeedY;
        this.g = g;
    }

    public void generateSeeds(float deltaTime) {
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
        for (int i = 0; i<seeds.size(); i++) {
            seeds.get(i).update(deltaTime);
        }
    }

    public void update(float deltaTime, int x, int y) {
        this.nextSeedX = x; //next seed location (Where the enemy is)
        this.nextSeedY = y; //next seed location (Where the enemy is)

        generateSeeds(deltaTime);
        updateSeeds(deltaTime);

    }

    public LinkedList<Seed> getSeeds(){
        return seeds;
    }

    /**
     * THis method is used to keep score of how many seeps were caught
     */
    public void seedCaught(int positionInList){
        seeds.remove(positionInList);
        countSeeds--;
        caught++;
    }
}
