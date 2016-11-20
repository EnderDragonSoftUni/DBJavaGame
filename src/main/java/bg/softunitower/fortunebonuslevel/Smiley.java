package bg.softunitower.fortunebonuslevel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;


public class Smiley implements RenderToCanvas {
    public static final int INITIAL_SPEED = 10;
    public static final int INITIAL_WIDTH = 40;
    public static final int INITIAL_HEIGHT = 40;
    protected Image imageSrc;
    protected int posX;
    protected int posY;
    protected int speed = 10;
    protected int directionX = 0;
    protected int directionY = 0;
    protected int width = 40;
    protected int height = 40;
    protected int posRenderX;
    protected int posRenderY;
    protected MapPath mapPath;

    public Smiley() {
        String name = "/player.png";
        this.imageSrc = (new ImageIcon(this.getClass().getResource(name))).getImage();
    }

    public void render(Graphics g) {
        this.update();
        g.drawImage(this.imageSrc,
                    this.posRenderX,
                    this.posRenderY,
                    this.width, this.height, (ImageObserver)null);
    }

    protected void update() {
        if(this.mapPath != null && this.mapPath.hasNext()) {
            if(this.mapPath.p == null) {
                this.mapPath.startMove(this.posX, this.posY);
            } else {
                this.mapPath.nextPos(this.speed);
                if(this.posX != this.mapPath.p.x) {
                    this.directionX = this.posX > this.mapPath.p.x?-1:1;
                } else {
                    this.directionX = 0;
                }

                if(this.posY != this.mapPath.p.y) {
                    this.directionY = this.posY > this.mapPath.p.y?-1:1;
                } else {
                    this.directionY = 0;
                }
            }
        } else {
            this.directionX = 0;
            this.directionY = 0;
        }
    }
}

