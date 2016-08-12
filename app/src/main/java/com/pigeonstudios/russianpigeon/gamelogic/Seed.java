package com.pigeonstudios.russianpigeon.gamelogic;

import com.pigeonstudios.russianpigeon.framework.graphics.Pixmap;
import com.pigeonstudios.russianpigeon.russianpigeongame.Drawable;
import java.util.Random;

/**
 * Created by tosch on 11.08.2016.
 */
public class Seed extends Drawable {
    public Seed(Pixmap pixmap, int x, int y) {
        super(pixmap, x, y);
        moveRight = random.nextBoolean();
        moveLeft = !moveRight;
    }
    //TODO we need 2 type of coordinate(x,y) one of them will be picture coord, and another one will be object coord
    private int x = this.getX()+75;
    private int y = this.getY()+50;
    private int xspeed = 12;
    private int yspeed = 6;
    private boolean moveRight = false;
    private boolean moveLeft = false;
    final Random random = new Random();

    public void move(){

        if(moveRight){
            x+=xspeed;
            y+=yspeed;
            if(x>=695){
                moveRight = false;
                moveLeft = true;
                return;
            }
            this.setNewLocation(x,y);
        }else if(moveLeft){
            x-=xspeed;
            y+=yspeed;
            if(x<=0){
                moveRight = true;
                moveLeft = false;
                return;
            }
            this.setNewLocation(x,y);
        }
    }
}
