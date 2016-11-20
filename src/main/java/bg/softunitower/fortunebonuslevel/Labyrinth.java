package bg.softunitower.fortunebonuslevel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Labyrinth extends JPanel {
    protected Image bufer = null;
    protected Color backGround;
    protected ArrayList<RenderToCanvas> renders;

    public Labyrinth() {
        this.backGround = Color.black;
        this.renders = new ArrayList();
    }

    public void addRender(RenderToCanvas render) {
        this.renders.add(render);
    }

    public void removeRenders() {
        this.renders.clear();
    }

    public void paintLabyrinth(Graphics g) {
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(this.backGround);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        try {
            for(int i = 0; i < this.renders.size(); ++i) {
                RenderToCanvas render = (RenderToCanvas)this.renders.get(i);
                render.render(g);
            }
        } catch (Exception var4) {

        }

    }

    public void paint(Graphics g) {
        super.paint(g);
        if(this.bufer == null) {
            this.bufer = this.createImage(this.getWidth(), this.getHeight());
        }

        this.paintLabyrinth(this.bufer.getGraphics());
        g.drawImage(this.bufer, 0, 0, (ImageObserver)null);
    }
}
