package com.pigeonstudios.russianpigeon.russianpigeongame;

import com.pigeonstudios.russianpigeon.framework.Game;
import com.pigeonstudios.russianpigeon.framework.Input;
import com.pigeonstudios.russianpigeon.framework.Screen;
import com.pigeonstudios.russianpigeon.framework.graphics.Graphics;
import com.pigeonstudios.russianpigeon.gamelogic.Enemy;
import com.pigeonstudios.russianpigeon.gamelogic.GameWorld;
import com.pigeonstudios.russianpigeon.gamelogic.Seed;

import java.util.List;

/**
 * Created by tosch on 10.08.2016.
 */
public class GameScreen extends Screen {
    private static GameWorld gw = new GameWorld();
    private Control control;


    public GameScreen(Game game) {
        super(game);
        AssetSingleton.instance.setEnemy(game.getGraphics().newScaledPixmap("Sprites/2040120.png", Graphics.PixmapFormat.RGB565, 300, 300));
        AssetSingleton.instance.setSeed(game.getGraphics().newScaledPixmap("Sprites/seed.png", Graphics.PixmapFormat.RGB565, 35, 70));
        AssetSingleton.instance.setBackground(game.getGraphics().newScaledPixmap("Sprites/bg.jpg", Graphics.PixmapFormat.RGB565, 720 , 1240));
        AssetSingleton.instance.setControlBall(game.getGraphics().newScaledPixmap("Control/controlBall.png", Graphics.PixmapFormat.ARGB4444, 100, 100));
        gw.enemy = new Enemy(AssetSingleton.instance.getEnemy(), 0, 0);
        Control c = new Control(1080, 1920, game.getGraphics());
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        List<Input.KeyEvent> k = game.getInput().getKeyEvents();
        gw.update(deltaTime);
        control.update(touchEvents);
    }

    @Override
    public void draw(float deltaTime) {
        game.getGraphics().drawPixmap(AssetSingleton.instance.getBackground(), 0, 0);
        gw.enemy.draw(game.getGraphics());
        for(int i = 0; i < gw.countSeeds; i++){
            gw.seeds.get(i).draw(game.getGraphics());
        }
        control.draw();
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
