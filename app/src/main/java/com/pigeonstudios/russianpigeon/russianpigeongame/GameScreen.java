package com.pigeonstudios.russianpigeon.russianpigeongame;

import android.graphics.Color;

import com.pigeonstudios.russianpigeon.framework.Game;
import com.pigeonstudios.russianpigeon.framework.Screen;
import com.pigeonstudios.russianpigeon.framework.graphics.Graphics;
import com.pigeonstudios.russianpigeon.gamelogic.Enemy;
import com.pigeonstudios.russianpigeon.gamelogic.GameWorld;
import com.pigeonstudios.russianpigeon.gamelogic.Pigeon;
import com.pigeonstudios.russianpigeon.gamelogic.Seed;

/**
 * Created by tosch on 10.08.2016.
 */
public class GameScreen extends Screen {
    private float time = 0;
    private int FPSCounter = 0;
    private int FPS = 0;

    private GameWorld gw = new GameWorld();
    public Control c = new Control(1080, 1920, game.getGraphics());

    public GameScreen(Game game) {
        super(game);
        gw.enemy = new Enemy(AssetSingleton.instance.getEnemy(), 0, 0);
        gw.pigeon = new Pigeon(AssetSingleton.instance.getPigeon(), 300, 1000);
    }

    @Override
    public void update(float deltaTime) {
        gw.update(deltaTime);

        c.update(game.getInput().getTouchEvents());
    }

    @Override
    public void draw(float deltaTime) {
        game.getGraphics().drawPixmap(AssetSingleton.instance.getBackground(), 0, 0);
        gw.pigeon.draw(game.getGraphics());
        gw.enemy.draw(game.getGraphics());
        c.draw();
        for(int i = 0; i < gw.countSeeds; i++){
            gw.seeds.get(i).draw(game.getGraphics());
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
