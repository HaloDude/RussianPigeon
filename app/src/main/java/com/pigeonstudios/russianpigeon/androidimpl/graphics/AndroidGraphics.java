package com.pigeonstudios.russianpigeon.androidimpl.graphics;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

import com.pigeonstudios.russianpigeon.androidimpl.AndroidGame;
import com.pigeonstudios.russianpigeon.framework.graphics.Graphics;
import com.pigeonstudios.russianpigeon.framework.graphics.Pixmap;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by DennisFedorchuk on 8/2/2016.
 */
public class AndroidGraphics implements Graphics {
    private AssetManager assets;
    private Bitmap framebuffer;
    private Canvas canvas;
    private Paint paint;
    private Rect srcRect;
    private Rect dstRect;

    public AndroidGraphics(AssetManager assets, Bitmap framebuffer){
        this.assets = assets;
        this.framebuffer = framebuffer;
        this.canvas = new Canvas(framebuffer);
        this.paint = new Paint();
        this.srcRect = new Rect();
        this.dstRect = new Rect();
    }

    @Override
    public Pixmap newPixmap(String fileName, PixmapFormat format) {
        Config config = null;
        if (format == PixmapFormat.RGB565)
            config = Config.RGB_565;
        else if(format == PixmapFormat.ARGB4444)
            config = Config.ARGB_4444;
        else
            config = Config.ARGB_8888;
        Options options = new Options();
        options.inPreferredConfig = config;

        InputStream in = null;
        Bitmap bitmap = null;
        try {
            in = assets.open(fileName);
            bitmap = BitmapFactory.decodeStream(in);
            if(bitmap == null)
                throw new RuntimeException("Couldn't load bitmap from asset '" + fileName + "'");
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load bitmap from asset '" + fileName + "'");
        } finally {
            if (in != null){
                try{
                    in.close();
                } catch (IOException e){}
            }
        }

        if (bitmap.getConfig() == Config.RGB_565)
            format = PixmapFormat.RGB565;
        else if (bitmap.getConfig() == Config.ARGB_4444)
            format = PixmapFormat.ARGB4444;
        else
            format = PixmapFormat.ARGB8888;

        bitmap = autoScaleToScreen(bitmap);

        return new AndroidPixmap(bitmap, format);
    }

    public Pixmap newScaledPixmap(String fileName, PixmapFormat format, int width, int height){
        Config config = null;
        if (format == PixmapFormat.RGB565)
            config = Config.RGB_565;
        else if(format == PixmapFormat.ARGB4444)
            config = Config.ARGB_4444;
        else
            config = Config.ARGB_8888;
        Options options = new Options();
        options.inPreferredConfig = config;

        InputStream in = null;
        Bitmap bitmap = null;
        try {
            in = assets.open(fileName);
            bitmap = BitmapFactory.decodeStream(in);
            if(bitmap == null)
                throw new RuntimeException("Couldn't load bitmap from asset '" + fileName + "'");
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load bitmap from asset '" + fileName + "'");
        } finally {
            if (in != null){
                try{
                    in.close();
                } catch (IOException e){}
            }
        }
        //resize
        float scaleWidth = ((float) width / bitmap.getWidth());
        float scaleHeight = ((float) height / bitmap.getHeight());
        //Matrix for resize
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        //create a new bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);

        if (bitmap.getConfig() == Config.RGB_565)
            format = PixmapFormat.RGB565;
        else if (bitmap.getConfig() == Config.ARGB_4444)
            format = PixmapFormat.ARGB4444;
        else
            format = PixmapFormat.ARGB8888;

        resizedBitmap = autoScaleToScreen(resizedBitmap);

        return new AndroidPixmap(resizedBitmap, format);
    }

    public Pixmap newCropedPixmap(String fileName, PixmapFormat format, int x, int y, int width, int height){
        Config config = null;
        if (format == PixmapFormat.RGB565)
            config = Config.RGB_565;
        else if(format == PixmapFormat.ARGB4444)
            config = Config.ARGB_4444;
        else
            config = Config.ARGB_8888;
        Options options = new Options();
        options.inPreferredConfig = config;

        InputStream in = null;
        Bitmap bitmap = null;
        try {
            in = assets.open(fileName);
            bitmap = BitmapFactory.decodeStream(in);
            if(bitmap == null)
                throw new RuntimeException("Couldn't load bitmap from asset '" + fileName + "'");
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load bitmap from asset '" + fileName + "'");
        } finally {
            if (in != null){
                try{
                    in.close();
                } catch (IOException e){}
            }
        }
        //crop
        Bitmap cropedBitmap = Bitmap.createBitmap(bitmap, x, y, width, height);

        if (bitmap.getConfig() == Config.RGB_565)
            format = PixmapFormat.RGB565;
        else if (bitmap.getConfig() == Config.ARGB_4444)
            format = PixmapFormat.ARGB4444;
        else
            format = PixmapFormat.ARGB8888;

        cropedBitmap = autoScaleToScreen(cropedBitmap);

        return new AndroidPixmap(cropedBitmap, format);
    }

    public Bitmap autoScaleToScreen(Bitmap bitmap){
        //scale the pixmap to fit the size of the different screens

        //resize
        float scaleWidth = (((float) bitmap.getWidth() * AndroidGame.getScaleX()) / bitmap.getWidth());
        float scaleHeight = (((float) bitmap.getHeight() * AndroidGame.getScaleY()) / bitmap.getHeight());
        //Matrix for resize
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        //create a new bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
        return resizedBitmap;
    }

    @Override
    public void clear(int color) {
        canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8,
                (color & 0xff));
    }

    @Override
    public void drawPixel(int x, int y, int color) {
        paint.setColor(color);
        canvas.drawPoint(x, y, paint);
    }

    @Override
    public void drawLine(int x, int y, int x2, int y2, int color) {
        paint.setColor(color);
        canvas.drawLine(x, y, x2, y2, paint);
    }

    @Override
    public void drawRect(int x, int y, int width, int height, int color) {
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(x, y, x + width - 1, y + width - 1, paint);
    }

    @Override
    public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight) {
        srcRect.left = srcX;
        srcRect.top = srcY;
        srcRect.right = srcX + srcWidth -1;
        srcRect.bottom = srcY + srcHeight -1;

        dstRect.left = x;
        dstRect.top = y;
        dstRect.right = x + srcWidth - 1;
        dstRect.bottom = y + srcHeight - 1;

        canvas.drawBitmap(((AndroidPixmap) pixmap).getBitmap(), srcRect, dstRect, null);
    }

    @Override
    public void drawPixmap(Pixmap pixmap, int x, int y) {
        canvas.drawBitmap(((AndroidPixmap)pixmap).getBitmap(), x, y, null);
    }

    @Override
    public int getWidth() {
        return framebuffer.getWidth();
    }

    @Override
    public int getHeight() {
        return framebuffer.getHeight();
    }
}
