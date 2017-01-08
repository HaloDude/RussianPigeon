package com.pigeonstudios.russianpigeon.androidimpl.graphics;

import android.graphics.Bitmap;
import android.graphics.Matrix;

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
     * @param x - x position
     * @param y - y position
     * @param width - new width
     * @param height - new height
     * @return - new pixmap
     */
    public AndroidPixmap cropPixmap(int x, int y, int width, int height){
        Bitmap cropedBitmap = Bitmap.createBitmap(bitmap, x, y, width, height);
        return new AndroidPixmap(cropedBitmap, format);
    }

    /**
     * Scale the picture by a set factor
     * @param scaleFactor
     * @return - new scaled pixmap
     */
    public AndroidPixmap scalePixmap(float scaleFactor){
        //Matrix for resize
        Matrix matrix = new Matrix();
        matrix.postScale(scaleFactor, scaleFactor);

        //create a new bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);

        return new AndroidPixmap(resizedBitmap, format);
    }

    /**
     * Scale pixmap to size
     * @param width - new width
     * @param height - new height
     * @return - new scaled pixmap
     */
    public AndroidPixmap scalePixmap(int width, int height){
        //resize
        float scaleWidth = ((float) width / bitmap.getWidth());
        float scaleHeight = ((float) height / bitmap.getHeight());
        //Matrix for resize
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        //create a new bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);

        return new AndroidPixmap(resizedBitmap, format);
    }



    public Bitmap getBitmap(){
        return bitmap;
    }



}
