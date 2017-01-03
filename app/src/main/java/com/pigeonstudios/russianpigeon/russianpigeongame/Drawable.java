package com.pigeonstudios.russianpigeon.russianpigeongame;

import android.graphics.Rect;

import com.pigeonstudios.russianpigeon.framework.Input;
import com.pigeonstudios.russianpigeon.framework.graphics.Graphics;
import com.pigeonstudios.russianpigeon.framework.graphics.Pixmap;

/**
 * Created by DennisFedorchuk on 8/7/2016.
 */
public abstract class Drawable {
    protected Pixmap pixmap;
    protected int x;
    protected int y;
    protected Graphics g;

    private Rect rectangle; //rectangle to check intersection with other objects

    public  Drawable(Pixmap pixmap, int x, int y, Graphics g){
        this.pixmap = pixmap;
        this.x = (int)(x);
        this.y = (int)(y);
        this.g = g;
        this.rectangle = new Rect(this.x ,this.y , this.x + this.pixmap.getWidth(), this.y + this.pixmap.getHeight());
    }

    public void setNewLocation(int x, int y){
        this.x = (int)(x);
        this.y = (int)(y);
        rectangle.set(x, y, x+pixmap.getWidth(), y+pixmap.getHeight());
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Pixmap getPixmap(){
        return pixmap;
    }

    public boolean isTouched(Input.TouchEvent event) {
        if ((event.x > x && event.x < x + pixmap.getWidth() - 1)) {
            if ((event.y > y && event.y < y + pixmap.getHeight() - 1))
                return true;
            return false;
        } else
            return false;
    }

    public void draw(){
        g.drawPixmap(pixmap, x, y);
    }

    /**
     * every drawable object must be updated.
     * @param deltaTime - update intervals
     */
    abstract public void update(float deltaTime);

    public void drawAtNewLocation(int x, int y){
        setNewLocation(x, y);
        g.drawPixmap(pixmap, x, y);
    }

    public Rect getRectangle(){
        return rectangle;
    }

}
