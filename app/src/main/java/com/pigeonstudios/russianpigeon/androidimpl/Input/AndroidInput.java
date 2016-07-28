package com.pigeonstudios.russianpigeon.androidimpl.Input;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;

import com.pigeonstudios.russianpigeon.androidimpl.Input.Touch.MultiTouchHandler;
import com.pigeonstudios.russianpigeon.androidimpl.Input.Touch.SingleTouchHandler;
import com.pigeonstudios.russianpigeon.androidimpl.Input.Touch.TouchHandler;
import com.pigeonstudios.russianpigeon.framework.Input;

import java.util.List;

/**
 * Created by DennisFedorchuk on 7/27/2016.
 */
public class AndroidInput implements Input {
    AccelerometerHandler accelHandler;
    //KeyboardHandler keyHandler;
    TouchHandler touchHandler;

    public AndroidInput(Context context, View view, float scaleX, float scaleY) {
        accelHandler = new AccelerometerHandler(context);
        //keyHandler = new KeyboardHandler(view);
        if (Integer.parseInt(VERSION.SDK) < 5)
            touchHandler = new SingleTouchHandler(view, scaleX, scaleY);
        else
            touchHandler = new MultiTouchHandler(view, scaleX, scaleY);
    }

    @Override
    public boolean isKeyPressed(int keyCode) {
        return false; //keyHandler.isKeyPressed(keyCode);
    }

    @Override
    public boolean isTouchDown(int pointer) {
        return touchHandler.isTouchDown(pointer);
    }

    @Override
    public int getTouchX(int pointer) {
        return touchHandler.getTouchX(pointer);
    }

    @Override
    public int getTouchY(int pointer) {
        return touchHandler.getTouchY(pointer);
    }

    @Override
    public float getAccelX() {
        return accelHandler.getAccelX();
    }

    @Override
    public float getAccelY() {
        return accelHandler.getAccelY();
    }

    @Override
    public float getAccelZ() {
        return accelHandler.getAccelZ();
    }

    @Override
    public List <TouchEvent> getTouchEvents() {
        return touchHandler.getTouchEvents();
    }

    @Override
    public List <KeyEvent> getKeyEvents() {
        return null;// keyHandler.getKeyEvents();
    }
}
