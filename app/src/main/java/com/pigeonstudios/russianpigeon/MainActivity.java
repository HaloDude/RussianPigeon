package com.pigeonstudios.russianpigeon;

import android.app.Activity;
import android.os.Bundle;

import com.pigeonstudios.russianpigeon.androidimpl.AndroidGame;

public class MainActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        AndroidGame game = new AndroidGame();
    }

}
