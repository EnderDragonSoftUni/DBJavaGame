package bg.softunitower.graphicHandler;

import bg.softunitower.game.Game;
import bg.softunitower.objects.Platform;

import java.awt.*;

/**
 * Created by Niki on 29.6.2016 г..
 */
public class LevelHandler {

    private static int currentLevel;
    private PlatformHandler platformHandler;
    private GiftHandler giftHandler;
    private static boolean levelPassed;

    public LevelHandler(PlatformHandler platformHandler, GiftHandler giftHandler) {
        currentLevel = 1;
        this.platformHandler = platformHandler;
        this.giftHandler = giftHandler;
        levelPassed = true;
    }

    public static int getCurrentLevel() {
        return currentLevel;
    }

    public static void nextLevel() {
        LevelHandler.currentLevel++;
    }

    public static void setCurrentLevel(int currentLevel) {
        LevelHandler.currentLevel = currentLevel;
    }

    public static void levelPassed() {
        levelPassed = true;
    }

    public void tick() {
        if (levelPassed) {
            levelPassed = false;
            if (currentLevel == 1) {

                Platform.setImg(Assets.iceberg);
            } else if (currentLevel == 2) {

                Platform.setImg(Assets.tile2);
            } else if (currentLevel == 3){

                Platform.setImg(Assets.iceberg);
            }
        }
    }

    public void render(Graphics g) {
        int levelSize = Assets.levelBackgrounds.size();
        g.drawImage(Assets.levelBackgrounds.get((currentLevel - 1) % levelSize), 0, 0, Game.WIDTH, Game.WIDTH, null);


     /*   } else if (getCurrentLevel() == 2) {
            g.setColor(Color.black);
            g.fillRect(0, 0,  game.WIDTH, game.WIDTH);
        } else {
            g.setColor(Color.red);
            g.fillRect(0, 0,  game.WIDTH, game.WIDTH);
        }*/
    }

}
