package com.pigeonstudios.russianpigeon.russianpigeongame;

import com.pigeonstudios.russianpigeon.androidimpl.Input.AndroidInput;
import com.pigeonstudios.russianpigeon.framework.Game;
import com.pigeonstudios.russianpigeon.framework.Input.TouchEvent;
import com.pigeonstudios.russianpigeon.framework.Screen;
import com.pigeonstudios.russianpigeon.framework.graphics.Graphics.PixmapFormat;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by DennisFedorchuk on 8/3/2016.
 */
public class MenuScreen extends Screen {
    private Button startButton;

    public MenuScreen(Game game) {
        super(game);

        AssetSingleton.instance.setBackground(game.getGraphics().newPixmap("Menu/Background.jpg", PixmapFormat.RGB565));
        AssetSingleton.instance.setStartButton(game.getGraphics().newScaledPixmap("Menu/StartButton.png", PixmapFormat.ARGB4444, 300, 200));

        this.startButton = new Button(AssetSingleton.instance.getStartButton(), 100, 100);
    }

    @Override
    public void update(float deltaTime) {
        //test for touch
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents(); // do this to clear the bufer. idk why
        for(int i = 0; i < touchEvents.size(); i++) {
            if (touchEvents.get(i).type == TouchEvent.TOUCH_UP) {
                if (startButton.isTouched(touchEvents.get(i))) {
                    startButton.setNewLocation(startButton.getX() + 50, 1200);
                }
            }
        }

    }

    @Override
    public void draw(float deltaTime) {
        game.getGraphics().drawPixmap(AssetSingleton.instance.getBackground(), 0, 0);
        startButton.draw(game.getGraphics());
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
