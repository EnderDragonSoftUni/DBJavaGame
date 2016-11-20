package game;

import graphicHandler.Assets;

import java.awt.*;

/**
 * Created by Niki on 4.8.2016 г..
 */
public class PauseMenu {

    private Graphics g;


    public PauseMenu() {

    }

    void tick() {

    }

    void render(Graphics g) {
        Assets.continueButton.render(g);
        Assets.mainMenuButton.render(g);
        Assets.pauseMenuExit.render(g);
    }
}
