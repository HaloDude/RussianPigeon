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
        setSide();
        setDirection();

    }

    private int x = this.getX()+75;
    private int y = this.getY()+50;
    private int xspeed = 6;
    private int yspeed = 6;
    private boolean moveRight = false;
    private boolean moveLeft = false;
    private int direction;
    private Random random = new Random();
    boolean kill;

    public void move(){

        if(moveRight){
            x+=xspeed*direction;
            y+=yspeed;
            if(x>=695){
                moveRight = false;
                moveLeft = true;
                return;
            }
            this.setNewLocation(x,y);
        }else if(moveLeft){
            x-=xspeed*direction;
            y+=yspeed;
            if(x<=0){
                moveRight = true;
                moveLeft = false;
                return;
            }
            this.setNewLocation(x,y);
        }
    }

    public void moveUp(){
        y-=yspeed;

        this.setNewLocation(x,y);
    }

    private void setDirection(){
        direction = random.nextInt(3);
        System.out.print(direction);

    }

    private void setSide(){
        moveRight = random.nextBoolean();
        moveLeft = !moveRight;
    }


}
