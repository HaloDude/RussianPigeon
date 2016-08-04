package com.pigeonstudios.russianpigeon.russianpigeongame;

import com.pigeonstudios.russianpigeon.androidimpl.graphics.AndroidGraphics;
import com.pigeonstudios.russianpigeon.framework.Game;
import com.pigeonstudios.russianpigeon.framework.Screen;
import com.pigeonstudios.russianpigeon.framework.graphics.Graphics;

/**
 * Created by DennisFedorchuk on 8/3/2016.
 */
public class LoadingScreen extends Screen {

    public LoadingScreen(Game game){
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics graphics = game.getGraphics();
        AssetSingleton.instance.setPigeon(graphics.newPixmap("Background.png", Graphics.PixmapFormat.RGB565));
        AssetSingleton.instance.setPigeon(graphics.newPixmap("Pigeon.png", Graphics.PixmapFormat.ARGB4444));
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
