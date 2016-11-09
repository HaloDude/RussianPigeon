package com.pigeonstudios.russianpigeon.screens;

import android.graphics.Color;

import com.pigeonstudios.russianpigeon.MainActivity;
import com.pigeonstudios.russianpigeon.framework.Game;
import com.pigeonstudios.russianpigeon.framework.Input;
import com.pigeonstudios.russianpigeon.framework.Screen;
import com.pigeonstudios.russianpigeon.framework.graphics.Graphics;
import com.pigeonstudios.russianpigeon.gamelogic.Enemy;
import com.pigeonstudios.russianpigeon.gamelogic.GameWorld;
import com.pigeonstudios.russianpigeon.gamelogic.Pigeon;
import com.pigeonstudios.russianpigeon.gamelogic.Seed;
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


    public GameScreen(Game game) {
        super(game);
        gw = new GameWorld();
        c = new Control(1080, 1920, game.getGraphics());
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
        gw.update(deltaTime);
        c.update(game.getInput().getTouchEvents());

        //TODO need to delete this code, but not now
        if(gw.isSkiped()){
            state = GameState.GameOver;
        }
    }

    private void updatePaused(){

    }

    private void updateGameOver(){
        if(game.getInput().getTouchEvents().size()>0){
            state = GameState.Ready;
            game.setScreen(new GameScreen(game));
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
        c.draw();
    }

    private void drawReady(){
        game.getGraphics().drawPixmap(AssetSingleton.instance.getReady(), 300, 700);
    }

    private void drawRunning(){
        for(int i = 0; i < gw.countSeeds; i++){
            gw.seeds.get(i).draw(game.getGraphics());
        }
    }

    private void drawPaused(){

    }

    private void drawGameOver(){
        game.getGraphics().drawPixmap(AssetSingleton.instance.getGameOver(), 300, 700);
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
