package com.pigeonstudios.russianpigeon.russianpigeongame;

import com.pigeonstudios.russianpigeon.framework.Input;
import com.pigeonstudios.russianpigeon.framework.graphics.Graphics;
import com.pigeonstudios.russianpigeon.framework.graphics.Pixmap;
import com.pigeonstudios.russianpigeon.russianpigeongame.AssetSingleton;

import java.util.List;

/**
 * Created by DennisFedorchuk on 10/12/2016.
 */
public class Control {
    private boolean touched = false;
    public int x;
    public int y;
    private ControlBall controlBall;
    private Graphics g;

    public Control(int screenWidth, int screenHeight, Graphics g){
        this.g = g;
        AssetSingleton.instance.setControlBall(g.newScaledPixmap("Control/controlBall.png", Graphics.PixmapFormat.RGB565, 300, 300));
        this.x = 300;
        this.y = 1100;
        this.controlBall = new ControlBall(AssetSingleton.instance.getControlBall(), x, y);
    }

    public void update(List<Input.TouchEvent> touchEvents){

        for(int i = 0; i < touchEvents.size(); i++) {
            if (touchEvents.get(i).type == Input.TouchEvent.TOUCH_DRAGGED && controlBall.isTouched(touchEvents.get(i)) ) {
                this.x = touchEvents.get(i).x - (AssetSingleton.instance.getControlBall().getHeight()/2);
                controlBall.setNewLocation(this.x,this.y);
            }
        }
    }

    public void draw(){
        controlBall.draw(g);
    }

    private class ControlBall extends Drawable{
        public ControlBall(Pixmap pixmap, int x, int y) {
            super(pixmap, x, y);
        }

        public boolean isTouched(Input.TouchEvent event) {
            if ((event.x > x - 60 && event.x < x + pixmap.getWidth() - 1)) {
                if ((event.y > y && event.y < y + pixmap.getHeight() - 1))
                    return true;
                return false;
            } else
                return false;
        }
    }





}
