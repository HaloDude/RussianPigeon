package com.pigeonstudios.russianpigeon.androidimpl.audio;

import android.media.SoundPool;

import com.pigeonstudios.russianpigeon.framework.audio.Sound;

/**
 * Created by DennisFedorchuk on 7/21/2016.
 */
public class AndroidSound implements Sound {
    int soundID;
    SoundPool soundPool;

    public AndroidSound(SoundPool soundPool, int soundID){
        this.soundPool = soundPool;
        this.soundID = soundID;
    }

    @Override
    public void play(float volume) {
        soundPool.play(soundID, volume, volume, 0, 0, 1);
    }

    @Override
    public void dispose() {
        soundPool.unload(soundID);
    }
}
