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
    public int x;
    public int y;
    private ControlBall controlBall;
    private Graphics g;

    public Control(int screenWidth, int screenHeight, Graphics g){
        this.x = screenWidth/2;
        this.y = screenHeight - AssetSingleton.instance.getControlBall().getHeight();
        this.controlBall = new ControlBall(AssetSingleton.instance.getControlBall(), x, y);
    }

    public void update(List<Input.TouchEvent> touchEvents){
        for(int i = 0; i < touchEvents.size(); i++) {
            if (controlBall.isTouched(touchEvents.get(i))) {
                if (touchEvents.get(i).type == Input.TouchEvent.TOUCH_DRAGGED) {
                    this.x = touchEvents.get(i).x;
                }
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
    }



}
