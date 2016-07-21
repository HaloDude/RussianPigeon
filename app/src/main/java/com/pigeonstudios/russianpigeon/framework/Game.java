package com.pigeonstudios.russianpigeon.framework;

import com.pigeonstudios.russianpigeon.framework.audio.Audio;
import com.pigeonstudios.russianpigeon.framework.graphics.Graphics;

/**
 * Created by DennisFedorchuk on 6/23/2016.
 */
public interface Game {
    public Input getInput();

    public FileIO getFileIO();

    public Graphics getGraphics();

    public Audio getAudio();

    public void setScreen(Screen screen);

    public Screen getCurrentScreen();

    public Screen getStartScreen();


}
