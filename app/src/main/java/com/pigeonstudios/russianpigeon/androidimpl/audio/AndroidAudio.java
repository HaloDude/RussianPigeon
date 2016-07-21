package com.pigeonstudios.russianpigeon.androidimpl.audio;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import com.pigeonstudios.russianpigeon.framework.audio.Audio;
import com.pigeonstudios.russianpigeon.framework.audio.Music;
import com.pigeonstudios.russianpigeon.framework.audio.Sound;

import java.io.IOException;

/**
 * Created by DennisFedorchuk on 7/21/2016.
 */


public class AndroidAudio implements Audio {
    AssetManager assets; //used to load files
    SoundPool soundPool;

    public AndroidAudio(Activity activity ){
        //make sure when the user changes volume it changes the music and not ringtone volume
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.assets = activity.getAssets();
        //maximum audio files to be played at the same time is 20
        this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
    }

    @Override
    public Music newMusic(String filename) {
        try{
            AssetFileDescriptor assetDescriptor = assets.openFd(filename);
            return new AndroidMusic(assetDescriptor);
        }catch (IOException e) {
            throw new RuntimeException("Could not load music '" + filename + "'");
        }
    }

    @Override
    public Sound newSound(String filename) {
        try{
            AssetFileDescriptor assetDescriptor = assets.openFd(filename);
            int soundID = soundPool.load(assetDescriptor, 0);
            return new AndroidSound(soundPool, soundID);
        }catch (IOException e) {
            throw new RuntimeException("Could not load music '" + filename + "'");
        }
    }
}
