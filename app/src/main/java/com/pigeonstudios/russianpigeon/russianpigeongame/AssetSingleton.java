package com.pigeonstudios.russianpigeon.russianpigeongame;

import com.pigeonstudios.russianpigeon.framework.graphics.Pixmap;

import java.util.LinkedList;

/**
 * Created by DennisFedorchuk on 8/3/2016.
 */
public class AssetSingleton {
    public final static AssetSingleton instance = new AssetSingleton();

    private Pixmap loadingScreen;
    private Pixmap background;
    private Pixmap pigeon;
    private Pixmap startButton;
    private Pixmap enemy;
    private Pixmap seed;
    private Pixmap controlBall;

    private AssetSingleton(){}

    public Pixmap getSeed() {
        return seed;
    }

    public void setSeed(Pixmap seed) {
        this.seed = seed;
    }

    public Pixmap getLoadingScreen() {
        return loadingScreen;
    }

    public void setLoadingScreen(Pixmap loadingScreen) {
        this.loadingScreen = loadingScreen;
    }


    public Pixmap getEnemy() {
        return enemy;
    }

    public void setEnemy(Pixmap enemy) {
        this.enemy = enemy;
    }

    public Pixmap getPigeon() {
        return pigeon;
    }

    public Pixmap getBackground() {
        return background;
    }

    public void setBackground(Pixmap background) {
        this.background = background;
    }

    public void setPigeon(Pixmap pigeon) {
        this.pigeon = pigeon;
    }

    public Pixmap getStartButton() {
        return startButton;
    }

    public void setStartButton(Pixmap startButton) {
        this.startButton = startButton;
    }

    public Pixmap getControlBall() {
        return controlBall;
    }

    public void setControlBall(Pixmap controlBall) {
        this.controlBall = controlBall;
    }
}
