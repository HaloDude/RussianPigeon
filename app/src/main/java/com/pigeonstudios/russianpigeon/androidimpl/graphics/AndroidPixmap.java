package com.pigeonstudios.russianpigeon.androidimpl.graphics;

import android.graphics.Bitmap;

import com.pigeonstudios.russianpigeon.framework.Input;
import com.pigeonstudios.russianpigeon.framework.graphics.Graphics.PixmapFormat;
import com.pigeonstudios.russianpigeon.framework.graphics.Pixmap;

/**
 * Created by DennisFedorchuk on 7/28/2016.
 */
public class AndroidPixmap implements Pixmap {

    private Bitmap bitmap;
    private PixmapFormat format;
    private int x;
    private int y;

    public AndroidPixmap(Bitmap bitmap, PixmapFormat format){
        this.bitmap = bitmap;
        this.format = format;
    }

    @Override
    public int getWidth() {
        return bitmap.getWidth();
    }

    @Override
    public int getHeight() {
        return bitmap.getHeight();
    }

    @Override
    public PixmapFormat getFormat() {
        return format;
    }

    @Override
    public void dispose() {
        bitmap.recycle();
    }



    public Bitmap getBitmap(){
        return bitmap;
    }



}
