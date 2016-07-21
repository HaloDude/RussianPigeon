package com.pigeonstudios.russianpigeon.framework.graphics;

/**
 * Created by DennisFedorchuk on 6/20/2016.
 */
import com.pigeonstudios.russianpigeon.framework.graphics.Graphics.PixmapFormat;

public interface Pixmap {
    public int getWidth();

    public int getHeight();

    public PixmapFormat getFormat();

    public void dispose();
}
