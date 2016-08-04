package com.pigeonstudios.russianpigeon.russianpigeongame;

import com.pigeonstudios.russianpigeon.androidimpl.AndroidGame;
import com.pigeonstudios.russianpigeon.androidimpl.graphics.AndroidGraphics;
import com.pigeonstudios.russianpigeon.androidimpl.graphics.AndroidPixmap;
import com.pigeonstudios.russianpigeon.framework.Game;
import com.pigeonstudios.russianpigeon.framework.Input.TouchEvent;
import com.pigeonstudios.russianpigeon.framework.Screen;
import com.pigeonstudios.russianpigeon.framework.graphics.Graphics;
import com.pigeonstudios.russianpigeon.framework.graphics.Graphics.PixmapFormat;
import com.pigeonstudios.russianpigeon.framework.graphics.Pixmap;

import java.util.List;

/**
 * Created by DennisFedorchuk on 8/3/2016.
 */
public class MenuScreen extends Screen {

    public MenuScreen(Game game) {
        super(game);

    }

    @Override
    public void update(float deltaTime) {
        AssetSingleton.instance.setBackground(game.getGraphics().newScaledPixmap("Background.jpg", PixmapFormat.RGB565, 720, 1280));
        AssetSingleton.instance.setStartButton(game.getGraphics().newPixmap("Menu/StartButton.png", PixmapFormat.ARGB4444));
    }

    private boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
        if((event.x > x && event.x < x + width - 1) && (event.y > y && event.y < y + height - 1))
            return true;
        else
            return false;
    }

    @Override
    public void draw(float deltaTime) {
        game.getGraphics().drawPixmap(AssetSingleton.instance.getBackground(), 0, 0);
        game.getGraphics().drawPixmap(AssetSingleton.instance.getStartButton(), 300, 300);
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
