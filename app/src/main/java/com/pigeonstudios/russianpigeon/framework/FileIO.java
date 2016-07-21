package com.pigeonstudios.russianpigeon.framework;

/**
 * Created by DennisFedorchuk on 6/18/2016.
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface FileIO {
    public InputStream readAsset(String fileName) throws IOException;

    public InputStream readFile(String fileName) throws IOException;

    public OutputStream writeFile(String fileName) throws IOException;
}
