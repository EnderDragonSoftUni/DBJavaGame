package bg.softunitower.graphicHandler;

import bg.softunitower.game.Game;
import bg.softunitower.objects.gift.CopperCoin;
import bg.softunitower.objects.gift.Gift;
import bg.softunitower.objects.gift.GoldCoin;
import bg.softunitower.objects.gift.SilverCoin;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GiftHandler {

    public static ArrayList<Gift> objects = new ArrayList<>();

    private static Random rand;
    private static int timeBetweenGifts = 0;

    public GiftHandler() {
        rand = new Random();
    }

    public void tick() {

        if (!InputHandler.beginning) {
            if (timeBetweenGifts >= 30) {
                timeBetweenGifts = 0;
                addRandomGifts();
            } else {
                timeBetweenGifts++;
            }
        }

        for (int i = 0; i < objects.size(); i++) {
            Gift tempObject = objects.get(i);

            tempObject.tick();
        }
    }

    public void render(Graphics g) {
        try {
            for (int i = 0; i < objects.size(); i++) {
                Gift tempObject = objects.get(i);

                tempObject.render(g);
            }
        } catch (Exception e) {

        }
    }

    public static void clearAllGifts() {
        objects = new ArrayList<>();
    }

    public static void addObject(Gift object) {
        objects.add(object);
    }

    public static void removeObject(Gift object) {
        objects.remove(object);
    }

    public static void addRandomGifts() {
        int tempWidth = rand.nextInt(200);
        int tempX = rand.nextInt(Game.WIDTH - tempWidth);

        objects.add(getRandomGifts(tempX, -50, 32, 32));
    }

    private static Gift getRandomGifts(int x, int y, int width, int heigh) {
        int randomNumber = rand.nextInt(3);
        Gift gift;
        switch (randomNumber) {
            case 0:
                return gift = new GoldCoin(x, y, width, heigh);
                
            case 1:
                return gift = new SilverCoin(x, y, width, heigh);
                
            case 2:
                return gift = new CopperCoin(x, y, width, heigh);
                
                default: 
                    return gift = new GoldCoin(x, y, width, heigh);
        }

    }
}
