package com.pigeonstudios.russianpigeon.gamelogic;

import com.pigeonstudios.russianpigeon.framework.graphics.Pixmap;
import com.pigeonstudios.russianpigeon.russianpigeongame.Drawable;

/**
 * Created by tosch on 10.08.2016.
 */
public class Enemy extends Drawable{
    public Enemy(Pixmap pixmap, int x, int y) {
        super(pixmap, x, y);
    }
    //TODO we need 2 type of coordinate(x,y) one of them will be picture coord, and another one will be object coord
    private int x = 0;
    private int y = 0;
    private int speed = 4;
    private boolean moveRight = true;
    private boolean moveLeft = false;

    public void move(){
        if(moveRight){
            x+=speed;
            if(x>=420){
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
