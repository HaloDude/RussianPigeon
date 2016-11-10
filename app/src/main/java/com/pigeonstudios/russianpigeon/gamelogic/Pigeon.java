package com.pigeonstudios.russianpigeon.gamelogic;

import com.pigeonstudios.russianpigeon.framework.graphics.Pixmap;
import com.pigeonstudios.russianpigeon.russianpigeongame.Animation;
import com.pigeonstudios.russianpigeon.russianpigeongame.Control;
import com.pigeonstudios.russianpigeon.russianpigeongame.Drawable;

/**
 * Created by tosch on 12.10.2016.
 */
public class Pigeon extends Drawable {
    boolean moveRight = false;
    boolean moveLeft = false;
    public Pigeon(Pixmap pixmap, int x, int y) {
        super(pixmap, x, y);

    }

    public void update(){
        move();
    }

    public void move(){
        setNewLocation(Control.targetX, 1320);
    }


}
