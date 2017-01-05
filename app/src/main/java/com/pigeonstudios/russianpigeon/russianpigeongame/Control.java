package com.pigeonstudios.russianpigeon.russianpigeongame;

import com.pigeonstudios.russianpigeon.framework.Input;
import com.pigeonstudios.russianpigeon.framework.graphics.Graphics;
import com.pigeonstudios.russianpigeon.framework.graphics.Pixmap;

import java.util.List;

/**
 * Created by DennisFedorchuk on 10/12/2016.
 */
public class Control {
    private boolean touched = false;
    static public int x;
    static public int y;
    private ControlBall controlBall;
    private Graphics g;
    private int ballWidth = 300/2;
    private int ballHeight = 300/2;
    static public float accel=0;
    static public int targetX;
    private Pixmap target;
    static public int fingerPosition;

    public Control(Graphics g, Pixmap target){
        this.g = g;
        AssetSingleton.instance.setControlBall(g.newScaledPixmap("Control/controlBall.png", Graphics.PixmapFormat.RGB565, ballWidth*2, ballHeight*2));
        this.x = 540 - ballWidth;
        this.y = g.getHeight() - (ballHeight*2);
        this.targetX = g.getWidth()/2 - target.getWidth()/2;
        this.controlBall = new ControlBall(AssetSingleton.instance.getControlBall(), x, y, g);
        this.target = target;
        this.fingerPosition = 0;
    }

    public void update(List<Input.TouchEvent> touchEvents){
        int pos = 0;
        //check all touch events
        for(int i = 0; i < touchEvents.size(); i++) {
            if (touchEvents.get(i).type == Input.TouchEvent.TOUCH_DRAGGED && controlBall.isTouched(touchEvents.get(i))) { //if the ball is touched set flag to true
                touched = true;
                //if touched the right part of screen
                if (touchEvents.get(i).x > g.getWidth()/2) {
                    //get the position on screen but make the middle = 0
                    //the position should be between 0 to 1080/2
                    pos = touchEvents.get(i).x - g.getWidth() / 2;
                    //find the percent of accel to the right
                    accel = ((float) (1) * (float) (pos)) / (float) 440;
                } else { // if touched to the left of the screen
                    pos = (g.getWidth() / 2) - (touchEvents.get(i).x); // find the position. middle is 0 all th way left is 1080/2
                    accel = ((float) (-1) * (float) (pos)) / (float) 440; // find the percent left
                }
                //set the new x coordinate for the ball

                    this.x = (int) ((touchEvents.get(i).x - ballWidth));
                    fingerPosition = touchEvents.get(i).x;
            } else if (touchEvents.get(i).type == Input.TouchEvent.TOUCH_UP) { //if finger lifted set flag to false
                touched = false;
            }else if(!controlBall.isTouched(touchEvents.get(i)) ){  //if the screen is touched but ball is not set flag to fals
                touched = false;
            }
        }
        //if ball is not touched slide it back to the middle
        if (touched == false) {
            if (this.x > 540-ballWidth) {   //if x is greater than the middle
                this.x -= 10;  //slide the ball to the left 10 pixels per iteration
                if (this.x <= 540-ballWidth) //if ball went more left than suppose to then move it back to the middle
                    this.x = 540-ballWidth;
                pos = (this.x+ballWidth) - 540; // set the new position
                accel = ((float) (1) * (float) (pos)) / (float) 440; // set the new acceleration
            }

            if (this.x < 540-ballWidth) { //if the ball is left then slide it right (everything is the same as above)
                this.x += 10;
                if (this.x >= 540 -ballWidth)
                    this.x = 540 - ballWidth;
                pos = 540 - (this.x+ballWidth);
                accel = ((float) (-1) * (float) (pos)) / (float) 440;
            }
        }
        controlBall.setNewLocation(this.x, this.y); //set new calculated position for the ball
        //if the target moves too far to the side make it come out of another side
        if(targetX >= g.getWidth() + target.getWidth()) {
            targetX = -target.getWidth();
        }else if(targetX <= -target.getWidth()){
            targetX = g.getWidth() + target.getWidth();
        }


        targetX += 25 * accel; //move target 15 pixels times the acceleration

        /* this makes the pigeon slide back when it hits the walls*/
        /*if(targetX > 870){
            targetX = (int)(830-(200*accel));
        } else if ( targetX<-40) {
            targetX = (int)(0 - (200*accel));
        }else {
            targetX += 15 * accel;
        }*/
    }

    public void draw(){
        controlBall.draw();
    }

    public int getTargetX() {
        return targetX;
    }

    public int getY() {
        return y;
    }

    private class ControlBall extends Drawable{
        public ControlBall(Pixmap pixmap, int x, int y, Graphics g) {
            super(pixmap, x, y, g);
        }

        public boolean isTouched(Input.TouchEvent event) {
            if ((event.x > x - 30  && event.x < x + pixmap.getWidth()+ 30)) {
                if ((event.y > y - 30 && event.y < y + pixmap.getHeight() - 1))
                    return true;
                return false;
            } else
                return false;
        }

        public void update(float deltaTime){}
    }



}
