package com.pigeonstudios.russianpigeon.androidimpl.Input.Touch;

import android.annotation.TargetApi;
import android.text.method.Touch;
import android.view.MotionEvent;
import android.view.View;

import com.pigeonstudios.russianpigeon.androidimpl.Pool;
import com.pigeonstudios.russianpigeon.androidimpl.Pool.PoolObjectFactory;
import com.pigeonstudios.russianpigeon.framework.Input;
import com.pigeonstudios.russianpigeon.framework.Input.TouchEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DennisFedorchuk on 7/27/2016.
 */
@TargetApi(5)
public class MultiTouchHandler implements TouchHandler {
    private static final int MAX_TOUCHPOINTS = 10;
    private boolean[] isTouched = new boolean[MAX_TOUCHPOINTS];
    private int[] touchX = new int[MAX_TOUCHPOINTS];
    private int[] touchY = new int[MAX_TOUCHPOINTS];
    private int[] id = new int[MAX_TOUCHPOINTS];

    private Pool<TouchEvent> touchEventPool;
    private List<TouchEvent> touchEvents = new ArrayList<TouchEvent>();
    private List<TouchEvent> touchEventsBuffer = new ArrayList<TouchEvent>();

    private float scaleX;
    private float scaleY;

    public MultiTouchHandler(View view, float scaleX, float scaleY){
        PoolObjectFactory<TouchEvent> factory = new PoolObjectFactory<TouchEvent>() {
            @Override
            public TouchEvent createObject() {
                return new TouchEvent();
            }
        };
        touchEventPool = new Pool<TouchEvent>(factory, 100);
        view.setOnTouchListener(this);

        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        synchronized (this){
            int action = event.getAction() & MotionEvent.ACTION_MASK;
            int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;
            int pointerCount = event.getPointerCount();
            TouchEvent touchEvent;
            for(int i = 0; i<MAX_TOUCHPOINTS; i++){
                if(i >= pointerCount){
                    isTouched[i] = false;
                    id[i] = -1;
                    continue;
                }
                int pointerId = event.getPointerId(i);
                if (event.getAction() != MotionEvent.ACTION_MOVE && i != pointerIndex) {
                // if it's an up/down/cancel/out event, mask the id to see if we should process it for this touch point
                    continue;
                }
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        touchEvent = touchEventPool.newObject();
                        touchEvent.type = TouchEvent.TOUCH_DOWN;
                        touchEvent.pointer = pointerId;

                        //this does not work if the scale is 0. and it is for some reason.wtf?!
                       // touchEvent.x = touchX[i] = (int) (event.getX(i) * scaleX);
                       // touchEvent.y = touchY[i] = (int) (event.getY(i) * scaleY);

                        touchEvent.x = touchX[i] = (int) (event.getX(i) * scaleX);
                        touchEvent.y = touchY[i] = (int) (event.getY(i) * scaleY);
                        isTouched[i] = true;
                        id[i] = pointerId;
                        touchEventsBuffer.add(touchEvent);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                    case MotionEvent.ACTION_CANCEL:
                        touchEvent = touchEventPool.newObject();
                        touchEvent.type = TouchEvent.TOUCH_UP;
                        touchEvent.pointer = pointerId;
                        touchEvent.x = touchX[i] = (int) (event.getX(i) * scaleX);
                        touchEvent.y = touchY[i] = (int) (event.getY(i) * scaleY);
                        isTouched[i] = false;
                        id[i] = -1;
                        touchEventsBuffer.add(touchEvent);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        touchEvent = touchEventPool.newObject();
                        touchEvent.type = TouchEvent.TOUCH_DRAGGED;
                        touchEvent.pointer = pointerId;
                        touchEvent.x = touchX[i] = (int) (event.getX(i) * scaleX);
                        touchEvent.y = touchY[i] = (int) (event.getY(i) * scaleY);
                        isTouched[i] = true;
                        id[i] = pointerId;
                        touchEventsBuffer.add(touchEvent);
                        break;
                }
            }
            return true;
        }
    }

    @Override
    public boolean isTouchDown(int pointer) {
        synchronized (this) {
            int index = getIndex(pointer);
            if (index < 0 || index >= MAX_TOUCHPOINTS)
                return false;
            else
                return isTouched[index];
        }
    }

    @Override
    public int getTouchX(int pointer) {
        synchronized (this) {
            int index = getIndex(pointer);
            if (index < 0 || index >= MAX_TOUCHPOINTS)
                return 0;
            else
                return touchX[index];
        }
    }

    @Override
    public int getTouchY(int pointer) {
        synchronized (this) {
            int index = getIndex(pointer);
            if (index < 0 || index >= MAX_TOUCHPOINTS)
                return 0;
            else
                return touchY[index];
        }
    }

    @Override
    public List<TouchEvent> getTouchEvents() {
        synchronized (this) {
            int len = touchEvents.size();
            for (int i = 0; i < len; i++)
                touchEventPool.free(touchEvents.get(i));
            touchEvents.clear();
            touchEvents.addAll(touchEventsBuffer);
            touchEventsBuffer.clear();
            return touchEvents;
        }
    }

    private int getIndex(int pointerId) {
        for (int i = 0; i < MAX_TOUCHPOINTS; i++) {
            if (id[i] == pointerId) {
                return i;
            }
        }
        return -1;
    }

}
