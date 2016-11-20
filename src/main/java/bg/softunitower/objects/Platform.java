package objects;

import game.Game;
import graphicHandler.Assets;
import graphicHandler.InputHandler;
import graphicHandler.PlatformHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Platform {

    private int x;
    private int y;
    private int height;
    private static BufferedImage img = Assets.iceberg;
    private int length;

    public Platform(int x, int y, int length, int height) {
        this.x = x;
        this.y = y;
        this.length = length;
        this.height = height;
    }

    public static void setImg(BufferedImage img) {
        Platform.img = img;
    }

    public Rectangle getTopBounds() {
        return new Rectangle(this.x + 15, this.y, this.length * 64 - 30, 10);
    }

    public Rectangle getJumpingBounds() {
        return new Rectangle(this.x, this.y - 20, this.length * 64, this.height + 20);
    }

    public void tick() {
        if (!InputHandler.beginning) {
            y += 6;
        }
        if (this.y > Game.HEIGHT) {
            PlatformHandler.removeObject(this);
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < length; i++) {
            g.drawImage(img, x + i * 64, y, 64, this.height, null);
        }
    }

}
