package com.pigeonstudios.russianpigeon.russianpigeongame;

import com.pigeonstudios.russianpigeon.framework.Input;
import com.pigeonstudios.russianpigeon.framework.graphics.Graphics;
import com.pigeonstudios.russianpigeon.framework.graphics.Pixmap;

/**
 * Created by DennisFedorchuk on 8/7/2016.
 */
public abstract class Drawable {
    private Pixmap pixmap;
    private int x;
    private int y;

    public  Drawable(Pixmap pixmap, int x, int y){
        this.pixmap = pixmap;
        this.x = x;
        this.y = y;
    }

    public void setNewLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public boolean isTouched(Input.TouchEvent event) {
        if ((event.x > x && event.x < x + pixmap.getWidth() - 1)) {
            if ((event.y > y && event.y < y + pixmap.getHeight() - 1))
                return true;
            return false;
        } else
            return false;
    }

    public void draw(Graphics graphics){
        graphics.drawPixmap(pixmap, x, y);
    }

    public void drawAtNewLocation(Graphics graphics, int x, int y){
        setNewLocation(x, y);
        graphics.drawPixmap(pixmap, x, y);
    }

}
