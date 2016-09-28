package com.pigeonstudios.russianpigeon.Handlers;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;

import com.pigeonstudios.russianpigeon.framework.FileIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by DennisFedorchuk on 7/21/2016.
 */
public class AndroidFileIO implements FileIO{
    Context context;
    AssetManager assets;
    String externalStoragePath;

    public AndroidFileIO(Context context){
        this.context = context;
        this.assets = context.getAssets();
        this.externalStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }

    @Override
    public InputStream readAsset(String fileName) throws IOException {
        return assets.open(fileName);
    }

    @Override
    public InputStream readFile(String fileName) throws IOException {
        return new FileInputStream((externalStoragePath + fileName));
    }

    @Override
    public OutputStream writeFile(String fileName) throws IOException {
        return new FileOutputStream(externalStoragePath + fileName);
    }
}
