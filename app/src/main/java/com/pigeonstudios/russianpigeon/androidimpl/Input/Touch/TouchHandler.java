package com.pigeonstudios.russianpigeon.androidimpl.Input.Touch;

import android.view.View;

import com.pigeonstudios.russianpigeon.framework.Input.TouchEvent;

import java.util.List;

/**
 * Created by DennisFedorchuk on 7/27/2016.
 */
public interface TouchHandler extends View.OnTouchListener {
    public boolean isTouchDown(int pointer);

    public int  getTouchX(int pointer);

    public int getTouchY(int pointer);

    public List<TouchEvent> getTouchEvents();
}
