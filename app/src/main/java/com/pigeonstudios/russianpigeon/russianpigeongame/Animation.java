package com.pigeonstudios.russianpigeon.russianpigeongame;

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
    private int rows;
    private int columns;
    private int width;
    private int height;

    private List<Pixmap> animationSequence = new ArrayList<Pixmap>();

    /**
     * The whole spritesheet is uploaded. User can define the amount of columns(animation switches) and the
     * number of the row where the current animation sequence is located.
     * @param spriteSheet - spritesheet of all the animations
     * @param rowNumber - the row number of the desired animation sequence
     * @param columns - amount of columns that he animation sequence is taking up
     * @param width - width of one animation pixmap
     * @param height - height of one animation pixmap
     */
    public Animation(Pixmap spriteSheet, int rowNumber, int columns, int width, int height){
        this.spriteSheet = spriteSheet;
        this.rows = rows;
        this.columns = columns;
        this.width = width;
        this.height = height;
    }

    public Pixmap nextAnimation(){
        return null;
    }

    public Pixmap prevAnimation(){
        return null;
    }
}
