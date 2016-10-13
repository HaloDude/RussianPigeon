package com.pigeonstudios.russianpigeon.russianpigeongame;

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
    private GameWorld gw = new GameWorld();
    public Control c = new Control(1080, 1920, game.getGraphics());

    public GameScreen(Game game) {
        super(game);
        AssetSingleton.instance.setEnemy(game.getGraphics().newScaledPixmap("Sprites/2040120.png", Graphics.PixmapFormat.RGB565, 300, 300));
        AssetSingleton.instance.setSeed(game.getGraphics().newScaledPixmap("Sprites/seed.png", Graphics.PixmapFormat.RGB565, 35, 70));
        AssetSingleton.instance.setBackground(game.getGraphics().newPixmap("Sprites/bg1.jpg", Graphics.PixmapFormat.RGB565));
        AssetSingleton.instance.setPigeon(game.getGraphics().newScaledPixmap("Sprites/Pigeon.png", Graphics.PixmapFormat.RGB565, 300, 300));
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
