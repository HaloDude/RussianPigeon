package com.pigeonstudios.russianpigeon.screens;

import android.graphics.Color;

import com.pigeonstudios.russianpigeon.framework.Game;
import com.pigeonstudios.russianpigeon.framework.Input;
import com.pigeonstudios.russianpigeon.framework.Screen;
import com.pigeonstudios.russianpigeon.gamelogic.GameWorld;
import com.pigeonstudios.russianpigeon.russianpigeongame.AssetSingleton;
import com.pigeonstudios.russianpigeon.russianpigeongame.Button;
import com.pigeonstudios.russianpigeon.russianpigeongame.Control;

import java.util.List;

/**
 * Created by tosch on 10.08.2016.
 */
public class GameScreen extends Screen {
    enum GameState{
        Ready,
        Running,
        Paused,
        GameOver
    }

    GameState state = GameState.Ready;
    private float time = 0;
    private int FPSCounter = 0;
    private int FPS = 0;

    private GameWorld gw;
    public Control c;


    //buttons
    private Button menuButton;
    private Button restartButton;
    private Button resumeButton;
    private Button pauseButton;


    public GameScreen(Game game) {
        super(game);
        gw = new GameWorld(game);
        c = new Control(game.getGraphics(), gw.pigeon.getPixmap());
        this.menuButton = new Button(AssetSingleton.instance.getMenuButton(), 240, 1100);
        this.restartButton = new Button(AssetSingleton.instance.getRestartButton(), 240, 850);
        this.resumeButton = new Button(AssetSingleton.instance.getResumeButton(), 240, 600);
        this.pauseButton = new Button(AssetSingleton.instance.getPauseButton(), 980, 0);
    }

    @Override
    public void update(float deltaTime) {

        if(state == GameState.Ready){
            updateReady();
        }
        if(state == GameState.Running){
            updateRunning(deltaTime);
        }
        if(state == GameState.Paused){
            updatePaused();
        }
        if(state == GameState.GameOver){
            updateGameOver();
        }

    }

    private void updateReady(){
        game.getInput().getKeyEvents();

        if(game.getInput().getTouchEvents().size()>0){
            state = GameState.Running;
        }
    }

    private void updateRunning(float deltaTime){
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        gw.update(deltaTime);
        c.update(touchEvents);

        for(int i = 0; i < touchEvents.size(); i++) {
            if (touchEvents.get(i).type == Input.TouchEvent.TOUCH_UP) {
                if (pauseButton.isTouched(touchEvents.get(i))) {
                    state = GameState.Paused;
                    return;
                }
            }
        }


        if(gw.isSkipped()){
            state = GameState.GameOver;
            return;
        }


    }

    private void updatePaused(){
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        for(int i = 0; i < touchEvents.size(); i++) {
            if (touchEvents.get(i).type == Input.TouchEvent.TOUCH_UP) {
                if (restartButton.isTouched(touchEvents.get(i))) {
                    game.setScreen(new GameScreen(game));
                    return;
                }else if (menuButton.isTouched(touchEvents.get(i))) {
                    game.setScreen(new MenuScreen(game));
                    return;
                }else if (resumeButton.isTouched(touchEvents.get(i))){
                    state = GameState.Running;
                    return;
                }
            }
        }
    }

    private void updateGameOver(){
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        for(int i = 0; i < touchEvents.size(); i++) {
            if (touchEvents.get(i).type == Input.TouchEvent.TOUCH_UP) {
                if (restartButton.isTouched(touchEvents.get(i))) {
                    game.setScreen(new GameScreen(game));
                }else if (menuButton.isTouched(touchEvents.get(i))) {
                    game.setScreen(new MenuScreen(game));
                }
            }
        }
    }

    @Override
    public void draw(float deltaTime) {

        drawWorld();

        if(state == GameState.Ready){
            drawReady();
        }
        if(state == GameState.Running){
            drawRunning();
        }
        if(state == GameState.Paused){
            drawPaused();
        }
        if(state == GameState.GameOver){
            drawGameOver();
        }



        time += deltaTime;
        if(time >= 1){
            time = 0;
            FPS = FPSCounter;
            FPSCounter = 0;
        } else {
            FPSCounter++;
        }
        game.getGraphics().drawText(String.valueOf(FPS), 0, 50, Color.GREEN);

    }

    private void drawWorld(){
        game.getGraphics().drawPixmap(AssetSingleton.instance.getBackground(), 0, 0);
        gw.pigeon.draw(game.getGraphics());
        gw.enemy.draw(game.getGraphics());
        game.getGraphics().drawText("Score " + String.valueOf(gw.getScore()), 0, 100, Color.GREEN);
        c.draw();
    }

    private void drawReady(){
        game.getGraphics().drawPixmap(AssetSingleton.instance.getReady(), 40, 700);
    }

    private void drawRunning(){
        pauseButton.draw(game.getGraphics());
        for(int i = 0; i < gw.countSeeds; i++){
            gw.seeds.get(i).draw(game.getGraphics());
        }
    }

    private void drawPaused(){
        resumeButton.draw(game.getGraphics());
        menuButton.draw(game.getGraphics());
        restartButton.draw(game.getGraphics());
    }

    private void drawGameOver(){
        game.getGraphics().drawPixmap(AssetSingleton.instance.getGameOver(), 300, 400);
        menuButton.draw(game.getGraphics());
        restartButton.draw(game.getGraphics());
    }


    @Override
    public void pause() {
        if(state == GameState.Running){
            state = GameState.Paused;
        }
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
