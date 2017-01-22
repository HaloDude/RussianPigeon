package com.pigeonstudios.russianpigeon.gamelogic;

import com.pigeonstudios.russianpigeon.framework.graphics.Graphics;
import com.pigeonstudios.russianpigeon.framework.graphics.Pixmap;
import com.pigeonstudios.russianpigeon.russianpigeongame.Drawable;
import java.util.Random;

/**
 * Created by tosch on 11.08.2016.
 */
public class Seed extends Drawable {
    private int xspeed = 6;
    private int yspeed;
    private boolean moveRight = false;
    private boolean moveLeft = false;
    private int direction;
    private Random random = new Random();
    boolean skipped = false;
    boolean caught = false;
    boolean eaten = false;

    public Seed(Pixmap pixmap, int x, int y, int speed, Graphics g) {
        super(pixmap, x, y, g);
        setSide();
        setDirection();
        yspeed = speed;

    }

    public void update(float deltaTime){
        move();

    }

    public void move(){
        if(!caught) {
            if (moveRight) {
                if (getX() >= 1045) {
                    moveRight = false;
                    moveLeft = true;
                    return;
                }
                this.setNewLocation(getX() + (xspeed * direction), getY() + yspeed);
            } else if (moveLeft) {
                if (getX() <= 0) {
                    moveRight = true;
                    moveLeft = false;
                    return;
                }
                this.setNewLocation(getX() - (xspeed * direction), getY() + yspeed);
            }
        }else{
            this.scalePixmap(0.9f);
            this.setNewLocation(getX() +5, getY() + 5);
            if(this.rectangleScaleFactor<=0.2f){
                this.eaten = true;
            }
        }

    }


    private void setDirection(){
        direction = random.nextInt(3);
        //System.out.print(direction);
    }

    private void setSide(){
        moveRight = random.nextBoolean();
        moveLeft = !moveRight;
    }


    public void setSpeed(int x){
        this.yspeed = x;
    }

}
