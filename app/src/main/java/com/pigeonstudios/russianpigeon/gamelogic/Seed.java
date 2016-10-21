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

    private int xspeed = 6;
    private int yspeed = 6;
    private boolean moveRight = false;
    private boolean moveLeft = false;
    private int direction;
    private Random random = new Random();
    boolean kill;

    public void update(){
        move();
    }

    public void move(){

        if(moveRight){
            if(getX()>=1045){
                moveRight = false;
                moveLeft = true;
                return;
            }
            this.setNewLocation(getX()+(xspeed*direction),getY()+yspeed);
        }else if(moveLeft){
            if(getX()<=0){
                moveRight = true;
                moveLeft = false;
                return;
            }
            this.setNewLocation(getX()-(xspeed*direction),getY()+yspeed);
        }

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
