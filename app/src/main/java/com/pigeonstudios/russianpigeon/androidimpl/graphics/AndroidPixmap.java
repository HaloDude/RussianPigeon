package com.pigeonstudios.russianpigeon.androidimpl.graphics;

import android.graphics.Bitmap;

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

    /**
     * Crop a piece of the current pixmap. used for spritesheet
     * @param x
     * @param y
     * @param width
     * @param height
     * @return
     */
    public AndroidPixmap cropPixmap(int x, int y, int width, int height){
        Bitmap cropedBitmap = Bitmap.createBitmap(bitmap, x, y, width, height);
        return new AndroidPixmap(cropedBitmap, format);
    }



    public Bitmap getBitmap(){
        return bitmap;
    }



}
