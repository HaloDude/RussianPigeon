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
    private int ballWidth = 200/2;
    private int ballHeight = 200/2;
    private float accel=0;
    static public int targetX;

    public Control(int screenWidth, int screenHeight, Graphics g){
        this.g = g;
        AssetSingleton.instance.setControlBall(g.newScaledPixmap("Control/controlBall.png", Graphics.PixmapFormat.RGB565, ballWidth*2, ballHeight*2));
        this.x = 540 - ballWidth;
        this.y = g.getHeight() - (ballHeight*2);
        this.targetX = g.getWidth()/2;
        this.controlBall = new ControlBall(AssetSingleton.instance.getControlBall(), x, y);
    }

    public void update(List<Input.TouchEvent> touchEvents){
        int pos = 0;
        for(int i = 0; i < touchEvents.size(); i++) {
            if(touchEvents.get(i).type == Input.TouchEvent.TOUCH_DRAGGED){
                touched = true;
            }else if(touchEvents.get(i).type == Input.TouchEvent.TOUCH_UP){
                touched = false;
            }
            if (touchEvents.get(i).type == Input.TouchEvent.TOUCH_DRAGGED && controlBall.isTouched(touchEvents.get(i))) {
                if (touchEvents.get(i).x > 540) {
                    pos = touchEvents.get(i).x - g.getWidth() / 2;
                    accel = ((float) (1) * (float) (pos)) / (float) 440;
                } else {
                    pos = 540 - touchEvents.get(i).x;
                    accel = ((float) (-1) * (float) (pos)) / (float) 440;
                }
                if (touchEvents.get(i).x > ballWidth && touchEvents.get(i).x <= g.getWidth() - ballWidth) {
                    this.x = (int) ((touchEvents.get(i).x - ballHeight));
                }
            }
            if (touched == false) {
                if (this.x > 540) {
                    this.x -= 10;
                    if (this.x <= 540)
                        this.x = 540;
                    pos = this.x - 540;
                    accel = ((float) (1) * (float) (pos)) / (float) 440;
                }

                if (this.x < 540) {
                    this.x += 10;
                    if (this.x >= 540)
                        this.x = 540;
                    pos = 540 - this.x;
                    accel = ((float) (-1) * (float) (pos)) / (float) 440;
                }
            }
        }

        if (touched == false) {
            if (this.x > 540-ballWidth) {
                this.x -= 10;
                if (this.x <= 540-ballWidth)
                    this.x = 540-ballWidth;
                pos = this.x - 540;
                accel = ((float) (1) * (float) (pos)) / (float) 440;
            }

            if (this.x < 540-ballWidth) {
                this.x += 10;
                if (this.x >= 540 -ballWidth)
                    this.x = 540 - ballWidth;
                pos = 540 - this.x;
                accel = ((float) (-1) * (float) (pos)) / (float) 440;
            }
        }
        controlBall.setNewLocation(this.x, this.y);
        targetX += 10 * accel;
    }

    public void draw(){
        controlBall.draw(g);
    }

    public int getTargetX() {
        return targetX;
    }

    public int getY() {
        return y;
    }

    private class ControlBall extends Drawable{
        public ControlBall(Pixmap pixmap, int x, int y) {
            super(pixmap, x, y);
        }

        public boolean isTouched(Input.TouchEvent event) {
            if ((event.x > x - 70 && event.x < x + pixmap.getWidth() + 70)) {
                if ((event.y > y && event.y < y + pixmap.getHeight() - 1))
                    return true;
                return false;
            } else
                return false;
        }
    }





}
