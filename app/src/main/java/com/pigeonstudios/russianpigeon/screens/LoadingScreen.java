package com.pigeonstudios.russianpigeon.screens;

import com.pigeonstudios.russianpigeon.framework.Game;
import com.pigeonstudios.russianpigeon.framework.Screen;
import com.pigeonstudios.russianpigeon.framework.graphics.Graphics;
import com.pigeonstudios.russianpigeon.russianpigeongame.AssetSingleton;

/**
 * Created by DennisFedorchuk on 10/19/2016.
 */
public class LoadingScreen extends Screen {
    private boolean finishedLoading = false;
    private boolean finishedAnimation = false;
    int i = 0;

    public LoadingScreen(Game game) {
        super(game);
        //load assets here
        AssetSingleton.instance.setLoadingScreen(game.getGraphics().newScaledPixmap("Screens/loadingScreen.jpg", Graphics.PixmapFormat.RGB565, 1080, 1920));
        AssetSingleton.instance.setEnemy(game.getGraphics().newScaledPixmap("Sprites/Enemy.png", Graphics.PixmapFormat.RGB565, 300, 300));
        AssetSingleton.instance.setSeed(game.getGraphics().newScaledPixmap("Sprites/seed.png", Graphics.PixmapFormat.RGB565, 35, 70));
        AssetSingleton.instance.setBackground(game.getGraphics().newScaledPixmap("Screens/Background.jpg", Graphics.PixmapFormat.RGB565, 1080, 1920));
        AssetSingleton.instance.setPigeon(game.getGraphics().newScaledPixmap("Sprites/pigeonSprite.png", Graphics.PixmapFormat.RGB565, 1000,250));
        AssetSingleton.instance.setMenuScreen(game.getGraphics().newPixmap("Screens/menuScreen.jpg", Graphics.PixmapFormat.RGB565));
        AssetSingleton.instance.setMenuButton(game.getGraphics().newPixmap("Buttons/menuButton.png", Graphics.PixmapFormat.ARGB4444));
        AssetSingleton.instance.setRestartButton(game.getGraphics().newPixmap("Buttons/restartButton.png", Graphics.PixmapFormat.ARGB4444));
        AssetSingleton.instance.setResumeButton(game.getGraphics().newPixmap("Buttons/resumeButton.png", Graphics.PixmapFormat.ARGB4444));
        AssetSingleton.instance.setPauseButton(game.getGraphics().newScaledPixmap("Buttons/pauseButton.png", Graphics.PixmapFormat.ARGB4444, 100, 100));
        AssetSingleton.instance.setStartButton(game.getGraphics().newScaledPixmap("Buttons/StartButton.png", Graphics.PixmapFormat.ARGB4444, 500, 500));
        AssetSingleton.instance.setMusicButton(game.getGraphics().newScaledPixmap("Buttons/musicButton.png", Graphics.PixmapFormat.ARGB4444, 250, 250));
        AssetSingleton.instance.setSoundButton(game.getGraphics().newScaledPixmap("Buttons/soundButton.png", Graphics.PixmapFormat.ARGB4444, 250, 250));
        AssetSingleton.instance.setRecordButton(game.getGraphics().newScaledPixmap("Buttons/recordButton.png", Graphics.PixmapFormat.ARGB4444, 250, 250));
        AssetSingleton.instance.setReady(game.getGraphics().newScaledPixmap("Menu/ready.png", Graphics.PixmapFormat.RGB565, 1000, 400));
        AssetSingleton.instance.setGameOver(game.getGraphics().newPixmap("Menu/gameOver.png", Graphics.PixmapFormat.RGB565));
        //=================//
        finishedLoading = true;
    }

    @Override
    public void update(float deltaTime) {
        i++;
        if(i==150) finishedAnimation = true;

        if(finishedLoading && finishedAnimation)
            game.setScreen(new MenuScreen(game));
    }

    @Override
    public void draw(float deltaTime) {
        game.getGraphics().drawPixmap(AssetSingleton.instance.getLoadingScreen(),0,0);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
