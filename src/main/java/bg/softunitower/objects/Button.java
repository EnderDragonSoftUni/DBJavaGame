package objects;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Button {

    private int x;
    private int y;
    private int width;
    private int height;
    private BufferedImage image;

    public Button(int x, int y, BufferedImage image) {
        this(x, y, 200, 80, image);
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
