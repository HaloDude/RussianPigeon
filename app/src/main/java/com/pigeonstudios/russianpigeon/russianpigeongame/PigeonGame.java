package com.pigeonstudios.russianpigeon.russianpigeongame;

import com.pigeonstudios.russianpigeon.androidimpl.AndroidGame;
import com.pigeonstudios.russianpigeon.framework.Screen;

/**
 * Created by DennisFedorchuk on 8/10/2016.
 */
public class PigeonGame extends AndroidGame {

    public Screen getStartScreen() {
        return new MenuScreen(this);
    }
}
