package com.pigeonstudios.russianpigeon.russianpigeongame;

import android.graphics.Rect;

import com.pigeonstudios.russianpigeon.framework.Input;
import com.pigeonstudios.russianpigeon.framework.graphics.Graphics;
import com.pigeonstudios.russianpigeon.framework.graphics.Pixmap;

/**
 * Created by DennisFedorchuk on 8/7/2016.
 */
public abstract class Drawable {
    protected float rectangleScaleFactor;
    protected Pixmap pixmap;
    protected int x;
    protected int y;
    protected Graphics g;

    private Rect rectangle; //rectangle to check intersection with other objects

    public Drawable(Pixmap pixmap, int x, int y, Graphics g){
        this.pixmap = pixmap;
        this.x = (int)(x);
        this.y = (int)(y);
        this.g = g;
        this.rectangle = new Rect(this.x ,this.y , this.x + this.pixmap.getWidth(), this.y + this.pixmap.getHeight());
        this.rectangleScaleFactor = 1;
    }

    /**
     * @param pixmap - pismap of drawable object
     * @param x - x position
     * @param y - y position
     * @param g - graphics
     * @param rectangleScaleFactor - scale factor to make the collision detection rectangle smaller of bigger
     */
    public Drawable(Pixmap pixmap, int x, int y, Graphics g, float rectangleScaleFactor){
        this.pixmap = pixmap;
        this.x = (int)(x);
        this.y = (int)(y);
        this.g = g;
        this.rectangle = new Rect(this.x ,this.y , this.x + this.pixmap.getWidth(), this.y + this.pixmap.getHeight());
        this.rectangleScaleFactor = rectangleScaleFactor;
    }

    public void setNewLocation(int x, int y){
        this.x = x;
        this.y = y;
        //rectangle.set(x, y, x+pixmap.getWidth(), y+pixmap.getHeight());


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
        int rectWidth = (int)(pixmap.getWidth() * rectangleScaleFactor);
        int rectHeight = (int)(pixmap.getHeight() * rectangleScaleFactor);

        int widthOffset = rectWidth - pixmap.getWidth();
        int heightOffset = rectHeight - pixmap.getHeight();

        rectangle.set(x - (widthOffset/2), y - (heightOffset/2), (x - (widthOffset/2))+rectWidth, (y - (heightOffset/2))+rectHeight);


        return rectangle;
    }

    /**
     * Set the rectangle scale factor in order to make it bigger or smaller than the sprite
     * @param scaleFactor - scale factor
     */
    public void setRectangleScaleFactor(float scaleFactor){
        this.rectangleScaleFactor = scaleFactor;
    }



}
