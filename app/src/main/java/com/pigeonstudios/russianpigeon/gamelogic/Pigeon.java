package com.pigeonstudios.russianpigeon.gamelogic;

import com.pigeonstudios.russianpigeon.androidimpl.graphics.AndroidPixmap;
import com.pigeonstudios.russianpigeon.framework.graphics.Graphics;
import com.pigeonstudios.russianpigeon.framework.graphics.Pixmap;
import com.pigeonstudios.russianpigeon.russianpigeongame.Animation;
import com.pigeonstudios.russianpigeon.russianpigeongame.AssetSingleton;
import com.pigeonstudios.russianpigeon.russianpigeongame.Control;
import com.pigeonstudios.russianpigeon.russianpigeongame.Drawable;

/**
 * Created by tosch on 12.10.2016.
 */
public class Pigeon extends Drawable {
    boolean moveRight = false;
    boolean moveLeft = false;

    private Animation walkingAnimationLeft;
    private Animation walkingAnimationRight;
    public int lives = 3;

    public Pigeon(Pixmap pixmap, int x, int y, Graphics g) {
        super(pixmap, x, y, g);
        walkingAnimationLeft = new Animation(this.g, (AndroidPixmap)AssetSingleton.instance.getPigeon(), 0.5f, 2, 4, 1);
        walkingAnimationRight = new Animation(this.g, (AndroidPixmap)AssetSingleton.instance.getPigeon(), 0.5f, 2, 4, 2);
        this.pixmap = walkingAnimationLeft.getFrame(0);

    }

    public void update(float deltaTime){
        if(Control.fingerPosition > 1080/2 ){
            walkingAnimationRight.setUpdateTime(0.25f - Control.accel/5f);
            this.pixmap = walkingAnimationRight.getFrame(deltaTime);
        } else{
            walkingAnimationLeft.setUpdateTime(0.25f + Control.accel/5f);
            this.pixmap = walkingAnimationLeft.getFrame(deltaTime);
        }

        move();
    }


    public void move(){
        setNewLocation(Control.targetX, 1320);
    }


}
