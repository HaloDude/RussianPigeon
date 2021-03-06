package com.pigeonstudios.russianpigeon.gamelogic;


import com.pigeonstudios.russianpigeon.androidimpl.graphics.AndroidPixmap;
import com.pigeonstudios.russianpigeon.framework.Game;
import com.pigeonstudios.russianpigeon.russianpigeongame.AssetSingleton;


/**
 * Created by tosch on 10.08.2016.
 */
public class GameWorld {
    Game game;

    public Enemy enemy;
    public Pigeon pigeon;
    public Seeds seeds;

    private int score = 0;

    public GameWorld(Game game) {
        this.game = game;
        enemy = new Enemy(AssetSingleton.instance.getEnemy(), 390, 50, game.getGraphics());
        pigeon = new Pigeon(AssetSingleton.instance.getPigeon(), 1080-680, 1920-600, game.getGraphics() );
        seeds = new Seeds(enemy.getX(), enemy.getY(), game.getGraphics());
    }

    public void update(float deltaTime) {
        pigeon.update(deltaTime);
        enemy.update(deltaTime);
        seeds.update(deltaTime, enemy.getX(), enemy.getY());
        isSeedCaught();
        isSeedSkipped();
    }

    private void isSeedCaught() {
        for(int i = 0; i< seeds.getSeeds().size(); i++){
            if(!seeds.getSeeds().get(i).caught && pigeon.getRectangle().intersect(seeds.getSeeds().get(i).getRectangle())){
                seeds.seedCaught(i);
                score++;
            }
        }
    }

    private void isSeedSkipped(){
        for (Seed s : seeds.getSeeds()) {
            if(s.getY()>1920 && !s.caught && !s.skipped){
                s.skipped = true;
                pigeon.lives--;
            }
        }
    }

    public boolean isLost(){
        return pigeon.lives == 0;
    }



    public int getScore(){
        return score;
    }
}
