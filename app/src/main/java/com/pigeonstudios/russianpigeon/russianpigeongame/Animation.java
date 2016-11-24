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
    private int number = 0;
    private int frames;
    private float time = 0;
    private float uploadTime;
    private List<Pixmap> animationSequence = new ArrayList<Pixmap>();

    /**
     * The whole spritesheet is uploaded. User can define the amount of columns(animation switches) and the
     * number of the row where the current animation sequence is located.
     * @param graphics - used to create the pixmaps
     * @param spriteSheet - spritesheet of all the animations
     //* @param rowNumber - the row number of the desired animation sequence --- нахуя?
     * @param rows - the amount of rows there are. used to claculate width and height
     * @param columns - amount of columns that he animation sequence is taking up
     */
    public Animation(float uploadTime, Graphics graphics, Pixmap spriteSheet, int rows, int columns){
        this.spriteSheet = spriteSheet;
        this.uploadTime = uploadTime;
        frames = columns;

        //extreact the animation and put it in the arraylist
        int width = spriteSheet.getWidth()/columns;
        int height = spriteSheet.getHeight()/ rows;

        for(int i = 0; i < columns; i++){
            animationSequence.add(graphics.newCropedPixmap("Sprites/pigeonSprite.png", Graphics.PixmapFormat.RGB565, (spriteSheet.getWidth()/columns)*i, 0, spriteSheet.getWidth()/columns, spriteSheet.getHeight()));
        }
    }

    public Pixmap getFrame(float deltaTime){
        time += deltaTime;
        if(time >= uploadTime){
            number ++;
            if(number == frames){
                number = 0;
            }
            time = 0;
        }
        return animationSequence.get(number);
    }

    public Pixmap nextAnimation(){
        number++;
        return animationSequence.get(number);
    }

    public Pixmap prevAnimation(){
        number--;
        return animationSequence.get(number);
    }

    public Pixmap getKeyFrame(int n){
        number = n;
        return animationSequence.get(number);
    }
}
