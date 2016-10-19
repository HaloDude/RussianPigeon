package com.pigeonstudios.russianpigeon.russianpigeongame;

import com.pigeonstudios.russianpigeon.framework.graphics.Graphics;
import com.pigeonstudios.russianpigeon.framework.graphics.Pixmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DennisFedorchuk on 8/7/2016.
 *
 * Serves as a single animation sequence. For example walking
 * left and walking right are different animation sequences.
 *
 */
public class Animation {
    private Pixmap spriteSheet;

    private List<Pixmap> animationSequence = new ArrayList<Pixmap>();

    /**
     * The whole spritesheet is uploaded. User can define the amount of columns(animation switches) and the
     * number of the row where the current animation sequence is located.
     * @param graphics - used to create the pixmaps
     * @param spriteSheet - spritesheet of all the animations
     * @param rowNumber - the row number of the desired animation sequence
     * @param rows - the amount of rows there are. used to claculate width and height
     * @param columns - amount of columns that he animation sequence is taking up
     */
    public Animation(Graphics graphics, Pixmap spriteSheet, int rowNumber, int rows, int columns){
        this.spriteSheet = spriteSheet;


        //extreact the animation and put it in the arraylist
        int width = spriteSheet.getWidth()/columns;
        int height = spriteSheet.getHeight()/ rows;

        for(int i = 0; i < columns; i++){
           // animationSequence.add(graphics.newPixmap());
        }
    }

    public Pixmap nextAnimation(){return null;}

    public Pixmap prevAnimation(){
        return null;
    }
}
