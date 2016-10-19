package com.pigeonstudios.russianpigeon.russianpigeongame;

import com.pigeonstudios.russianpigeon.framework.Game;
import com.pigeonstudios.russianpigeon.framework.Screen;
import com.pigeonstudios.russianpigeon.framework.graphics.Graphics;

/**
 * Created by DennisFedorchuk on 10/19/2016.
 */
public class LoadingScreen extends Screen {
    private boolean finishedLoading = false;
    private boolean finishedAnimation = false;

    public LoadingScreen(Game game) {
        super(game);
        //load assets here
        AssetSingleton.instance.setEnemy(game.getGraphics().newScaledPixmap("Sprites/2040120.png", Graphics.PixmapFormat.RGB565, 300, 300));
        AssetSingleton.instance.setSeed(game.getGraphics().newScaledPixmap("Sprites/seed.png", Graphics.PixmapFormat.RGB565, 35, 70));
        AssetSingleton.instance.setBackground(game.getGraphics().newPixmap("Sprites/bg1.jpg", Graphics.PixmapFormat.RGB565));
        AssetSingleton.instance.setPigeon(game.getGraphics().newScaledPixmap("Sprites/1.jpg", Graphics.PixmapFormat.RGB565, 200, 200));
        //=================//
        finishedLoading = true;
    }

    @Override
    public void update(float deltaTime) {
        finishedAnimation = true;
        if(finishedLoading && finishedAnimation)
            game.setScreen(new GameScreen(game));
    }

    @Override
    public void draw(float deltaTime) {

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
