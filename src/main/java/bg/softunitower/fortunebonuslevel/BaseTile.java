package bg.softunitower.fortunebonuslevel;


import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.HashMap;

public class BaseTile implements RenderToCanvas {
    public static final int SIZE = 40;
    private static HashMap<String, Image> images = new HashMap();
    public String image;
    public int posX;
    public int posY;
    public boolean isWalkable;
    public int door = 0;

    public BaseTile(String image, int posX, int posY, boolean isWalkable) {
        this.image = image;
        this.posX = posX;
        this.posY = posY;
        this.isWalkable = isWalkable;
    }

    public BaseTile(String image, int posX, int posY, boolean isWalkable, int door) {
        this.image = image;
        this.posX = posX;
        this.posY = posY;
        this.isWalkable = isWalkable;
        this.door = door;
    }

    public static BaseTile getTileById(int tileId) {
        return tileId == 1?new BaseTile("/brick.jpg", 0, 0, false):(tileId == 2?new BaseTile("/black.png", 0, 0, true):(tileId == 3?new BaseTile("/wizImage.png", 0, 0, true, 2):(tileId == 4?new BaseTile("/logo.png", 0, 0, true, 1):(tileId == 5?new BaseTile("/logo.png", 0, 0, true, 3):(tileId == 6?new BaseTile("/orange.png", 0, 0, false):null)))));
    }

    public void render(Graphics g) {
        g.drawImage(getImage(this.image), this.posX, this.posY, 40, 40, (ImageObserver)null);
    }

    public static Image getImage(String name) {
        if(images.get(name) == null) {
            images.put(name, (new ImageIcon(BaseTile.class.getResource(name))).getImage());
        }

        return (Image)images.get(name);
    }
}


