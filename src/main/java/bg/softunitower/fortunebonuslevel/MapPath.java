package bg.softunitower.fortunebonuslevel;

import java.awt.Point;
import java.util.ArrayList;

public class MapPath {
    public ArrayList<Point> path = new ArrayList();
    public Point p;
    protected int index = 0;

    public MapPath() {
    }

    public void clear() {
        this.path.clear();
    }

    public void add(Point p) {
        this.path.add(p);
    }

    public void startMove(int posX, int posY) {
        this.p = new Point(posX, posY);
        this.index = this.path.size() - 1;
    }

    public boolean hasNext() {
        return this.index > -1;
    }

    public Point nextPos(int step) {
        int x = ((Point)this.path.get(this.index)).x * 40;
        int y = ((Point)this.path.get(this.index)).y * 40;
        if(this.p.x != x) {
            this.p.x += step * (x < this.p.x?-1:1);
        }

        if(this.p.y != y) {
            this.p.y += step * (y < this.p.y?-1:1);
        }

        if(this.p.x == x && this.p.y == y) {
            --this.index;
        }

        return this.p;
    }
}
