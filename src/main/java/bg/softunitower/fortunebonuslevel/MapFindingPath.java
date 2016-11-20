package bg.softunitower.fortunebonuslevel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

class MapFindingPath {
    protected MapCheckPoint checkPoint;
    protected boolean toEnd;
    protected MapPath path;
    protected ArrayList<PathPoint> possiblePoints;
    protected ArrayList<PathPoint> usedPoints;

    MapFindingPath() {
    }

    public void setcheckPoint(MapCheckPoint checkPoint) {
        this.checkPoint = checkPoint;
    }

    public MapPath getPath() {
        return this.path;
    }

    public boolean findPath(int startx, int starty, int endx, int endy) {
        this.toEnd = false;
        this.path = new MapPath();
        this.usedPoints = new ArrayList();
        this.possiblePoints = new ArrayList();
        PathPoint p = new PathPoint(startx, starty, -1, -1, Math.abs(startx - endx) + Math.abs(starty - endy), true);
        this.usedPoints.add(p);
        this.possiblePoints.add(p);

        while(this.possiblePoints.size() > 0) {
            PathPoint node = (PathPoint)this.possiblePoints.get(0);
            this.possiblePoints.remove(0);
            if(node.x == endx && node.y == endy) {
                this.makePatch(node);
                this.toEnd = true;
                break;
            }

            node.visited = true;
            this.addNode(node, node.x + 1, node.y, endx, endy);
            this.addNode(node, node.x - 1, node.y, endx, endy);
            this.addNode(node, node.x, node.y + 1, endx, endy);
            this.addNode(node, node.x, node.y - 1, endx, endy);
        }

        this.usedPoints = null;
        this.possiblePoints = null;
        return this.toEnd;
    }

    protected void makePatch(PathPoint node) {
        this.path.clear();

        while(true) {
            while(node.px != -1) {
                this.path.add(new Point(node.x, node.y));
                Iterator var2 = this.usedPoints.iterator();

                while(var2.hasNext()) {
                    PathPoint p = (PathPoint)var2.next();
                    if(p.x == node.px && p.y == node.py) {
                        node = p;
                        break;
                    }
                }
            }

            return;
        }
    }

    protected void addNode(PathPoint node, int x, int y, int endx, int endy) {
        if(this.checkPoint.check(x, y)) {
            int cost = Math.abs(x - endx) + Math.abs(y - endy);
            PathPoint px = new PathPoint(x, y, node.x, node.y, cost, false);
            PathPoint old = null;
            Iterator i = this.usedPoints.iterator();

            while(i.hasNext()) {
                PathPoint p = (PathPoint)i.next();
                if(p.x == px.x && p.y == px.y) {
                    old = p;
                    break;
                }
            }

            if(old == null || old.cost > cost) {
                this.usedPoints.add(px);

                int var11;
                for(var11 = 0; var11 < this.possiblePoints.size(); ++var11) {
                    if(cost < ((PathPoint)this.possiblePoints.get(var11)).cost) {
                        this.possiblePoints.add(var11, px);
                        break;
                    }
                }

                if(var11 >= this.possiblePoints.size()) {
                    this.possiblePoints.add(px);
                }
            }
        }

    }
}

