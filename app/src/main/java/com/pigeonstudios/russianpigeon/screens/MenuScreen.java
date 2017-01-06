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
    private Button recordButton;

    public MenuScreen(Game game) {
        super(game);
        this.startButton = new Button(AssetSingleton.instance.getStartButton(), 290, 500, game.getGraphics());
        this.musicButton = new Button(AssetSingleton.instance.getMusicButton(), 80, 1500, game.getGraphics());
        this.soundButton = new Button(AssetSingleton.instance.getSoundButton(), 415, 1500, game.getGraphics());
        this.recordButton = new Button(AssetSingleton.instance.getRecordButton(), 750, 1500, game.getGraphics());
    }

    @Override
    public void update(float deltaTime) {
        startButtonUpdate();
        musicButtonUpdate();
        soundButtonUpdate();
        recordButtonUpdate();
    }

    private void startButtonUpdate(){
        if(isTouched(startButton))
            game.setScreen(new GameScreen(game));
    }

    private void musicButtonUpdate(){
        if(isTouched(musicButton)) {
            //TODO action listener for musicButton
        }
    }

    private void soundButtonUpdate(){
        if(isTouched(soundButton)){
            //TODO action listener for soundButton
        }
    }

    private void recordButtonUpdate(){
        if(isTouched(recordButton)){
            //TODO action listener for recordButton
        }

    }

    private boolean isTouched(Button button){
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents(); // do this to clear the buffer. idk why
        for(int i = 0; i < touchEvents.size(); i++) {
            if (touchEvents.get(i).type == TouchEvent.TOUCH_UP) {
                if (button.isTouched(touchEvents.get(i))) {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public void draw(float deltaTime) {
        game.getGraphics().drawPixmap(AssetSingleton.instance.getMenuScreen(), 0, 0);
        startButton.draw();
        soundButton.draw();
        musicButton.draw();
        recordButton.draw();
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
