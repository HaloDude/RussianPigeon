package com.pigeonstudios.russianpigeon.gamelogic;

import com.pigeonstudios.russianpigeon.androidimpl.AndroidGame;
import com.pigeonstudios.russianpigeon.framework.graphics.Pixmap;
import com.pigeonstudios.russianpigeon.russianpigeongame.Drawable;

/**
 * Created by tosch on 10.08.2016.
 */
public class Enemy extends Drawable{
    public Enemy(Pixmap pixmap, int x, int y) {
        super(pixmap, x, y);
    }
    private int x = 0;
    private int y = 0;
    private int speed = 4;
    private boolean moveRight = true;
    private boolean moveLeft = false;

    public void update(){
        move();
    }

    public void move(){
        if(moveRight){
            x+=speed;
            if(x >= (1080-450)){
                moveRight = false;
                moveLeft = true;
                return;
            }
            this.setNewLocation(x,y);
        }else if(moveLeft){
            x-=speed;
            if(x<=0){
                moveRight = true;
                moveLeft = false;
                return;
            }
            this.setNewLocation(x,y);
        }

    }
}
