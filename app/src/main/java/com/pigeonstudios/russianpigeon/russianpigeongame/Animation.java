package com.pigeonstudios.russianpigeon.russianpigeongame;

import com.pigeonstudios.russianpigeon.androidimpl.graphics.AndroidPixmap;
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
     * @param updateTime - frame update interval
     * @param rows - the amount of rows there are. used to claculate width and height
     * @param columns - amount of columns that he animation sequence is taking up
     * @param sequenceRow - row number of the animation sequence
     */
    public Animation(Graphics graphics, AndroidPixmap spriteSheet, float updateTime, int rows, int columns, int sequenceRow){
        this.spriteSheet = spriteSheet;
        this.uploadTime = updateTime;
        frames = columns;

        //extreact the animation and put it in the arraylist
        int width = spriteSheet.getWidth()/columns;
        int height = spriteSheet.getHeight()/ rows;

        for(int i = 0; i < columns; i++){
            animationSequence.add(spriteSheet.cropPixmap(width*i, height*(sequenceRow-1), width, height)); //create a cropped pixmap for each animation tick
        }
    }

    /**
     * get the next frame of the animation
     * @param deltaTime - time for one tick
     * @return - return pixmap
     */
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

    /**
     * get next animation frame
     * @return - next animation frame
     */
    public Pixmap nextAnimation(){
        number++;
        return animationSequence.get(number);
    }

    /**
     * get previous animation frame
     * @return - previous animation frame
     */
    public Pixmap prevAnimation(){
        number--;
        return animationSequence.get(number);
    }

    /**
     * get a specific frame
     * @param n - frame number
     * @return - specific frame
     */
    public Pixmap getKeyFrame(int n){
        number = n;
        return animationSequence.get(number);
    }

    /**
     * set the update interval for each tick
     * @param updateTime - time
     */
    public void setUpdateTime(float updateTime) {
        this.uploadTime = updateTime;
    }
}
