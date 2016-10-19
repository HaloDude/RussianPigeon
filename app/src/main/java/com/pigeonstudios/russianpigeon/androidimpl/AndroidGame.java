package com.pigeonstudios.russianpigeon.androidimpl;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

import com.pigeonstudios.russianpigeon.androidimpl.Input.AndroidInput;
import com.pigeonstudios.russianpigeon.androidimpl.audio.AndroidAudio;
import com.pigeonstudios.russianpigeon.androidimpl.graphics.AndroidGraphics;
import com.pigeonstudios.russianpigeon.framework.FileIO;
import com.pigeonstudios.russianpigeon.framework.Game;
import com.pigeonstudios.russianpigeon.framework.Input;
import com.pigeonstudios.russianpigeon.framework.Screen;
import com.pigeonstudios.russianpigeon.framework.audio.Audio;
import com.pigeonstudios.russianpigeon.framework.graphics.Graphics;
import com.pigeonstudios.russianpigeon.russianpigeongame.GameScreen;
import com.pigeonstudios.russianpigeon.russianpigeongame.LoadingScreen;

/**
 * Created by DennisFedorchuk on 8/2/2016.
 */
public class AndroidGame extends Activity implements Game {
    private AndroidFastRenderView renderView;
    private Graphics graphics;
    private Audio audio;
    private Input input;
    private FileIO fileIO;
    private Screen screen;
    private WakeLock wakeLock;

    //screen properties
    private static int actualScreenWidth;
    private static int actualScreenHeight;
    private static float scaleX;
    private static float scaleY;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);




        //create a bitmap depending on the orientation
        boolean isLandscape = getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE;
        /*int frameBufferWidth = isLandscape ? 1920 : 1080;
        int frameBufferHeight = isLandscape ? 1080 : 1920;*/
        int frameBufferWidth = 1080;
        int frameBufferHeight = 1920;

        //frameBufferWidth = 1080;
        //frameBufferHeight = 1920;
        Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth,
                frameBufferHeight, Bitmap.Config.RGB_565);
        //find out the scaling factor if needed
        float scaleX = (float) frameBufferWidth
                / getWindowManager().getDefaultDisplay().getWidth();
        float scaleY = (float) frameBufferHeight
                / getWindowManager().getDefaultDisplay().getHeight();

        //save the screen props for the Drawable classes
        this.actualScreenWidth = getWindowManager().getDefaultDisplay().getWidth();
        this.actualScreenHeight = getWindowManager().getDefaultDisplay().getHeight();
        this.scaleX = scaleX;
        this.scaleY = scaleY;

        //set up all the fields
        renderView = new AndroidFastRenderView(this, frameBuffer);
        graphics = new AndroidGraphics(getAssets(), frameBuffer);
        fileIO = new AndroidFileIO(this);
        audio = new AndroidAudio(this);
        input = new AndroidInput(this, renderView, scaleX, scaleY);
        screen = getStartScreen();
        setContentView(renderView); // set our render engine to the screen

        //do not dim screen
        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "GLGame");

    }

    @Override
    public void onResume() {
        super.onResume();
        wakeLock.acquire();
        screen.resume();
        renderView.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        wakeLock.release();
        renderView.pause();
        screen.pause();
        if (isFinishing())
            screen.dispose();
    }

    @Override
    public Input getInput() {
        return input;
    }

    @Override
    public FileIO getFileIO() {
        return fileIO;
    }

    @Override
    public Graphics getGraphics() {
        return graphics;
    }

    @Override
    public Audio getAudio() {
        return audio;
    }

    @Override
    public void setScreen(Screen screen) {
        if (screen == null)
            throw new IllegalArgumentException("Screen must not be null");

        this.screen.pause();
        this.screen.dispose();
        screen.resume();
        screen.update(0);
        this.screen = screen;
    }

    @Override
    public Screen getCurrentScreen() {
        return screen;
    }

    @Override
    public Screen getStartScreen(){return new LoadingScreen(this);}

    public AndroidFastRenderView getRenderView(){
        return renderView;
    }

    public static float getScaleY() {
        return scaleY;
    }

    public static float getScaleX() {
        return scaleX;
    }

    public static int getActualScreenHeight() {
        return actualScreenHeight;
    }

    public static int getActualScreenWidth() {
        return actualScreenWidth;
    }


}
