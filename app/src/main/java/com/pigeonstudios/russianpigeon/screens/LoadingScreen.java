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
        AssetSingleton.instance.setLoadingScreen(game.getGraphics().newScaledPixmap("Menu/loadingScreen.jpg", Graphics.PixmapFormat.RGB565, 1080, 1920));
        AssetSingleton.instance.setEnemy(game.getGraphics().newScaledPixmap("Sprites/Enemy.png", Graphics.PixmapFormat.RGB565, 300, 300));
        AssetSingleton.instance.setSeed(game.getGraphics().newScaledPixmap("Sprites/seed.png", Graphics.PixmapFormat.RGB565, 35, 70));
        AssetSingleton.instance.setBackground(game.getGraphics().newScaledPixmap("Sprites/Background.jpg", Graphics.PixmapFormat.RGB565, 1080, 1920));
        AssetSingleton.instance.setPigeon(game.getGraphics().newScaledPixmap("Sprites/Pigeon.png", Graphics.PixmapFormat.RGB565, 250,250));
        AssetSingleton.instance.setMenuScreen(game.getGraphics().newPixmap("Menu/menuScreen.jpg", Graphics.PixmapFormat.RGB565));
        AssetSingleton.instance.setStartButton(game.getGraphics().newScaledPixmap("Menu/StartButton.png", Graphics.PixmapFormat.ARGB4444, 300, 200));
        AssetSingleton.instance.setReady(game.getGraphics().newPixmap("Menu/ready.png", Graphics.PixmapFormat.RGB565));
        AssetSingleton.instance.setGameOver(game.getGraphics().newPixmap("Menu/gameOver.png", Graphics.PixmapFormat.RGB565));
        //=================//
        finishedLoading = true;
    }

    @Override
    public void update(float deltaTime) {
        i++;
        if(i==200) finishedAnimation = true;

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
