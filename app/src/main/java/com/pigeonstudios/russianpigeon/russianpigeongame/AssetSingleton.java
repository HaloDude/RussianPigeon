package com.pigeonstudios.russianpigeon.russianpigeongame;

import com.pigeonstudios.russianpigeon.framework.graphics.Pixmap;

import java.util.LinkedList;

/**
 * Created by DennisFedorchuk on 8/3/2016.
 */
public class AssetSingleton {
    public final static AssetSingleton instance = new AssetSingleton();

    private Pixmap background;
    private Pixmap pigeon;

    private AssetSingleton(){}

    public Pixmap getPigeon() {
        return pigeon;
    }

    public Pixmap getBackground() {
        return background;
    }

    public void setBackground(Pixmap background) {
        this.background = background;
    }

    public void setPigeon(Pixmap pigeon) {
        this.pigeon = pigeon;
    }
}
