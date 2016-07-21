package com.pigeonstudios.russianpigeon.androidimpl.audio;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

import com.pigeonstudios.russianpigeon.framework.audio.Music;

import java.io.IOException;

/**
 * Created by DennisFedorchuk on 7/21/2016.
 */
public class AndroidMusic implements Music, OnCompletionListener {
    private MediaPlayer mediaPlayer;
    private boolean isPrepared = false;

    public AndroidMusic(AssetFileDescriptor assetDesctiptor){
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(assetDesctiptor.getFileDescriptor(), assetDesctiptor.getStartOffset(), assetDesctiptor.getLength());
            mediaPlayer.prepare();
            isPrepared = true;
            mediaPlayer.setOnCompletionListener(this);
        } catch (IOException e) {
            //throwing a runtime because we want the game to stop if the music file can not be leaded
           throw new RuntimeException("Could not load music");
        }
    }

    @Override
    public void play() {
        if(mediaPlayer.isPlaying())
            return;
        try{
            synchronized (this){
                if(!isPrepared)
                    try {
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
            mediaPlayer.start();
        } catch (IllegalStateException e){
            e.printStackTrace();
        }

    }

    @Override
    public void stop() {
        mediaPlayer.stop();
        synchronized (this){
            isPrepared = false;
        }
    }

    @Override
    public void pause() {
        if(mediaPlayer.isPlaying())
            mediaPlayer.pause();
    }

    @Override
    public void setLooping(boolean looping) {
        mediaPlayer.setLooping(looping);
    }

    @Override
    public void setVolume(float volume) {
        mediaPlayer.setVolume(volume, volume); // right left chanel
    }

    @Override
    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    @Override
    public boolean isStopped() {
        return !isPrepared;
    }

    @Override
    public boolean isLooping() {
        return mediaPlayer.isLooping();
    }

    @Override
    public void dispose() {
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
        mediaPlayer.release();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        synchronized (this){
            isPrepared = false;
        }
    }
}
