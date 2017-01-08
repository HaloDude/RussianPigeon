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

    private int startButtonYPosition = 500;
    private int musicButtonYPosition = 1500;
    private int soundButtonYPosition = 1500;
    private int recordButtonYPosition = 1500;

    private int buttonAnimationMove = 50;


    public MenuScreen(Game game) {
        super(game);
        this.startButton = new Button(AssetSingleton.instance.getStartButton(), 290, 2000, game.getGraphics());
        this.musicButton = new Button(AssetSingleton.instance.getMusicButton(), 80, 2000, game.getGraphics());
        this.soundButton = new Button(AssetSingleton.instance.getSoundButton(), 415, 2000, game.getGraphics());
        this.recordButton = new Button(AssetSingleton.instance.getRecordButton(), 750, 2000, game.getGraphics());
    }

    @Override
    public void update(float deltaTime) {
        startButtonUpdate();
        musicButtonUpdate();
        soundButtonUpdate();
        recordButtonUpdate();
    }

    private void startButtonUpdate(){
        if(isTouched(startButton)) {
            game.setScreen(new GameScreen(game));
        }

        if(startButton.getY() >= startButtonYPosition){ //if the button is lower than final position
            startButton.drawAtNewLocation(startButton.getX(), startButton.getY() - buttonAnimationMove - 20);
        }
    }

    private void musicButtonUpdate(){
        if(isTouched(musicButton)) {
            //TODO action listener for musicButton
        }

        if(startButton.getY() <= startButtonYPosition + 200){ // if start button is almost at position
            if(musicButton.getY() >= musicButtonYPosition){ //if music button not in final position
                musicButton.drawAtNewLocation(musicButton.getX(), musicButton.getY() - buttonAnimationMove);
            }
        }
    }

    private void soundButtonUpdate(){
        if(isTouched(soundButton)){
            //TODO action listener for soundButton
        }

        if(musicButton.getY() <= musicButtonYPosition + 100){ // if music button is almost at final position
            if(soundButton.getY() >= soundButtonYPosition){ // if sound button is not in position
                soundButton.drawAtNewLocation(soundButton.getX(), soundButton.getY() - buttonAnimationMove);
            }
        }
    }

    private void recordButtonUpdate(){
        if(isTouched(recordButton)){
            //TODO action listener for recordButton
        }

        if(soundButton.getY() <= soundButtonYPosition + 100){ // if sound button is almost at position
            if(recordButton.getY() >=recordButtonYPosition){ // if record button is not in proper position
                recordButton.drawAtNewLocation(recordButton.getX(), recordButton.getY() - buttonAnimationMove);
            }
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
