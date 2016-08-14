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

        AssetSingleton.instance.setBackground(game.getGraphics().newScaledPixmap("Background.jpg", PixmapFormat.RGB565, 720, 1280));
        AssetSingleton.instance.setStartButton(game.getGraphics().newScaledPixmap("Menu/StartButton.png", PixmapFormat.ARGB4444, 300, 200));

        this.startButton = new Button(AssetSingleton.instance.getStartButton(), 10, 10);
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

    private boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
        if((event.x > x && event.x < x + width - 1) && (event.y > y && event.y < y + height - 1))
            return true;
        else
            return false;
    }

    @Override
    public void draw(float deltaTime) {
        int backgroundWidth = AssetSingleton.instance.getBackground().getWidth();
        int backgroundHeight = AssetSingleton.instance.getBackground().getHeight();
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
