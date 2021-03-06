package com.pigeonstudios.russianpigeon.russianpigeongame;

import com.pigeonstudios.russianpigeon.framework.graphics.Pixmap;

/**
 * Created by DennisFedorchuk on 8/3/2016.
 */
public class AssetSingleton {
    public final static AssetSingleton instance = new AssetSingleton();

    private Pixmap loadingScreen;
    private Pixmap menuScreen;
    private Pixmap background;
    private Pixmap pigeon;
    private Pixmap startButton;
    private Pixmap restartButton;
    private Pixmap recordButton;
    private Pixmap resumeButton;
    private Pixmap pauseButton;
    private Pixmap menuButton;
    private Pixmap musicButton;
    private Pixmap soundButton;
    private Pixmap enemy;
    private Pixmap seed;
    private Pixmap controlBall;
    private Pixmap ready;
    private Pixmap gameOver;
    private Pixmap live;

    private AssetSingleton(){}

    public Pixmap getRecordButton() {
        return recordButton;
    }

    public void setRecordButton(Pixmap recordButton) {
        this.recordButton = recordButton;
    }

    public Pixmap getLive() {
        return live;
    }

    public void setLive(Pixmap live) {
        this.live = live;
    }

    public Pixmap getMenuButton() {
        return menuButton;
    }

    public Pixmap getSoundButton() {
        return soundButton;
    }

    public void setSoundButton(Pixmap soundButton) {
        this.soundButton = soundButton;
    }

    public Pixmap getMusicButton() {
        return musicButton;
    }

    public void setMusicButton(Pixmap musicButton) {
        this.musicButton = musicButton;
    }

    public Pixmap getPauseButton() {
        return pauseButton;
    }

    public void setPauseButton(Pixmap pauseButton) {
        this.pauseButton = pauseButton;
    }

    public Pixmap getResumeButton() {
        return resumeButton;
    }

    public void setResumeButton(Pixmap resumeButton) {
        this.resumeButton = resumeButton;
    }

    public void setMenuButton(Pixmap menuButton) {
        this.menuButton = menuButton;
    }

    public Pixmap getRestartButton() {
        return restartButton;
    }

    public void setRestartButton(Pixmap restartButton) {
        this.restartButton = restartButton;
    }

    public Pixmap getGameOver() {
        return gameOver;
    }

    public void setGameOver(Pixmap gameOver) {
        this.gameOver = gameOver;
    }

    public Pixmap getReady() {
        return ready;
    }

    public void setReady(Pixmap ready) {
        this.ready = ready;
    }

    public Pixmap getMenuScreen() {
        return menuScreen;
    }

    public void setMenuScreen(Pixmap menuScreen) {
        this.menuScreen = menuScreen;
    }

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
