package bg.softunitower.objects;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Button {


    private static final int  BOTTON_SIZE_WIDTH = 200;

    private static final int  BOTTON_SIZE_HEIGHT = 80;

    private int x;
    private int y;
    private int width;
    private int height;
    private BufferedImage image;

    public Button(int x, int y, BufferedImage image) {
        this(x, y, BOTTON_SIZE_WIDTH, BOTTON_SIZE_HEIGHT, image);
    }

    public Button(int x, int y, int width, int height, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }
}
