package com.pigeonstudios.russianpigeon.screens;

import com.pigeonstudios.russianpigeon.androidimpl.Input.AndroidInput;
import com.pigeonstudios.russianpigeon.framework.Game;
import com.pigeonstudios.russianpigeon.framework.Input.TouchEvent;
import com.pigeonstudios.russianpigeon.framework.Screen;
import com.pigeonstudios.russianpigeon.framework.graphics.Graphics.PixmapFormat;
import com.pigeonstudios.russianpigeon.russianpigeongame.AssetSingleton;
import com.pigeonstudios.russianpigeon.russianpigeongame.Button;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by DennisFedorchuk on 8/3/2016.
 */
public class MenuScreen extends Screen {
    private Button startButton;
    private Button musicButton;
    private Button soundButton;

    public MenuScreen(Game game) {
        super(game);
        this.startButton = new Button(AssetSingleton.instance.getStartButton(), 290, 500);
        this.musicButton = new Button(AssetSingleton.instance.getMusicButton(), 200, 1500);
        this.soundButton = new Button(AssetSingleton.instance.getSoundButton(), 630, 1500);
    }

    @Override
    public void update(float deltaTime) {
        //test for touch
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents(); // do this to clear the buffer. idk why
        for(int i = 0; i < touchEvents.size(); i++) {
            if (touchEvents.get(i).type == TouchEvent.TOUCH_UP) {
                if (startButton.isTouched(touchEvents.get(i))) {
                    game.setScreen(new GameScreen(game));
                }
            }
        }

    }

    @Override
    public void draw(float deltaTime) {
        game.getGraphics().drawPixmap(AssetSingleton.instance.getMenuScreen(), 0, 0);
        startButton.draw(game.getGraphics());
        soundButton.draw(game.getGraphics());
        musicButton.draw(game.getGraphics());
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
