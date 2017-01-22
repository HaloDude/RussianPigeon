package com.pigeonstudios.russianpigeon.framework.graphics;

/**
 * Created by DennisFedorchuk on 6/20/2016.
 */
import com.pigeonstudios.russianpigeon.androidimpl.graphics.AndroidPixmap;
import com.pigeonstudios.russianpigeon.framework.graphics.Graphics.PixmapFormat;

public interface Pixmap {
    public int getWidth();

    public int getHeight();

    public PixmapFormat getFormat();

    public void dispose();

    public AndroidPixmap scalePixmap(float scaleFactor);

    public AndroidPixmap scalePixmap(int width, int height);
}
