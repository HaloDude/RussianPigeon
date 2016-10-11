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
    private static GameWorld gw = new GameWorld();


    public GameScreen(Game game) {
        super(game);
        AssetSingleton.instance.setEnemy(game.getGraphics().newScaledPixmap("Sprites/2040120.png", Graphics.PixmapFormat.RGB565, 300, 300));
        AssetSingleton.instance.setSeed(game.getGraphics().newScaledPixmap("Sprites/seed.png", Graphics.PixmapFormat.RGB565, 35, 70));
        AssetSingleton.instance.setBackground(game.getGraphics().newScaledPixmap("Sprites/bg.jpg", Graphics.PixmapFormat.RGB565, 1080, 1920));
        AssetSingleton.instance.setPigeon(game.getGraphics().newScaledPixmap("Sprites/Pigeon.png", Graphics.PixmapFormat.RGB565, 300, 300));
        gw.enemy = new Enemy(AssetSingleton.instance.getEnemy(), 0, 0);
        gw.pigeon = new Pigeon(AssetSingleton.instance.getPigeon(), 300, 1000);
    }

    @Override
    public void update(float deltaTime) {

        gw.update();
    }

    @Override
    public void draw(float deltaTime) {
        game.getGraphics().drawPixmap(AssetSingleton.instance.getBackground(), 0, 0);
        gw.pigeon.draw(game.getGraphics());
        gw.enemy.draw(game.getGraphics());
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
